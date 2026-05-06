import java.io.*;
import java.util.*;

/**
 * @description 이 클래스에 대한 동작 설명
 */

// n은 최대 100000인 자연수
// money의 길이는 최대 100
// 정답을 1,000,000,007로 나눈 나머지를 출력

// 각 동전을 사용하는 경우의 수를 누적
// 1원을 사용한다면: 0+1원, 1+1원, 2+1원...에 1씩 더함

// 시간 복잡도: Len*N
class Solution {
    public int solution(int n, int[] money) {
        int[] dp = new int[n+1];
        dp[0] = 1;
        for(int i = 0 ; i < money.length ; i++) {
            for(int j = money[i] ; j<=n ; j++) {
                dp[j] += dp[j-money[i]];
                dp[j] %= 1000000007;
            }
        }
        return dp[n];
    }
}