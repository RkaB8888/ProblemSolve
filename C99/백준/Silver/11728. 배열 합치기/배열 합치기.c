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
    int N, M;
    if (scanf("%d %d", &N, &M) != 2)
        return 1;
    int a[N];
    int b[M];
    for (int i = 0; i < N; i++)
    {
        if (scanf("%d", &a[i]) != 1)
            return 1;
    }
    for (int i = 0; i < M; i++)
    {
        if (scanf("%d", &b[i]) != 1)
            return 1;
    }
    int i = 0, j = 0;
    while (i < N && j < M)
    {
        if (a[i] < b[j])
            printf("%d ", a[i++]);
        else
            printf("%d ", b[j++]);
    }
    while (i < N)
        printf("%d ", a[i++]);
    while (j < M)
        printf("%d ", b[j++]);
    printf("\n");
    return 0;
}