import java.io.*;
import java.util.*;

/**
 * @description 삼분탐색
 */

// land는 NxN 크기의 2차원 배열이며, N은 1이상 300이하
// 각 요소는 0이상 10억이하 정수
// 높이 증가에 P 비용, 높이 감소에 Q비용
// P와 Q는 1이상 100이하 자연수

// 맞춰야 할 높이는 각 요소의 최소값 ~ 최대값 범위 내에 있음
// 높이의 갯수를 메모리 상에 올려두려면 최대 10억 * 4Byte -> 메모리 초과
// 적어도 높이를 정하고 해당 높이를 맞출 때의 비용을 다 계산해야 함.
// -> 예를 들어 최소가 0이고 최대가 10억이라면 범위가 넓으니 이분 탐색을 고려해볼만 함
// -> 근데 P비용과 Q비용 둘 다 자연수라서 비용 그래프가 2차 함수 형태 같은데?
// -> 기준의 오른쪽이 더 낮을지 왼쪽이 더 낮을지를 구분해야 함.
// -> 기준과 기준+1의 비용을 비교해서 낮아지는 쪽으로 이분탐색 진행.

// 단순 높이 이분탐색 시간복잡도: 300*300 * log 10^10;
public class Solution {
    private long calc(int height, int[][] land, int P, int Q){
        long result = 0;
        for(int i = 0 ; i < land.length ; i++) {
            for(int j = 0 ; j < land[0].length ; j++) {
                int diff = height - land[i][j];
                if(diff<0) {
                    result -= (long)Q * (long)diff;
                }else{
                    result += (long)P * (long)diff;
                }
            }
        }
        return result;
    }
    public long solution(int[][] land, int P, int Q) {
        int maxH = 0, minH = 1000000000;
        for(int i = 0 ; i < land.length ; i++) {
            for(int j = 0 ; j < land[0].length ; j++) {
                maxH = Math.max(maxH,land[i][j]);
                minH = Math.min(minH,land[i][j]);
            }
        }
        while(maxH-minH >= 3){
            int midH1 = minH + (maxH - minH) / 3;
            int midH2 = maxH - (maxH - minH) / 3;
            long midV1 = calc(midH1, land, P, Q);
            long midV2 = calc(midH2, land, P, Q);
            if(midV1 < midV2) { // 기준 왼쪽에 최소가 있음
                maxH = midH2;
            } else if(midV1 > midV2){ // 기준 오른쪽에 최소가 있음
                minH = midH1;
            } else {
                minH = midH1;
                maxH = midH2;
            }
        }
        long answer = calc(minH,land,P,Q);
        for(int i = minH + 1 ; i <= maxH ; i++) {
            answer = Math.min(answer,calc(i,land,P,Q));
        }
        return answer;
    }
}