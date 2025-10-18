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

long max1(long a, long b)
{
    return a > b ? a : b;
}

long max3(long a, long b, long c)
{
    return max1(a, max1(b, c));
}

int main(void)
{
    int N;
    if (scanf("%d", &N) != 1)
    {
        return 1;
    }
    long (*dp)[3][2] = calloc(N + 1, sizeof(long[3][2]));
    if (dp == NULL)
    {
        return 1;
    }
    //[N][ACV, V, +A][screen, buffer]
    dp[1][2][0] = 1l; // 화면

    for (int i = 1; i < N; i++)
    {
        long cnt = 0l, buf = 0l;

        // ACV를 누르는 경우
        if (i + 3 <= N)
        {
            cnt = dp[i + 3][0][0];
            buf = dp[i + 3][0][1];
            if (cnt < dp[i][0][0] * 2l)
            {
                cnt = dp[i][0][0] * 2l;
                buf = dp[i][0][0];
            }
            if (cnt < dp[i][1][0] * 2l)
            {
                cnt = dp[i][1][0] * 2l;
                buf = dp[i][1][0];
            }
            if (cnt < dp[i][2][0] * 2l)
            {
                cnt = dp[i][2][0] * 2l;
                buf = dp[i][2][0];
            }
            dp[i + 3][0][0] = cnt;
            dp[i + 3][0][1] = buf;
        }

        // V만 누르는 경우
        cnt = dp[i + 1][1][0];
        buf = dp[i + 1][1][1];
        if (cnt < dp[i][0][0] + dp[i][0][1] || (cnt == dp[i][0][0] + dp[i][0][1] && dp[i][0][1] > buf))
        {
            cnt = dp[i][0][0] + dp[i][0][1];
            buf = dp[i][0][1];
        }
        if (cnt < dp[i][1][0] + dp[i][1][1] || (cnt == dp[i][1][0] + dp[i][1][1] && dp[i][1][1] > buf))
        {
            cnt = dp[i][1][0] + dp[i][1][1];
            buf = dp[i][1][1];
        }
        if (cnt < dp[i][2][0] + dp[i][2][1] || (cnt == dp[i][2][0] + dp[i][2][1] && dp[i][2][1] > buf))
        {
            cnt = dp[i][2][0] + dp[i][2][1];
            buf = dp[i][2][1];
        }
        dp[i + 1][1][0] = cnt;
        dp[i + 1][1][1] = buf;

        // A를 하나 추가하는 경우
        cnt = dp[i + 1][2][0];
        buf = dp[i + 1][2][1];
        if (cnt < dp[i][0][0] + 1l || (cnt == dp[i][0][0] + 1l && dp[i][0][1] > buf))
        {
            cnt = dp[i][0][0] + 1l;
            buf = dp[i][0][1];
        }
        if (cnt < dp[i][1][0] + 1l || (cnt == dp[i][1][0] + 1l && dp[i][1][1] > buf))
        {
            cnt = dp[i][1][0] + 1l;
            buf = dp[i][1][1];
        }
        if (cnt < dp[i][2][0] + 1l || (cnt == dp[i][2][0] + 1l && dp[i][2][1] > buf))
        {
            cnt = dp[i][2][0] + 1l;
            buf = dp[i][2][1];
        }
        dp[i + 1][2][0] = cnt;
        dp[i + 1][2][1] = buf;

        // printf("i=%d: AC=%ld, V=%ld, A=%ld\n", i,
        //        dp[i][0][0],
        //        dp[i][1][0],
        //        dp[i][2][0]);
    }
    printf("%ld\n", max3(dp[N][0][0], dp[N][1][0], dp[N][2][0]));
    free(dp);
    return 0;
}