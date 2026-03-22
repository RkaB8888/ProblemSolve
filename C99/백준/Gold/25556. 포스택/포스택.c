#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <stdint.h>

/**
 * @description Greedy + Stack
 * @performance 메모리: 1,116 KB, 동작시간: 12 ms
 * @author java08
 */

int main(void)
{
    int N;
    int s[4] = {0};
    if (scanf("%d", &N) != 1)
        return 1;
    int cur = 0;
    bool flag = false;
    for (int i = 0; i < N; i++)
    {
        if (scanf("%d", &cur) != 1)
            return 1;
        if (flag)
            continue;
        int minS = -1, minD = 100001;
        for (int j = 0; j < 4; j++)
        {
            int d = cur - s[j];
            if (d > 0 && minD > d)
            {
                minD = d;
                minS = j;
            }
        }

        if (minD == 100001)
        {
            flag = true;
        }
        else
        {
            s[minS] = cur;
        }
    }
    if (flag)
    {
        printf("NO\n");
    }
    else
    {
        printf("YES\n");
    }
    return 0;
}