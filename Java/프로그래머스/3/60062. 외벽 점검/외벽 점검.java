import java.io.*;
import java.util.*;

/**
 * @description 이 클래스에 대한 동작 설명
 */
 
// 최소인원으로 원 둘레의 특정 부분을 커버하는 경우 찾기
// n은 1이상 200이하
// weak의 길이는 1이상 15이하
// weak의 요소는 0이상 n-1이하
// dist의 길이는 1이상 8이하
// dist의 요소는 1이상 100이하
// 전부 점검할 수 없는 경우 -1 반환

// 가장 큰 것부터 0부터 만나는 weak마다 시작해보는 방법 -> 그냥 하면 시간복잡도 15!/(15-8)! = 약 2억6천만
// dp[15][15], dp[3][7] = 적절한 범위 -> 3번부터 7번 weak를 커버하는데 필요한 인원
// 가장 큰 범위는 사용할 곳이 정해져 있음
// 나머지 구간은 직선 구간으로 보고 차례대로 커버하기
// 순열로 가능할 듯 -> 시간복잡도 8!*15 = 604,800
class Solution {
    int weak_len;
    int[] weak2;
    int result;
    private void permmutation(int depth, int[] dist, boolean[] visited, int[] per) {
        if(depth == dist.length) {
            check(per);
            return;
        }
        for(int i = 0 ; i < dist.length ; i++) {
            if(!visited[i]) {
                visited[i] = true;
                per[depth] = dist[i];
                permmutation(depth+1, dist, visited, per);
                visited[i] = false;
            }
        }
    }
    private void check(int[] per){
        for(int i = 0 ; i < weak_len ; i++) {
            int nextIdx = 0; // 현재 친구 번호
            int maxPos = weak2[i] + per[nextIdx]; // 현재 최대 커버
            boolean possible = true;

            for(int j = i ; j < i+weak_len ; j++) {
                if(maxPos < weak2[j]) { // 커버영역 밖
                    nextIdx++; // 친구 추가

                    if(nextIdx == per.length) {
                        possible = false;
                        break;
                    }
                    maxPos = weak2[j] + per[nextIdx];
                }
            }
            if(possible) {
                result = Math.min(result,nextIdx+1);
            }
        }
    }
    public int solution(int n, int[] weak, int[] dist) {
        weak_len = weak.length;
        weak2 = new int[weak_len<<1];
        result = dist.length+1;
        for (int i = 0; i < weak_len; i++) {
            weak2[i] = weak[i];
            weak2[i + weak_len] = weak[i] + n;
        }

        boolean[] visited = new boolean[dist.length];
        int[] per = new int[dist.length];
        permmutation(0,dist,visited,per);
        return result>dist.length?-1:result;
    }
}