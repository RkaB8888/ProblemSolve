#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <stdint.h>

/**
 * @description BFS + 외판원
 * @performance 메모리: 1,112 KB, 동작시간: 0 ms
 * @author java08
 */
#define MAX_W 20
#define MAX_H 20
#define MAX_K 10
#define INF 0x3f3f3f3f

static int W, H, k, dirty_count, move_count;
static int dirty_positions[MAX_K + 1][2];
static int DIR[4][2] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 상하좌우
static char map[MAX_H][MAX_W + 1];
static int dp[MAX_K + 1][MAX_K + 1];
static int queue[MAX_H * MAX_W][2];

static void bfs(int sr, int sc, int dist_map[MAX_H][MAX_W])
{
    for (int i = 0; i < H; i++)
    {
        for (int j = 0; j < W; j++)
        {
            dist_map[i][j] = -1;
        }
    }
    int front = 0, rear = 0;
    queue[rear][0] = sr;
    queue[rear][1] = sc;
    rear++;
    dist_map[sr][sc] = 0;
    while (front < rear)
    {
        int r = queue[front][0];
        int c = queue[front][1];
        front++;
        for (int i = 0; i < 4; i++)
        {
            int nr = r + DIR[i][0];
            int nc = c + DIR[i][1];
            if (nr < 0 || nr >= H || nc < 0 || nc >= W)
                continue;
            if (map[nr][nc] == 'x')
                continue;
            if (dist_map[nr][nc] != -1)
                continue;
            dist_map[nr][nc] = dist_map[r][c] + 1;
            queue[rear][0] = nr;
            queue[rear][1] = nc;
            rear++;
        }
    }
    return;
}

int main(void)
{
    while (1)
    {
        if (scanf("%d %d", &W, &H) != 2)
            return 0;
        if (W == 0 && H == 0)
            break;

        int sr = -1, sc = -1;
        k = 0, dirty_count = 1;
        for (int i = 0; i < H; i++)
        {
            if (scanf("%s", map[i]) != 1)
            {
                return 1;
            }
            for (int j = 0; j < W; j++)
            {
                if (map[i][j] == 'o')
                {
                    sr = i;
                    sc = j;
                    dirty_positions[0][0] = sr;
                    dirty_positions[0][1] = sc;
                }
                else if (map[i][j] == '*')
                {
                    dirty_positions[dirty_count][0] = i;
                    dirty_positions[dirty_count][1] = j;
                    dirty_count++;
                }
            }
        }
        int dist_map[MAX_H][MAX_W]; // BFS 거리 맵
        for (int i = 0; i < dirty_count; i++)
        {
            bfs(dirty_positions[i][0], dirty_positions[i][1], dist_map);
            for (int j = 0; j < dirty_count; j++)
            {
                dp[i][j] = dist_map[dirty_positions[j][0]][dirty_positions[j][1]];
            }
        }

        bool flag = false;
        for (int i = 0; i < dirty_count; i++)
        {
            if (dp[0][i] == -1)
            {
                flag = true;
                break;
            }
        }
        if (flag)
        {
            printf("-1\n");
            continue;
        }
        if (dirty_count == 1)
        {
            printf("0\n");
            continue;
        }

        int CAP = (1 << (dirty_count - 1));
        int dp2[1 << MAX_K][MAX_K + 1];
        for (int i = 0; i < CAP; i++)
        {
            for (int j = 0; j < dirty_count; j++)
            {
                dp2[i][j] = INF;
            }
        }
        for (int i = 1; i < dirty_count; i++)
        {
            dp2[1 << (i - 1)][i] = dp[0][i];
        }
        for (int mask = 0; mask < CAP; mask++)
        {
            for (int i = 1; i < dirty_count; i++)
            {
                int cur = dp2[mask][i];
                if (cur == INF)
                    continue;
                for (int j = 1; j < dirty_count; j++)
                {
                    if (mask & (1 << (j - 1)))
                        continue;
                    int next_mask = mask | (1 << (j - 1));
                    int next_dist = cur + dp[i][j];
                    if (dp2[next_mask][j] > next_dist)
                    {
                        dp2[next_mask][j] = next_dist;
                    }
                }
            }
        }
        move_count = INF;
        for (int i = 1; i < dirty_count; i++)
        {
            if (move_count > dp2[CAP - 1][i])
            {
                move_count = dp2[CAP - 1][i];
            }
        }
        printf("%d\n", move_count);
    }
    return 0;
}