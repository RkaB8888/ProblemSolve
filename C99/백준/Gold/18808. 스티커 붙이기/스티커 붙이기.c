#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <stdint.h>

/**
 * @description 완전탐색(Brute Force) + 2차원 배열(Matrix)
 * @performance 메모리: 1,116 KB, 동작시간: 8 ms
 * @author java08
 */
static int N, M, r, c;
static long *map, *arr;

static int stitch(int map_r, int map_c, long piece_bits)
{

    for (int i = 0; i < r; i++)
    {
        long map_bits = map[map_r + i] >> (M - c - map_c);
        map_bits = map_bits & piece_bits;
        if (map_bits & arr[i])
        {
            return 0;
        }
    }
    for (int i = 0; i < r; i++)
    {
        map[map_r + i] |= (arr[i] << (M - c - map_c));
    }
    return 1;
}

static int dfs()
{
    if (r > N || c > M)
    {
        return 0;
    }
    long piece_bits = 1 << c;
    piece_bits--;
    for (int i = 0; i <= N - r; i++)
    {
        for (int j = 0; j <= M - c; j++)
        {
            if (stitch(i, j, piece_bits))
            {
                return 1;
            }
        }
    }
    return 0;
}

void rotate()
{
    long *new_arr = calloc(c, sizeof(long));
    for (int i = 0; i < r; i++)
    {
        for (int j = 0; j < c; j++)
        {
            if (arr[i] & (1 << (c - j - 1)))
            {
                new_arr[j] |= (1 << i);
            }
        }
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
    map = calloc(N, sizeof(long));

    for (int i = 0; i < K; i++)
    {
        if (scanf("%d %d", &r, &c) != 2)
        {
            return 1;
        }
        int cnt = 0;
        arr = calloc(r, sizeof(long));
        for (int j = 0; j < r; j++)
        {
            long col = 0;
            for (int k = 0; k < c; k++)
            {
                int cell;
                if (scanf("%d", &cell) != 1)
                {
                    return 1;
                }
                col = (col << 1) | cell;
                if (cell == 1)
                {
                    cnt++;
                }
            }
            arr[j] = col;
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
        free(arr);
    }
    printf("%d\n", result);
    free(map);
    return 0;
}