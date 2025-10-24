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
    int S, K;
    if (scanf("%d %d", &S, &K) != 2)
    {
        return 1; // 입력 오류 처리
    }

    int quotient = S / K;
    int remainder = S % K;
    long long result = 1;
    for (int i = 0; i < K; i++)
    {
        if (remainder)
        {
            result *= (quotient + 1);
            remainder--;
        }
        else
        {
            result *= quotient;
        }
    }
    printf("%lld\n", result);
    return 0;
}