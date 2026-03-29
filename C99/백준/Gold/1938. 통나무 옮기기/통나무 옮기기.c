#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <stdint.h>

/**
 * @description BFS 시뮬레이션
 * @performance 메모리: 1,112 KB, 동작시간: 0 ms
 * @author java08
 */

// 현재 상태에서 각 움직임을 수행할 수 있는지 확인하고, 큐에 넣는다.
// visited[N][N][2]를 통해 해당 좌표에서 가로/세로로 놓인 적 있는지 검사해서 가지치기를 한다.

int N, bCnt, eCnt;
int **map;
bool ***dp;
int b[3][2];
int e[3][2];
int B[3]; // r, c, d
int E[3];

typedef struct
{
    int r, c, d;
} pos;

bool up(int r, int c, int d)
{
    if (d == 0)
    {                  // 가로 방향
        if (r - 1 < 0) // 중심점을 기준으로 바로 윗 행만 고려하면 됨
            return false;
        if (map[r - 1][c - 1] != 0 || map[r - 1][c] != 0 || map[r - 1][c + 1] != 0)
            return false;
        return true;
    }
    else
    {                  // 세로 방향
        if (r - 2 < 0) // 중심점을 기준으로 2칸 위를 고려해야 함
            return false;
        if (map[r - 2][c] != 0)
            return false;
        return true;
    }
}

bool down(int r, int c, int d)
{
    if (d == 0)
    {                   // 가로 방향
        if (r + 1 >= N) // 중심점을 기준으로 바로 아래 행만 고려하면 됨
            return false;
        if (map[r + 1][c - 1] != 0 || map[r + 1][c] != 0 || map[r + 1][c + 1] != 0)
            return false;
        return true;
    }
    else
    {                   // 세로 방향
        if (r + 2 >= N) // 중심점을 기준으로 2칸 아래를 고려해야 함
            return false;
        if (map[r + 2][c] != 0)
            return false;
        return true;
    }
}

bool right(int r, int c, int d)
{
    if (d == 0)
    {                   // 가로 방향
        if (c + 2 >= N) // 중심점을 기준으로 2칸 오른쪽을 고려해야 함
            return false;
        if (map[r][c + 2] != 0)
            return false;
        return true;
    }
    else
    {                   // 세로 방향
        if (c + 1 >= N) // 중심점을 기준으로 1칸 오른쪽 고려해야 함
            return false;
        if (map[r - 1][c + 1] != 0 || map[r][c + 1] != 0 || map[r + 1][c + 1] != 0)
            return false;
        return true;
    }
}

bool left(int r, int c, int d)
{
    if (d == 0)
    {                  // 가로 방향
        if (c - 2 < 0) // 중심점을 기준으로 2칸 왼쪽을 고려해야 함
            return false;
        if (map[r][c - 2] != 0)
            return false;
        return true;
    }
    else
    {                  // 세로 방향
        if (c - 1 < 0) // 중심점을 기준으로 1칸 왼쪽 고려해야 함
            return false;
        if (map[r - 1][c - 1] != 0 || map[r][c - 1] != 0 || map[r + 1][c - 1] != 0)
            return false;
        return true;
    }
}

bool turn(int r, int c, int d)
{
    if (r - 1 < 0 || c - 1 < 0 || r + 1 >= N || c + 1 >= N)
        return false;
    for (int i = r - 1; i <= r + 1; i++)
    {
        for (int j = c - 1; j <= c + 1; j++)
        {
            if (map[i][j] != 0)
                return false;
        }
    }
    return true;
}

