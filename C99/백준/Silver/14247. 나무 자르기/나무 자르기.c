#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <stdint.h>

/**
 * @description Counting Sort
 * @performance 메모리: 1,364 KB, 동작시간: 16 ms
 * @author java08
 */

int main(void)
{
    int n;
    if (scanf("%d", &n) != 1)
    {
        return 1;
    }
    long result = 0;
    for (int i = 0; i < n; i++)
    {
        int h;
        if (scanf("%d", &h) != 1)
        {
            return 1;
        }
        result += h;
    }
    int *grow = malloc(n * sizeof(int));
    int max = 0;
    for (int i = 0; i < n; i++)
    {
        int a;
        if (scanf("%d", &a) != 1)
        {
            return 1;
        }
        grow[i] = a;
        if (a > max)
        {
            max = a;
        }
    }

    // 카운팅 배열
    int *count = calloc(max + 1, sizeof(int));
    for (int i = 0; i < n; i++)
    {
        count[grow[i]]++;
    }
    free(grow);
    int idx = 0;
    for (int i = 1; i <= max; i++)
    {
        for (; count[i]; count[i]--)
        {
            result += i * idx++;
        }
    }
    printf("%ld\n", result);
    free(count);
    return 0;
}