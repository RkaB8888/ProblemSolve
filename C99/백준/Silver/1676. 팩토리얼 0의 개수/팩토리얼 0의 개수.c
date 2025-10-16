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

    int temp = 0, two = 0, five = 0;
    for (int i = 2; i <= N; i++)
    {
        temp = i;
        while (temp % 2 == 0)
        {
            two++;
            temp /= 2;
        }
        while (temp % 5 == 0)
        {
            five++;
            temp /= 5;
        }
    }
    printf("%d\n", two < five ? two : five);
    return 0;
}