int main(void)
{
    // 입력 시작
    if (scanf("%d", &N) != 1)
        return 1;
    map = malloc(N * sizeof(int *));
    dp = malloc(N * sizeof(bool **));
    for (int i = 0; i < N; i++)
    {
        map[i] = malloc(N * sizeof(int));
        dp[i] = malloc(N * sizeof(bool *));
        for (int j = 0; j < N; j++)
        {
            dp[i][j] = calloc(2, sizeof(bool));
        }
    }

    char *in = malloc((N + 1) * sizeof(char));
    for (int i = 0; i < N; i++)
    {
        if (scanf("%s", in) != 1)
            return 1;
        for (int j = 0; j < N; j++)
        {
            if (in[j] == 'B')
            {
                b[bCnt][0] = i;
                b[bCnt][1] = j;
                bCnt++;
                map[i][j] = 0;
            }
            else if (in[j] == 'E')
            {
                e[eCnt][0] = i;
                e[eCnt][1] = j;
                eCnt++;
                map[i][j] = 0;
            }
            else
            {
                map[i][j] = in[j] - '0';
            }
        }
    }
    free(in);
    // 입력 끝

    B[0] = b[1][0];
    B[1] = b[1][1];
    if (b[0][0] == b[1][0]) // 행이 같으면 가로방향
    {
        B[2] = 0;
    }
    else
    {
        B[2] = 1;
    }

    E[0] = e[1][0];
    E[1] = e[1][1];
    if (e[0][0] == e[1][0]) // 행이 같으면 가로방향
    {
        E[2] = 0;
    }
    else
    {
        E[2] = 1;
    }

    pos q[10000];
    int front = 0, rear = 0;
    pos init = {B[0], B[1], B[2]};
    q[rear++] = init;
    dp[init.r][init.c][init.d] = true;
    int result = 0;
    bool flag = false;
    while (front < rear)
    {
        int cnt = rear - front;
        while (cnt--)
        {
            pos cur = q[front++];
            int curR = cur.r;
            int curC = cur.c;
            int curD = cur.d;
            if (curR == E[0] && curC == E[1] && curD == E[2])
            {
                flag = true;
                break;
            }
            int nextR, nextC, nextD;
            if (up(curR, curC, curD))
            {
                nextR = curR - 1;
                nextC = curC;
                nextD = curD;
                if (!dp[nextR][nextC][nextD])
                {
                    dp[nextR][nextC][nextD] = true;
                    pos next = {nextR, nextC, nextD};
                    q[rear++] = next;
                }
            }
            if (down(curR, curC, curD))
            {
                nextR = curR + 1;
                nextC = curC;
                nextD = curD;
                if (!dp[nextR][nextC][nextD])
                {
                    dp[nextR][nextC][nextD] = true;
                    pos next = {nextR, nextC, nextD};
                    q[rear++] = next;
                }
            }
            if (right(curR, curC, curD))
            {
                nextR = curR;
                nextC = curC + 1;
                nextD = curD;
                if (!dp[nextR][nextC][nextD])
                {
                    dp[nextR][nextC][nextD] = true;
                    pos next = {nextR, nextC, nextD};
                    q[rear++] = next;
                }
            }
            if (left(curR, curC, curD))
            {
                nextR = curR;
                nextC = curC - 1;
                nextD = curD;
                if (!dp[nextR][nextC][nextD])
                {
                    dp[nextR][nextC][nextD] = true;
                    pos next = {nextR, nextC, nextD};
                    q[rear++] = next;
                }
            }
            if (turn(curR, curC, curD))
            {
                nextR = curR;
                nextC = curC;
                nextD = curD ? 0 : 1;
                if (!dp[nextR][nextC][nextD])
                {
                    dp[nextR][nextC][nextD] = true;
                    pos next = {nextR, nextC, nextD};
                    q[rear++] = next;
                }
            }
        }
        if (flag)
            break;
        result++;
    }
    if (!flag)
        result = 0;
    printf("%d\n", result);
    for (int i = 0; i < N; i++)
    {
        for (int j = 0; j < N; j++)
        {
            free(dp[i][j]);
        }
        free(dp[i]);
        free(map[i]);
    }
    free(dp);
    free(map);
    return 0;
}