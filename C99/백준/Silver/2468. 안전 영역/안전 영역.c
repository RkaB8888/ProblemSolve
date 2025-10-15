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
static int DIR[4][2] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

int main(void)
{
    int N, min = 100, max = 1;
    if (scanf("%d", &N) != 1)
        return 1;
    int arr[N][N];
    for (int i = 0; i < N; i++)
    {
        for (int j = 0; j < N; j++)
        {
            int temp;
            if (scanf("%d", &temp) != 1)
                return 1;
            arr[i][j] = temp;
            if (temp < min)
                min = temp;
            if (temp > max)
                max = temp;
        }
    }
    int flooded[N][N];
    memset(flooded, 0, sizeof flooded);
    int queue[N * N];
    int b = 0, t = 0;
    int result = 1;
    for (int lv = min; lv < max; lv++)
    {
        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < N; j++)
            {
                if (arr[i][j] <= lv)
                    flooded[i][j] = -1;
                else
                    flooded[i][j] = 0;
            }
        }
        int area = 0;
        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < N; j++)
            {
                if (flooded[i][j] == 0)
                {
                    area++;
                    b = 0;
                    t = 0;
                    queue[t++] = i * N + j;
                    flooded[i][j] = area;
                    while (b < t)
                    {
                        int r = queue[b] / N;
                        int c = queue[b++] % N;
                        for (int d = 0; d < 4; d++)
                        {
                            int nr = r + DIR[d][0];
                            int nc = c + DIR[d][1];
                            if (nr < 0 || nr >= N || nc < 0 || nc >= N)
                                continue;
                            if (flooded[nr][nc] == 0)
                            {
                                flooded[nr][nc] = area;
                                queue[t++] = nr * N + nc;
                            }
                        }
                    }
                }
            }
        }
        if (area > result)
            result = area;
    }
    printf("%d\n", result);
    return 0;
}