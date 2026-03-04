#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <stdint.h>

/**
 * @description 2차원 누적합
 * @performance 메모리: 1,112 KB, 동작시간: 0 ms
 * @author java08
 */

int main(void)
{
    int M, N, K;
    if (scanf("%d %d", &M, &N) != 2)
        return 1;
    if (scanf("%d", &K) != 1)
        return 1;
    int **dp[3];
    for (int i = 0; i < 3; i++)
    {
        dp[i] = malloc((M + 1) * sizeof(int *));
        for (int j = 0; j <= M; j++)
        {
            dp[i][j] = calloc((N + 1), sizeof(int));
        }
    }
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
        int result[3];
        for (int j = 0; j < 3; j++)
        {
            result[j] = dp[j][x2][y2] - dp[j][x1 - 1][y2] - dp[j][x2][y1 - 1] + dp[j][x1 - 1][y1 - 1];
        }
        printf("%d %d %d\n", result[0], result[1], result[2]);
    }
    return 0;
}