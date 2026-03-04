#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <stdint.h>

/**
 * @description 2차원 누적합
 * @performance 메모리: 12,732 KB, 동작시간: 92 ms
 * @author java08
 */
int dp[3][1001][1001];
int main(void)
{
    int M, N, K;
    if (scanf("%d %d", &M, &N) != 2)
        return 1;
    if (scanf("%d", &K) != 1)
        return 1;
    for (int i = 1; i <= M; i++)
    {
        char line[1001];
        if (scanf("%1000s", line) != 1)
            return 1;
        for (int j = 1; j <= N; j++)
        {
            int idx = (line[j - 1] == 'J') ? 0 : ((line[j - 1] == 'O') ? 1 : 2);
            for (int k = 0; k < 3; k++)
            {
                dp[k][i][j] = dp[k][i - 1][j] + dp[k][i][j - 1] - dp[k][i - 1][j - 1];
            }
            dp[idx][i][j]++;
        }
    }
    for (int i = 0; i < K; i++)
    {
        int x1, y1, x2, y2;
        if (scanf("%d %d %d %d", &x1, &y1, &x2, &y2) != 4)
            return 1;

        printf("%d %d %d\n",
               dp[0][x2][y2] - dp[0][x1 - 1][y2] - dp[0][x2][y1 - 1] + dp[0][x1 - 1][y1 - 1],
               dp[1][x2][y2] - dp[1][x1 - 1][y2] - dp[1][x2][y1 - 1] + dp[1][x1 - 1][y1 - 1],
               dp[2][x2][y2] - dp[2][x1 - 1][y2] - dp[2][x2][y1 - 1] + dp[2][x1 - 1][y1 - 1]);
    }
    return 0;
}