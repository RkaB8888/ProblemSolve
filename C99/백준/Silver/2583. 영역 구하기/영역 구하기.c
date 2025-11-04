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

static inline int cmp(const void *a, const void *b)
{
    return *(int *)a - *(int *)b;
}

int main(void)
{
    int M, N, K;
    scanf("%d %d %d", &M, &N, &K);
    int map[N][M];
    memset(map, 0, sizeof(map));
    for (int i = 0; i < K; i++)
    {
        int x1, x2, y1, y2;
        scanf("%d %d %d %d", &x1, &y1, &x2, &y2);
        for (int j = x1; j < x2; j++)
        {
            for (int k = y1; k < y2; k++)
            {
                map[j][k] = -1;
            }
        }
    }
    int area_count = 0;
    int area_sizes[N * M];
    memset(area_sizes, 0, sizeof(area_sizes));
    int DIR[4][2] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    for (int i = 0; i < N; i++)
    {
        for (int j = 0; j < M; j++)
        {
            if (map[i][j] == 0)
            {
                area_count++;
                int area_size = 1;
                map[i][j] = area_count;
                int stack[N * M][2];
                int top = 0;
                stack[top][0] = i;
                stack[top][1] = j;
                top++;
                while (top > 0)
                {
                    top--;
                    int cx = stack[top][0];
                    int cy = stack[top][1];
                    for (int d = 0; d < 4; d++)
                    {
                        int nx = cx + DIR[d][0];
                        int ny = cy + DIR[d][1];
                        if (nx >= 0 && nx < N && ny >= 0 && ny < M && map[nx][ny] == 0)
                        {
                            map[nx][ny] = area_count;
                            area_size++;
                            stack[top][0] = nx;
                            stack[top][1] = ny;
                            top++;
                        }
                    }
                }
                area_sizes[area_count - 1] = area_size;
            }
        }
    }
    printf("%d\n", area_count);
    qsort(area_sizes, area_count, sizeof(int), cmp);
    for (int i = 0; i < area_count; i++)
    {
        printf("%d ", area_sizes[i]);
    }
    return 0;
}