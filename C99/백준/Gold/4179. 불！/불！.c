#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <stdint.h>

/**
 * @description 시뮬레이션
 * @performance 메모리: 1,112 KB, 동작시간: 0 ms
 * @author java08
 */

// 불의 움직임을 미리 캐싱, 지훈의 움직임은 BFS로 이동횟수 추적.
int q[1000000][2];
void fire(int R, int C, int **map, int cnt)
{
    int DIR[4][2] = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    int front = 0, rear = cnt;
    while (front < rear)
    {
        int curR = q[front][0];
        int curC = q[front][1];
        front++;
        for (int i = 0; i < 4; i++)
        {
            int nextR = curR + DIR[i][0];
            int nextC = curC + DIR[i][1];
            if (nextR < 0 || nextC < 0 || nextR >= R || nextC >= C)
                continue;
            if (map[nextR][nextC] > map[curR][curC] + 1)
            {
                map[nextR][nextC] = map[curR][curC] + 1;
                q[rear][0] = nextR;
                q[rear][1] = nextC;
                rear++;
            }
        }
    }
}

int run(int R, int C, int **map, int *pos)
{
    int DIR[4][2] = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    int **visited = malloc(R * sizeof(int *));
    for (int i = 0; i < R; i++)
    {
        visited[i] = calloc(C, sizeof(int));
    }

    int front = 0, rear = 0;
    visited[pos[0]][pos[1]] = 1;
    q[rear][0] = pos[0];
    q[rear][1] = pos[1];
    rear++;

    while (front < rear)
    {
        int curR = q[front][0];
        int curC = q[front][1];
        front++;
        for (int i = 0; i < 4; i++)
        {
            int nextR = curR + DIR[i][0];
            int nextC = curC + DIR[i][1];
            if (nextR < 0 || nextC < 0 || nextR >= R || nextC >= C)
            {
                int result = visited[curR][curC];
                for (int j = 0; j < R; j++)
                {
                    free(visited[j]);
                }
                free(visited);
                return result;
            }
            if (visited[nextR][nextC])
                continue;
            visited[nextR][nextC] = visited[curR][curC] + 1;
            if (map[nextR][nextC] == -1 || map[nextR][nextC] <= visited[nextR][nextC])
                continue;

            q[rear][0] = nextR;
            q[rear][1] = nextC;
            rear++;
        }
    }

    for (int i = 0; i < R; i++)
    {
        free(visited[i]);
    }
    free(visited);
    return 0;
}

int main(void)
{
    // 입력
    int R, C;
    if (scanf("%d %d", &R, &C) != 2)
        return 1;

    int **map = malloc(R * sizeof(int *));
    char *input = malloc(C + 1);
    int J[2];
    int fcnt = 0;
    for (int i = 0; i < R; i++)
    {
        map[i] = malloc(C * sizeof(int));
        if (scanf("%s", input) != 1)
            return 1;
        for (int j = 0; j < C; j++)
        {
            if (input[j] == '#')
            {
                map[i][j] = -1;
                continue;
            }
            else if (input[j] == '.')
            {
                map[i][j] = 1000000;
            }
            else if (input[j] == 'J')
            {
                map[i][j] = 1000000;
                J[0] = i;
                J[1] = j;
            }
            else if (input[j] == 'F')
            {
                map[i][j] = 1;
                q[fcnt][0] = i;
                q[fcnt][1] = j;
                fcnt++;
            }
        }
    }
    free(input);

    // printf("F: %d %d, J: %d %d\n", F[0], F[1], J[0], J[1]);
    // for (int i = 0; i < R; i++)
    // {
    //     for (int j = 0; j < C; j++)
    //     {
    //         printf("%d ", map[i][j]);
    //     }
    //     printf("\n");
    // }
    // printf("#########################\n");

    // 동작
    fire(R, C, map, fcnt);
    // for (int i = 0; i < R; i++)
    // {
    //     for (int j = 0; j < C; j++)
    //     {
    //         printf("%d ", map[i][j]);
    //     }
    //     printf("\n");
    // }
    // printf("#########################\n");
    int result = run(R, C, map, J);

    // 출력
    if (result)
    {
        printf("%d\n", result);
    }
    else
    {
        printf("IMPOSSIBLE\n");
    }

    // 메모리 해제
    for (int i = 0; i < R; i++)
    {
        free(map[i]);
    }
    free(map);
    return 0;
}
