import java.io.*;
import java.util.*;

/**
 * @description 정렬
 */
 
// -30,000 ~ 30,000 사이 구간분포가 존재하며, 
// 여러 지점에 선을 그어 모든 구간분포가 접하는 최소 선의 갯수

// 구간분포의 갯수는 1이상 10,000이하
// 구간의 시작과 끝을 지나도 접하는 것으로 판정

// 구간분포의 시작점을 기준으로 오름차순 정렬
// 제일 첫번째 구간분포와 두번째 구간분포의 겹치는 구간 탐색
// 해당 구간과 그 다음 번째 구간분포의 겹치는 구간 탐색 ...
// 그 다음 구간분포와 겹치지 않는다면 이전까지의 구간분포에 대한 접하는 선 +1하고 다시 초기화
// 시간복잡도는 
// 최초 정렬 10000 log 10000
// 모든 구간분포 순회하며 연산 10000
// 따라서 10000 log 10000 예상
class Solution {
    int[][] r;
    public int solution(int[][] routes) {
        Arrays.sort(routes, (a,b) -> Integer.compare(a[0], b[0]));
        int end = -30001;
        int answer = 0;
        for(int i = 0 ; i < routes.length ; i++) {
            if(end < routes[i][0]) { // 새로운 구간이 이전 구간과 겹치지 않음
                answer++;
                end = routes[i][1];
            } else {
                end = Math.min(end,routes[i][1]);
            }
        }
        return answer;
    }
}