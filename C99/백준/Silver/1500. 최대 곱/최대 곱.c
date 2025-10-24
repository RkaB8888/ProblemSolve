#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <stdint.h>

/**
 * @description 수학적 분할(Quotient 분배) + 반복 계산
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

    int q = S / K, r = S % K;
    long long result = 1;
    while (K--)
    {
        result *= (K < r) ? (q + 1) : q;
    }
    printf("%lld\n", result);
    return 0;
}