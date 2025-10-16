#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <stdint.h>

/**
 * @description 투 포인터 병합(Merge) + 배열
 * @performance 메모리: 8,804 KB, 동작시간: 648 ms
 * @author java08
 */

int main(void)
{
    int N, M;
    if (scanf("%d %d", &N, &M) != 2)
        return 1;
    int *a = malloc(sizeof(int) * N);
    int *b = malloc(sizeof(int) * M);

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

    size_t cap = (size_t)(N + M) * 12 + 1;
    char *buf = malloc(cap);

    size_t len = 0;

    int i = 0, j = 0;
    while (i < N && j < M)
    {
        int temp = (a[i] <= b[j]) ? a[i++] : b[j++];
        size_t rem = cap - len;
        if (rem <= 0)
            break;
        len += (size_t)snprintf(buf + len, rem, "%d ", temp);
    }
    while (i < N)
    {
        size_t rem = cap - len;
        if (rem <= 0)
            break;
        len += (size_t)snprintf(buf + len, rem, "%d ", a[i++]);
    }
    while (j < M)
    {
        size_t rem = cap - len;
        if (rem <= 0)
            break;
        len += (size_t)snprintf(buf + len, rem, "%d ", b[j++]);
    }
    fwrite(buf, sizeof(char), len, stdout);

    free(buf);
    free(a);
    free(b);
    return 0;
}