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

static void reverse3x3(int r, int c, int **matrix)
{
    for (int i = 0; i < 3; i++)
    {
        for (int j = 0; j < 3; j++)
        {
            matrix[r + i][c + j] ^= 1;
        }
    }
}

static int check(int r, int c, int **matrix1, int **matrix2)
{
    for (int i = 0; i < r; i++)
    {
        for (int j = 0; j < c; j++)
        {
            if (matrix1[i][j] != matrix2[i][j])
                return 0;
        }
    }
    return 1;
}

static void end(int r, int **matrix1, int **matrix2)
{
    for (int i = 0; i < r; i++)
    {
        free(matrix1[i]);
        free(matrix2[i]);
    }
    free(matrix1);
    free(matrix2);
}

int main(void)
{
    int N, M;
    if (scanf("%d %d", &N, &M) != 2)
        return 0;
    int **matrix1 = (int **)malloc(N * sizeof(int *));
    int **matrix2 = (int **)malloc(N * sizeof(int *));
    for (int i = 0; i < N; i++)
    {
        matrix1[i] = (int *)malloc(M * sizeof(int));
        matrix2[i] = (int *)malloc(M * sizeof(int));
    }

    for (int i = 0; i < N; i++)
    {
        char buf[51];
        if (scanf("%s", buf) != 1)
        {
            end(N, matrix1, matrix2);
            return 0;
        }

        for (int j = 0; j < M; j++)
        {
            matrix1[i][j] = buf[j] - '0';
        }
    }
    for (int i = 0; i < N; i++)
    {
        char buf[51];
        if (scanf("%s", buf) != 1)
        {
            end(N, matrix1, matrix2);
            return 0;
        }
        for (int j = 0; j < M; j++)
        {
            matrix2[i][j] = buf[j] - '0';
        }
    }
    if (N < 3 || M < 3)
    {
        if (check(N, M, matrix1, matrix2))
        {
            printf("%d\n", 0);
        }
        else
            printf("%d\n", -1);
        end(N, matrix1, matrix2);
        return 0;
    }
    int cnt = 0;
    for (int i = 0; i < N - 2; i++)
    {
        for (int j = 0; j < M - 2; j++)
        {
            if (matrix1[i][j] != matrix2[i][j])
            {
                reverse3x3(i, j, matrix1);
                cnt++;
            }
        }
    }
    if (check(N, M, matrix1, matrix2))
    {
        printf("%d\n", cnt);
    }
    else
        printf("%d\n", -1);
    end(N, matrix1, matrix2);
    return 0;
}