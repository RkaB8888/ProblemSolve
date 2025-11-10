#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <stdint.h>

/**
 * @description BFS + DFS 시간초과
 * @performance 메모리: 1,112 KB, 동작시간: 0 ms
 * @author java08
 */

static int N;               // 100이하 자연수
static int map[100][100];   // 0은 바다, 1은 육지, 항상 2개 이상의 섬 존재
static int dist[100][100];  // 파도가 지나간 거리
static int owner[100][100]; // 해당 파도가 시작한 그룹

static int DIR[4][2] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
static int gn = 2, ans = 200;

static void floodfill(int r, int c)
{
    int q[10000][2];
    int front = 0, rear = 0;

    q[rear][0] = r;
    q[rear][1] = c;
    rear++;
    map[r][c] = gn;

    while (front < rear)
    {
        int cr = q[front][0];
        int cc = q[front][1];
        front++;

        for (int i = 0; i < 4; i++)
        {
            int nr = cr + DIR[i][0];
            int nc = cc + DIR[i][1];
            if (nr < 0 || nr >= N || nc < 0 || nc >= N)
                continue;
            if (map[nr][nc] == 1)
            {
                q[rear][0] = nr;
                q[rear][1] = nc;
                rear++;
                map[nr][nc] = gn;
            }
        }
    }
}

int main(void)
{
    if (scanf("%d", &N) != 1)
        return 1;
    for (int i = 0; i < N; i++)
    {
        for (int j = 0; j < N; j++)
        {
            if (scanf("%d", &map[i][j]) != 1)
                return 1;
        }
    }

    for (int i = 0; i < N; i++)
    {
        for (int j = 0; j < N; j++)
        {
            if (map[i][j] == 1)
            {
                floodfill(i, j);
                gn++;
            }
        }
    }

    int q[10000][2] = {0};
    int front = 0, rear = 0;
    for (int i = 0; i < N; i++)
    {
        for (int j = 0; j < N; j++)
        {
            if (map[i][j] >= 2)
            {
                owner[i][j] = map[i][j];
                dist[i][j] = 0;
                q[rear][0] = i;
                q[rear][1] = j;
                rear++;
            }
            else
            {
                owner[i][j] = -1;
                dist[i][j] = -1;
            }
        }
    }

    while (front < rear)
    {
        int cr = q[front][0];
        int cc = q[front][1];
        front++;
        for (int d = 0; d < 4; ++d)
        {
            int nr = cr + DIR[d][0];
            int nc = cc + DIR[d][1];
            if (nr < 0 || nr >= N || nc < 0 || nc >= N)
                continue;

            if (owner[nr][nc] == -1)
            {
                owner[nr][nc] = owner[cr][cc];
                dist[nr][nc] = dist[cr][cc] + 1;
                q[rear][0] = nr;
                q[rear][1] = nc;
                rear++;
            }
            else
            {
                if (owner[nr][nc] != owner[cr][cc])
                {
                    int calc = dist[nr][nc] + dist[cr][cc];
                    if (calc < ans)
                        ans = calc;
                }
            }
        }
    }
    printf("%d\n", ans);
    return 0;
}