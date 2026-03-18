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

static int BIT[] = {
    0b1110111, // 0
    0b0010001, // 1
    0b0111110, // 2
    0b0111011, // 3
    0b1011001, // 4
    0b1101011, // 5
    0b1101111, // 6
    0b0110001, // 7
    0b1111111, // 8
    0b1111011  // 9
};

int N, K, P, X;

int bcount(int a, int b)
{
    int temp = a ^ b;
    int cnt = 0;
    while (temp)
    {
        if (temp & 1)
        {
            cnt++;
        }
        temp >>= 1;
    }
    return cnt;
}

int main(void)
{
    if (scanf("%d %d %d %d", &N, &K, &P, &X) != 4)
        return 1;

    int n_digit[6] = {0};
    int cnt[6][10] = {0};
    int tempX = X, tempN = N;
    for (int i = 0; i < 6; i++)
    {
        int digitX = tempX % 10;
        tempX /= 10;
        int digitN = tempN % 10;
        n_digit[i] = digitN;
        tempN /= 10;

        for (int j = 0; j < 10; j++)
        {
            cnt[i][j] = bcount(BIT[digitX], BIT[j]);
        }
    }

    int dp[7][43][2];
    memset(dp, 0, sizeof(dp));
    dp[K][0][0] = 1; // 아무것도 바꾸지 않은 상태

    for (int i = K; i > 0; i--)
    {
        for (int p = 0; p <= P; p++)
        {
            for (int l = 0; l < 2; l++)
            {
                if (dp[i][p][l] == 0)
                    continue;
                int limit = l ? 9 : n_digit[i - 1];
                for (int d = 0; d <= limit; d++)
                {
                    int nextP = p + cnt[i - 1][d];
                    if (nextP <= P)
                    {
                        if (l == 0 && d == limit) // N과 계속 같은 숫자라면
                        {
                            dp[i - 1][nextP][0] += dp[i][p][l];
                        }
                        else
                        {
                            dp[i - 1][nextP][1] += dp[i][p][l];
                        }
                    }
                }
            }
        }
    }
    int sum = 0;
    for (int i = 0; i <= P; i++)
    {
        sum += dp[0][i][0] + dp[0][i][1];
    }
    int allZero = 0;
    for (int i = 0; i < K; i++)
    {
        allZero += cnt[i][0];
    }
    if (allZero <= P)
        sum--;
    sum--;
    printf("%d\n", sum);
    return 0;
}