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
    int temp = 1, count = 0;
    for (int i = 2; i <= N; i++)
    {
        temp *= i;
        while (temp % 10 == 0)
        {
            count++;
            temp /= 10;
        }
        temp %= 100000;
    }
    printf("%d\n", count);
    return 0;
}