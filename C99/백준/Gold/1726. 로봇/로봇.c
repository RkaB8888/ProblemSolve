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

typedef struct
{
    int x, y, dir;
} Robot;

static int inline init_dir(int dir)
{
    switch (dir)
    {
    case 1: // 동
        return 0;
    case 2: // 서
        return 2;
    case 3: // 남
        return 1;
    case 4: // 북
        return 3;
    default:
        return -1; // 잘못된 방향
    }
}

int main(void)
{
    int N, M;
    if (scanf("%d %d", &N, &M) != 2)
    {
        return 1;
    }
    int map[N][M];
    for (int i = 0; i < N; i++)
    {
        for (int j = 0; j < M; j++)
        {
            if (scanf("%1d", &map[i][j]) != 1)
            {
                return 1;
            }
        }
    }
    int robot_start_x, robot_start_y, robot_start_direction;
    if (scanf("%d %d %d", &robot_start_x, &robot_start_y, &robot_start_direction) != 3)
    {
        return 1;
    }
    robot_start_x--;
    robot_start_y--;
    robot_start_direction = init_dir(robot_start_direction);
    int robot_end_x, robot_end_y, robot_end_direction;
    if (scanf("%d %d %d", &robot_end_x, &robot_end_y, &robot_end_direction) != 3)
    {
        return 1;
    }
    robot_end_x--;
    robot_end_y--;
    robot_end_direction = init_dir(robot_end_direction);

    // 로봇의 x, y, dir를 저장하는 큐
    int CAP = 1;
    while (CAP < (N + 1) * (M + 1) * 4)
    {
        CAP <<= 1;
    }
    Robot queue[CAP];
    int MAX = CAP - 1;
    bool visited[N][M][4];
    memset(visited, false, sizeof(visited));

    int front = 0, rear = 0;
    queue[rear++] = (Robot){robot_start_x, robot_start_y, robot_start_direction};
    visited[robot_start_x][robot_start_y][robot_start_direction] = true;
    int steps = 0;
    int dir_moves[4][2] = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 동, 남, 서, 북

    while (front != rear)
    {
        int size = (rear - front + CAP) & MAX;
        while (size-- > 0)
        {
            Robot current = queue[front++];
            front &= MAX;
            if (current.x == robot_end_x && current.y == robot_end_y && current.dir == robot_end_direction)
            {
                printf("%d\n", steps);
                break;
            }
            // 회전 (좌, 우)
            int new_dir = (current.dir + 4 - 1) % 4;
            if (!visited[current.x][current.y][new_dir])
            {
                visited[current.x][current.y][new_dir] = true;
                queue[rear++] = (Robot){current.x, current.y, new_dir};
                rear &= MAX;
            }
            new_dir = (current.dir + 1) % 4;
            if (!visited[current.x][current.y][new_dir])
            {
                visited[current.x][current.y][new_dir] = true;
                queue[rear++] = (Robot){current.x, current.y, new_dir};
                rear &= MAX;
            }

            // 전진 (1~3)
            for (int step = 1; step <= 3; step++)
            {
                int nx = current.x + dir_moves[current.dir][0] * step;
                int ny = current.y + dir_moves[current.dir][1] * step;
                if (nx < 0 || nx >= N || ny < 0 || ny >= M || map[nx][ny] == 1)
                {
                    break; // 벽이나 맵 밖에 도달하면 중지
                }
                if (!visited[nx][ny][current.dir])
                {
                    visited[nx][ny][current.dir] = true;
                    queue[rear++] = (Robot){nx, ny, current.dir};
                    rear &= MAX;
                }
            }
        }
        steps++;
    }
    return 0;
}