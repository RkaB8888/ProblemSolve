import java.io.*;
import java.util.*;

/**
 * @description 정렬 탐색
 */

// land는 NxN 크기의 2차원 배열이며, N은 1이상 300이하
// 각 요소는 0이상 10억이하 정수
// 높이 증가에 P 비용, 높이 감소에 Q비용
// P와 Q는 1이상 100이하 자연수

// 맞춰야 할 높이는 각 요소의 최소값 ~ 최대값 범위 내에 있음
// 높이의 갯수를 메모리 상에 올려두려면 최대 10억 * 4Byte -> 메모리 초과
// 적어도 높이를 정하고 해당 높이를 맞출 때의 비용을 다 계산해야 함.

// land를 1차원 배열로 오름차순 정렬한다.
// 제일 작은 높이를 기준으로 나머지 모든 높이를 깎을 때의 비용을 구한다.
// 그 다음으로 작은 높이[1]를 기준으로 비용을 계산할 때
// h[0]은 h[1]-h[0]만큼 높이를 증가시켜야 하므로 P*(h[1]-h[0])을 비용에 추가하고
// h[2]~h[N*N]은 h[1]-h[0]만큼의 높이를 원복하는 과정이므로 Q*(h[1]-h[0])이 각각 제거된다.
// 이를 N*N 순회할 때 최소비용을 반환하면 될 듯. 
public class Solution {
    public long solution(int[][] land, int P, int Q) {
        int len = land.length * land.length;
        int[] h = new int[len];
        for(int i = 0 ; i < land.length ; i++) {
            for(int j = 0 ; j < land[0].length ; j++) {
                h[i*land.length+j] = land[i][j];
            }
        }
        Arrays.sort(h);
        long answer = 0;
        for(int i = 1 ; i < len ; i++) {
            answer += (long)(h[i]-h[0])*Q;
        }
        long preV = answer;
        for(int i = 1 ; i < len ; i++) {
            long diff = h[i]-h[i-1];
            preV += diff*P*i;
            preV -= diff*Q*(len-i);
            answer = Math.min(answer,preV);
        }
        return answer;
    }
}