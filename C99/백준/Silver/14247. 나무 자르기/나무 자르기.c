#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <stdint.h>

/**
 * @description 정렬(qsort) + 배열
 * @performance 메모리: 1,484 KB, 동작시간: 24 ms
 * @author java08
 */

int main(void)
{
    int n;
    if (scanf("%d", &n) != 1)
    {
        return 1; // 입력 오류 처리
    }
    long result = 0;
    for (int i = 0; i < n; i++)
    {
        int h;
        if (scanf("%d", &h) != 1)
        {
            return 1; // 입력 오류 처리
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
            return 1; // 입력 오류 처리
        }
        grow[i] = a;
        if (a > max)
        {
            max = a;
        }
    }
    int *count = calloc(max + 1, sizeof(int));
    for (int i = 0; i < n; i++)
    {
        count[grow[i]]++;
    }
    int idx = 0;
    for (int i = 1; i <= max; i++)
    {
        for (; count[i]; count[i]--)
        {
            grow[idx++] = i;
        }
    }
    free(count);

    for (int i = 0; i < n; i++)
    {
        result += grow[i] * i;
    }
    printf("%ld\n", result);

    free(grow);
    return 0;
}