import java.io.*;
import java.util.*;

/**
 * @description 2차원 DP
 */
 
// alp: 알고력 0이상 150이하
// cop: 코딩력 0이상 150이하
// problems: 1이상 100이하
// [요구 알고력, 요구 코딩력, 보상 알고력, 보상 코딩력, 시간비용]
// 요구는 0이상 150이하, 보상은 0이상 30이하, 시간은 1이상 100이하

// 모든 problems를 해결할 수 있는 알고력과 코딩력을 갖는 최단시간 구하기

// 일단 채워야 하는 알고력과 코딩력을 구한다.
// 2차원 DP에 완전탐색시
// -> [151][151], 각 상태에 102번(problem은 100, 알고와 코딩 1씩 올리는 것 2개)씩 하면
// -> 시간 복잡도는 151*151*102 = 2,325,702
class Solution {
    public int solution(int alp, int cop, int[][] problems) {
        int maxAlp = 0, maxCop = 0;
        for(int[] p : problems) {
            maxAlp = Math.max(maxAlp,p[0]);
            maxCop = Math.max(maxCop,p[1]);
        }
        int[][] dp = new int[maxAlp+1][maxCop+1];
        alp = Math.min(maxAlp,alp);
        cop = Math.min(maxCop,cop);
        for(int i = 0 ; i <= maxAlp ; i++) {
            Arrays.fill(dp[i],maxAlp-alp+maxCop-cop);
        }
        dp[alp][cop] = 0;
        for(int i = alp ; i <= maxAlp ; i++) {
            for(int j = cop ; j <= maxCop ; j++) {
                if(i!=maxAlp) dp[i+1][j] = Math.min(dp[i+1][j],dp[i][j]+1);
                if(j!=maxCop) dp[i][j+1] = Math.min(dp[i][j+1],dp[i][j]+1);
                for(int[] p : problems) {
                    int alp_req = p[0];
                    int cop_req = p[1];
                    int ni = Math.min(maxAlp,i+p[2]);
                    int nj = Math.min(maxCop,j+p[3]);
                    int cost = p[4];
                    if(alp_req>i||cop_req>j) continue; // 풀 수 없는 경우
                    dp[ni][nj] = Math.min(dp[ni][nj],dp[i][j]+cost);
                }
            }
        }
        return dp[maxAlp][maxCop];
    }
}