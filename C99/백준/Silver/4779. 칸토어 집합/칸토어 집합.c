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
    char *str[13] = {0};
    int len[13] = {0};

    len[0] = 1;
    str[0] = (char *)malloc(len[0] + 1);
    str[0][0] = '-';
    str[0][1] = '\0';

    for (int i = 1; i < 13; ++i)
    {
        len[i] = len[i - 1] * 3;
        str[i] = (char *)malloc(len[i] + 1);

        memcpy(str[i], str[i - 1], len[i - 1]);
        memset(str[i] + len[i - 1], ' ', len[i - 1]);
        memcpy(str[i] + 2 * len[i - 1], str[i - 1], len[i - 1]);
        str[i][len[i]] = '\0';
    }

    int N;
    while (scanf("%d", &N) == 1)
    {
        printf("%s\n", str[N]);
    }

    for (int i = 0; i < 13; i++)
        free(str[i]);
    return 0;
}