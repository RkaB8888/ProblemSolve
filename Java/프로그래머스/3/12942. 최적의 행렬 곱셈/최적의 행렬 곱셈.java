import java.io.*;
import java.util.*;

/**
 * @description DP
 */

// dp[i][j] -> i부터 j까지의 곱할 때 최소 횟수 저장
 
class Solution {
    public int solution(int[][] matrix_sizes) {
        int n = matrix_sizes.length;
        int[][] dp = new int[n][n];
        for(int i = 0 ; i < n ; i++) {
            Arrays.fill(dp[i],Integer.MAX_VALUE);
            dp[i][i] = 0;
        }
        for(int len = 1 ; len < n ; len++) {
            for(int i = 0 ; i < n-len ; i++) {
                int j = i+len;
                for(int k = i ; k < j ; k++) {
                    // i부터 k까지 계산한 횟수 + k+1부터 j까지 계산한 횟수 + 두 행렬을 곱하는 계산 횟수
                    dp[i][j] = Math.min(dp[i][j],dp[i][k]+dp[k+1][j]+(matrix_sizes[i][0]*matrix_sizes[k][1]*matrix_sizes[j][1]));
                }
            }
        }
    
        return dp[0][n-1];
    }
    // public static void main(String[] args) {
    //     int[][] matrix_sizes = {{5,3},{3,10},{10,6}};
    //     Solution sol = new Solution();
    //     System.out.println(sol.solution(matrix_sizes));
    // }
}