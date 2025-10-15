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

int DP[31][31];

int combination(int n, int r)
{
    if (r == 0 || n == r)
        return 1;
    if (DP[n][r] != 0)
        return DP[n][r];
    return DP[n][r] = combination(n - 1, r - 1) + combination(n - 1, r);
}

int main(void)
{
    int N, M, K;
    if (scanf("%d %d %d", &N, &M, &K) != 3)
        return 0;
    int right = 0, down = 0, result = 0;
    if (K == 0)
    {
        right = M - 1;
        down = N - 1;
        result = combination(right + down, right);
    }
    else
    {
        right = (K - 1) % M;
        down = (K - 1) / M;
        result = combination(right + down, right);
        right = M - 1 - right;
        down = N - 1 - down;
        result *= combination(right + down, right);
    }
    printf("%d\n", result);
    return 0;
}