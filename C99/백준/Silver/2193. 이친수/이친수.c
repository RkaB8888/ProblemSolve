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
    int N;
    if (scanf("%d", &N) != 1)
        return 1;
    long (*arr)[2] = malloc(N * sizeof(long[2]));
    arr[0][0] = 0;
    arr[0][1] = 1;
    for (int i = 1; i < N; i++)
    {
        arr[i][0] = arr[i - 1][1] + arr[i - 1][0];
        arr[i][1] = arr[i - 1][0];
    }
    printf("%ld\n", arr[N - 1][0] + arr[N - 1][1]);
    free(arr);
    return 0;
}