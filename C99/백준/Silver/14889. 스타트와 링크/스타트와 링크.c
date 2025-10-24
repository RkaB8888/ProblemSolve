#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <stdint.h>

/**
 * @description 조합 탐색 + 비트마스크 + 인접 행렬
 * @performance 메모리: 1,112 KB, 동작시간: 0 ms
 * @author java08
 */

static int N, diff = 9000; // 최대 10명, 서로 최대 100 차이
static int **matrix;
static bool *visited;
static int MAX;

static bool next_permutation(int *arr, int size)
{
    int i = 0;
    while (i < size - 1 && (((*arr >> (i + 1)) & 1) >= ((*arr >> i) & 1)))
    {
        i++;
    }
    if (i == size - 1)
    {
        return false;
    }
    int j = 0;
    while (((*arr >> j) & 1) <= ((*arr >> (i + 1)) & 1))
    {
        j++;
    }
    *arr |= (1 << (i + 1));
    *arr &= ~(1 << j);
    int left = i, right = 0;
    while (left > right)
    {
        int left_bit = ((*arr >> left) & 1);
        int right_bit = ((*arr >> right) & 1);
        if (left_bit != right_bit)
        {
            if (left_bit)
            {
                *arr |= (1 << right);
                *arr &= ~(1 << left);
            }
            else
            {
                *arr |= (1 << left);
                *arr &= ~(1 << right);
            }
        }
        left--;
        right++;
    }
    return true;
}

int main(void)
{
    if (scanf("%d", &N) != 1)
    {
        return 1;
    }
    visited = calloc(1 << N, sizeof(bool)); // N은 최대 20
    MAX = (1 << N) - 1;
    matrix = (int **)malloc(N * sizeof(int *));
    for (int i = 0; i < N; i++)
    {
        matrix[i] = (int *)malloc(N * sizeof(int));
        for (int j = 0; j < N; j++)
        {
            if (scanf("%d", &matrix[i][j]) != 1)
            {
                return 1;
            }
        }
    }

    int team = 0;
    for (int i = 0; i < N / 2; i++)
    {
        team |= (1 << i);
    }
    int *membersA = (int *)malloc((N >> 1) * sizeof(int));
    int *membersB = (int *)malloc((N >> 1) * sizeof(int));
    do
    {
        if (visited[team])
        {
            continue;
        }
        visited[team] = true;
        visited[MAX ^ team] = true;
        int teamA = 0, teamB = 0;
        int sizeA = 0, sizeB = 0;

        int t = team;
        for (int i = 0; i < N; i++)
        {
            if (t & 1)
            {
                membersA[sizeA++] = i;
            }
            else
            {
                membersB[sizeB++] = i;
            }
            t >>= 1;
        }
        for (int i = 0; i < sizeA; i++)
        {
            for (int j = 0; j < sizeA; j++)
            {
                if (i != j)
                {
                    teamA += matrix[membersA[i]][membersA[j]];
                }
            }
        }
        for (int i = 0; i < sizeB; i++)
        {
            for (int j = 0; j < sizeB; j++)
            {
                if (i != j)
                {
                    teamB += matrix[membersB[i]][membersB[j]];
                }
            }
        }
        int curr_diff = teamA > teamB ? teamA - teamB : teamB - teamA;
        if (curr_diff < diff)
        {
            diff = curr_diff;
        }

    } while (diff && next_permutation(&team, N));
    free(membersA);
    free(membersB);
    printf("%d\n", diff);
    for (int i = 0; i < N; i++)
    {
        free(matrix[i]);
    }
    free(matrix);
    free(visited);
    return 0;
}