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
static int N, M, r, c;
static int **map, **arr;

static int stitch(int map_r, int map_c, int arr_r, int arr_c)
{
    if (map[map_r][map_c] == 1 && arr[arr_r][arr_c] == 1)
    {
        return 0;
    }
    map[map_r][map_c] |= arr[arr_r][arr_c];

    if (arr_r == r - 1 && arr_c == c - 1)
    {
        return 1;
    }

    int next_arr_r = arr_r;
    int next_arr_c = arr_c + 1;
    int next_map_r = map_r;
    int next_map_c = map_c + 1;
    if (next_arr_c == c)
    {
        next_arr_c = 0;
        next_arr_r++;
        next_map_c = map_c - arr_c;
        next_map_r++;
    }

    if (stitch(next_map_r, next_map_c, next_arr_r, next_arr_c))
    {
        return 1;
    }
    else
    {
        map[map_r][map_c] = arr[arr_r][arr_c] == 1 ? 0 : map[map_r][map_c];
        return 0;
    }
}

static int dfs()
{
    if (r > N || c > M)
    {
        return 0;
    }
    for (int i = 0; i <= N - r; i++)
    {
        for (int j = 0; j <= M - c; j++)
        {
            if (stitch(i, j, 0, 0))
            {
                return 1;
            }
        }
    }
    return 0;
}

void rotate()
{
    int **new_arr = malloc(c * sizeof(int *));
    for (int i = 0; i < c; i++)
    {
        new_arr[i] = malloc(r * sizeof(int));
    }
    for (int i = 0; i < r; i++)
    {
        for (int j = 0; j < c; j++)
        {
            new_arr[j][r - 1 - i] = arr[i][j];
        }
    }
    for (int i = 0; i < r; i++)
    {
        free(arr[i]);
    }
    free(arr);
    arr = new_arr;
    int temp = r;
    r = c;
    c = temp;
}

int main(void)
{
    int K, result = 0;
    if (scanf("%d %d %d", &N, &M, &K) != 3)
    {
        return 1;
    }
    map = malloc(N * sizeof(int *));
    for (int i = 0; i < N; i++)
    {
        map[i] = calloc(M, sizeof(int));
    }

    for (int i = 0; i < K; i++)
    {
        if (scanf("%d %d", &r, &c) != 2)
        {
            return 1;
        }
        int cnt = 0;
        arr = malloc(r * sizeof(int *));
        for (int j = 0; j < r; j++)
        {
            arr[j] = calloc(c, sizeof(int));
        }
        for (int j = 0; j < r; j++)
        {
            for (int k = 0; k < c; k++)
            {
                int cell;
                if (scanf("%d", &cell) != 1)
                {
                    return 1;
                }
                arr[j][k] = cell;
                if (cell == 1)
                {
                    cnt++;
                }
            }
        }
        for (int j = 0; j < 4; j++)
        {
            if (dfs())
            {
                result += cnt;
                // printf("Placed piece %d at rotation %d fill %d\n", i + 1, j * 90, cnt);
                break;
            }
            else
            {
                rotate();
            }
        }
        for (int j = 0; j < r; j++)
        {
            free(arr[j]);
        }
        free(arr);
    }
    printf("%d\n", result);
    for (int i = 0; i < N; i++)
    {
        free(map[i]);
    }
    free(map);
    return 0;
}