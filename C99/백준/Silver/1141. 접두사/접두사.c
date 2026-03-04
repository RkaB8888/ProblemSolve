#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <stdint.h>

/**
 * @description 정렬
 * @performance 메모리: 1,112 KB, 동작시간: 0 ms
 * @author java08
 */

int cmp(const void *a, const void *b)
{
    const char *s1 = (const char *)a;
    const char *s2 = (const char *)b;
    return strcmp(s1, s2);
}

int main(void)
{
    int N;
    char words[50][51];

    if (scanf("%d", &N) != 1)
        return 1;
    for (int i = 0; i < N; i++)
    {
        if (scanf("%50s", words[i]) != 1)
            return 1;
    }
    qsort(words, N, sizeof(words[0]), cmp);
    int result = N;
    for (int i = 1; i < N; i++)
    {
        if (strncmp(words[i - 1], words[i], strlen(words[i - 1])) == 0)
            result--;
    }
    printf("%d\n", result);
    return 0;
}