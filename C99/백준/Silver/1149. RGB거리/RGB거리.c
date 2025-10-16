#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <stdint.h>

/**
 * @description ?
 * @performance 메모리: 1,112 KB, 동작시간: 0 ms
 * @author java08
 */

int main(void)
{
    int N;
    if (scanf("%d", &N) != 1)
        return 1;
    int dp[N][3];
    if (scanf("%d %d %d", &dp[0][0], &dp[0][1], &dp[0][2]) != 3)
        return 1;
    for (int i = 1; i < N; i++)
    {
        int a, b, c;
        if (scanf("%d %d %d", &a, &b, &c) != 3)
            return 1;
        dp[i][0] = dp[i - 1][1] > dp[i - 1][2] ? dp[i - 1][2] + a : dp[i - 1][1] + a;
        dp[i][1] = dp[i - 1][0] > dp[i - 1][2] ? dp[i - 1][2] + b : dp[i - 1][0] + b;
        dp[i][2] = dp[i - 1][0] > dp[i - 1][1] ? dp[i - 1][1] + c : dp[i - 1][0] + c;
    }
    printf("%d\n", dp[N - 1][0] > dp[N - 1][1] ? (dp[N - 1][1] > dp[N - 1][2] ? dp[N - 1][2] : dp[N - 1][1]) : (dp[N - 1][0] > dp[N - 1][2] ? dp[N - 1][2] : dp[N - 1][0]));
    return 0;
}