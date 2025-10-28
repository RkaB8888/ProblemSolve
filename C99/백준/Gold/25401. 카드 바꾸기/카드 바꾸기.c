#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <stdint.h>

/**
 * @description 완전탐색(모든 쌍) + 등차수열 판정 + 배열
 * @performance 메모리: 1,112 KB, 동작시간: 0 ms
 * @author java08
 */

int main(void)
{
    int N;
    scanf("%d", &N);
    int *arr = malloc(N * sizeof(int));
    for (int i = 0; i < N; i++)
    {
        scanf("%d", &arr[i]);
    }

    int cnt = N;
    for (int i = 0; i < N - 1; i++)
    {
        for (int j = i + 1; j < N; j++)
        {
            int diff = arr[j] - arr[i];
            int step = j - i;
            if (diff % step != 0)
                continue;
            int d = diff / step;
            int c = 0;
            for (int k = 0, l = arr[i] - d * i; k < N; k++, l += d)
            {
                if (arr[k] != l)
                {
                    c++;
                    if (c >= cnt)
                        break;
                }
            }
            if (c < cnt)
                cnt = c;
        }
    }
    printf("%d\n", cnt);
    free(arr);
    return 0;
}