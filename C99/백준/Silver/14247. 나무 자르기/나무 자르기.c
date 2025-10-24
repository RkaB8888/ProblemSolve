#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <stdint.h>

/**
 * @description 정렬(qsort) + 배열
 * @performance 메모리: 2,108 KB, 동작시간: 24 ms
 * @author java08
 */

static int compare(const void *a, const void *b)
{
    int tree1 = *(int *)a;
    int tree2 = *(int *)b;
    return tree1 - tree2;
}

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
    int grow[n];
    for (int i = 0; i < n; i++)
    {
        int a;
        if (scanf("%d", &a) != 1)
        {
            return 1; // 입력 오류 처리
        }
        grow[i] = a;
    }
    qsort(grow, n, sizeof(int), compare);
    for (int i = 0; i < n; i++)
    {
        result += grow[i] * i;
    }

    printf("%ld\n", result);
    return 0;
}