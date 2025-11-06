#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <stdint.h>

/**
 * @description BFS
 * @performance 메모리: 1,112 KB, 동작시간: 0 ms
 * @author java08
 */
static int N, M; // 2이상 100,000이하
static int *higher, *w_sum;

static inline int fast_int()
{
    int s = 1, c, n;
    while ((n = getchar()) <= 32)
        ;
    if (n == '-')
    {
        s = -1;
        n = getchar();
    }
    n &= 15;
    while ((c = getchar()) > 32)
    {
        n = (n << 3) + (n << 1) + (c & 15);
    }
    return s * n;
}

static inline void bfs()
{
    for (int i = 1; i <= N; i++)
    {
        w_sum[i] += w_sum[higher[i]];
    }
}

int main(void)
{
    N = fast_int();
    M = fast_int();

    higher = malloc((N + 1) * sizeof(int));
    w_sum = calloc((N + 1), sizeof(int));

    for (int i = 1; i <= N; i++)
    {
        int h = fast_int();
        higher[i] = h;
    }
    higher[1] = 0;

    for (int i = 0; i < M; i++)
    {
        int emp = fast_int();
        int w = fast_int();
        w_sum[emp] += w;
    }

    bfs();

    for (int i = 1; i <= N; i++)
    {
        printf("%d ", w_sum[i]);
    }

    free(higher);
    free(w_sum);
    return 0;
}