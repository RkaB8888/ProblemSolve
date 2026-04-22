import java.io.*;
import java.util.*;

/**
 * @description 이 클래스에 대한 동작 설명
 * @performance 메모리: ? KB, 동작시간: ? ms
 */
 
// 합승 구간 비용 + 개별 구간 비용의 합이 최소가 되는 경로
// 플로이드 워샬로 각 노드에서 A와 B로 갈 수 있는 최소 비용 계산
// S->C 의 비용 + C->A의 비용 + C->B의 비용이 최소가 되는 경우
// 플로이드 워샬 N^3

// n: 지점의 개수 (3 이상 200 이하 자연수)
// s: 출발지점, a: A의 도착지점, b: B의 도착지점 (1 이상 n 이하 자연수)
// fares: 지점 사이의 예상 택시요금 (1 이상 100,000 이하 자연수)
class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = 0;
        int len = fares.length;
        int[][] dp = new int[n+1][n+1];
        for(int i = 0 ; i <= n ; i++) {
            Arrays.fill(dp[i],Integer.MAX_VALUE);
            dp[i][i] = 0;
        }
        for(int i = 0 ; i < len ; i++){
            dp[fares[i][0]][fares[i][1]] = fares[i][2];
            dp[fares[i][1]][fares[i][0]] = fares[i][2];
        }
        
        for(int k = 1 ; k <= n ; k++) {
            for(int i = 1 ; i <= n ; i++) {
                for(int j = 1 ; j <= n ; j++) {
                    if(dp[i][k]==Integer.MAX_VALUE || dp[k][j]==Integer.MAX_VALUE) continue;
                    dp[i][j] = Math.min(dp[i][j],dp[i][k]+dp[k][j]);
                }
            }
        }

        answer = dp[s][a] + dp[s][b];
        for(int i = 1 ; i <= n ; i++) {
            if(dp[s][i]==Integer.MAX_VALUE || dp[i][a]==Integer.MAX_VALUE || dp[i][b]==Integer.MAX_VALUE) continue;
            answer = Math.min(answer,dp[s][i]+dp[i][a]+dp[i][b]);
        }
        return answer;
    }
}