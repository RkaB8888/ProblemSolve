import java.io.*;
import java.util.*;

/**
 * @description 이 클래스에 대한 동작 설명
 */
 
// 행과 열은 모두 1 이상 300 이하
// 각 열마다 채워야 하는 1의 갯수 관리
// 각 열을 순회하면서 k개의 1을 채울 때 짝수 행의 갯수 추적
// -> k개의 행이 짝/홀이 바뀜
// 총 행길이가 N이라면 k개 바꿔서 j개 짝수 행이 남으려면
// 짝수행: j개, 홀수행: N-j개
// [j-k] ~ [j+k] (j-k가 0보다 같거나 크고, j+k가 N보다 같거나 작아야 함)
// dp[0][N] = 1 부터 시작
class Solution {
    private static final int MOD = 10000019;
    public int solution(int[][] a) {
        int rowLen = a.length;
        int colLen = a[0].length;

        long[][] comb = new long[rowLen+1][rowLen+1]; // 행의 개수만 고려한 조합
        for(int i = 0 ; i <= rowLen ; i++) {
            comb[i][0]=1;
            for(int j = 1 ; j <= i ; j++) {
                comb[i][j] = (comb[i-1][j-1]+comb[i-1][j])%MOD;
            }
        }

        int[] colCnt = new int[colLen];
        for(int i = 0 ; i < colLen ; i++) {
            for(int j = 0 ; j < rowLen ; j++) {
                if(a[j][i]==1) colCnt[i]++;
            }
        }

        long[] dp = new long[rowLen+1];
        dp[rowLen] = 1;
        for(int i = 0 ; i < colLen ; i++) {
            int k = colCnt[i]; // 해당 열에 k개가 채워져야 함 -> k개 행의 짝/홀이 바뀜
            long[] ndp = new long[rowLen+1];

            for(int j = 0 ; j <= rowLen ; j++) {//이전 열까지의 짝수행 갯수
                if(dp[j]==0) continue;
                for(int even = 0 ; even <= k ; even++) {//k개 중에서 짝수행 갯수
                    int odd = k - even;
                    if(j>=even && rowLen-j>=odd) {
                        int nextEven = j - even + odd;
                        long c = (comb[j][even]*comb[rowLen-j][odd])%MOD; // j개의 행 중에서 even개를 고르는 경우 * j-even개의 행 중에서 odd개를 고르는 경우
                        ndp[nextEven] += ((dp[j]*c)%MOD);
                    }
                }
            }
            dp = ndp;
        }
        return (int)dp[rowLen];
    }
}