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
    char *scr = malloc(4001);
    char *dst = malloc(4001);
    if (scanf("%4000s", scr) != 1)
    {
        free(scr);
        free(dst);
        return 1;
    }
    if (scanf("%4000s", dst) != 1)
    {
        free(scr);
        free(dst);
        return 1;
    }
    int scr_len = strlen(scr);
    int dst_len = strlen(dst);
    int count = 0;
    for (int k = 0; k < scr_len; k++)
    {
        int len = 0;
        for (int i = k, j = 0; i < scr_len && j < dst_len; i++, j++)
        {
            if (scr[i] == dst[j])
            {
                len++;
            }
            else
            {
                if (len > count)
                {
                    count = len;
                }
                len = 0;
            }
        }
        if (len > count)
            count = len;

        len = 0;
        for (int i = 0, j = k; i < scr_len && j < dst_len; i++, j++)
        {
            if (scr[i] == dst[j])
            {
                len++;
            }
            else
            {
                if (len > count)
                {
                    count = len;
                }
                len = 0;
            }
        }
        if (len > count)
            count = len;
    }
    printf("%d\n", count);
    free(scr);
    free(dst);
    return 0;
}