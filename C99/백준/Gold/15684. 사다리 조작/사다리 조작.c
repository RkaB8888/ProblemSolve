#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <stdint.h>

/**
 * @description dfs
 * @performance 메모리: 1,112 KB, 동작시간: 0 ms
 * @author java08
 */

static int N;               // 세로선의 개수 [2,10]
static int M;               // 가로선의 개수 [0, (N-1)*H]
static int H;               // 가로선 위치 개수 [1,30]
static bool ladder[31][11]; // 행: 1~H, 열: 1~(N-1), 0과 N은 패딩
static int cnt;

static bool check()
{
    for (int i = 1; i <= N; i++)
    {
        int col = i;
        for (int h = 1; h <= H; h++)
        {
            if (ladder[h][col])
                col++;
            else if (ladder[h][col - 1])
                col--;
        }
        if (col != i)
            return false;
    }
    return true;
}

static bool dfs(int cur, int start_idx)
{
    if (cur == cnt)
    {
        return check();
    }

    for (int i = start_idx; i < (N - 1) * H; i++)
    {
        int r = i / (N - 1) + 1;
        int c = i % (N - 1) + 1;
        if (!ladder[r][c] && !ladder[r][c - 1] && !ladder[r][c + 1])
        {
            ladder[r][c] = true;
            if (dfs(cur + 1, i + 1))
                return true;
            ladder[r][c] = false;
        }
    }
    return false;
}

int main(void)
{
    if (scanf("%d %d %d", &N, &M, &H) != 3)
        return 1;

    for (int i = 0; i < M; i++)
    {
        int a, b;
        if (scanf("%d %d", &a, &b) != 2)
            return 1;
        ladder[a][b] = true;
    }

    for (cnt = 0; cnt <= 3; cnt++)
    {
        if ((M + cnt) & 1) // 모든 다리의 갯수가 짝수여야 함
            continue;

        if (dfs(0, 0))
        {
            printf("%d\n", cnt);
            return 0;
        }
    }
    printf("-1\n");
    return 0;
}