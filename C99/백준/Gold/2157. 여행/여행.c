#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <stdint.h>

/**
 * @description BFS + DP
 * @performance 메모리: 1,112 KB, 동작시간: 0 ms
 * @author java08
 */

// 최대 M번만에 1에서 N지점으로 이동, 최대 비용 출력
// 이동 방향은 무조건 커지는 방향
// N은 최대 300, M은 최대 N, K는 최대 100,000이니 인접 행렬도 괜찮을 듯?

#define Q_SIZE 0b100000000000000000
#define Q_MASK 0b011111111111111111

int N, M, K;
int **adjMap;
int **visited;

int main(void)
{
    if (scanf("%d %d %d", &N, &M, &K) != 3)
        return 1;

    if (N == 1)
    {
        printf("0\n");
        return 0;
    }

    adjMap = malloc((N + 1) * sizeof(int *));
    for (int i = 0; i <= N; i++)
    {
        adjMap[i] = calloc(N + 1, sizeof(int));
    }

    for (int i = 0; i < K; i++)
    {
        int a, b, c;
        if (scanf("%d %d %d", &a, &b, &c) != 3)
        {
            for (int i = 0; i < N; i++)
            {
                free(adjMap[i]);
            }
            free(adjMap);
            return 1;
        }
        if (a >= b)
            continue;
        if (adjMap[a][b] < c)
            adjMap[a][b] = c;
    }

    visited = malloc((N + 1) * sizeof(int *));
    for (int i = 0; i <= N; i++)
    {
        visited[i] = calloc((M + 1), sizeof(int));
    }

    int q[Q_SIZE][2]; // 현재 위치, 지금까지 비용
    int front = 0, rear = 0;
    visited[1][1] = 0;
    q[rear][0] = 1;
    q[rear++][1] = 0;

    int mov = 1;
    while (front < rear)
    {
        mov++;
        if (mov > M)
            break;
        int cnt = rear - front;
        while (cnt-- > 0)
        {
            int curN = q[front & Q_MASK][0];
            int curV = q[front & Q_MASK][1];
            front++;
            if (curV < visited[curN][mov - 1])
                continue;

            for (int i = curN + 1; i <= N; i++)
            {
                if (adjMap[curN][i] != 0) // 길이 있는지 체크
                {
                    int nextV = curV + adjMap[curN][i];
                    if (visited[i][mov] < nextV) // 해당 이동횟수에 더 높은 비용이면 갱신
                    {
                        visited[i][mov] = nextV;
                        if (i != N) // 마지막 이동인지 체크
                        {
                            q[rear & Q_MASK][0] = i;
                            q[rear & Q_MASK][1] = nextV;
                            rear++;
                        }
                    }
                }
            }
        }
    }
    int result = 0;
    for (int i = 0; i <= M; i++)
    {
        if (result < visited[N][i])
        {
            result = visited[N][i];
        }
    }

    printf("%d\n", result);

    for (int i = 0; i <= N; i++)
    {
        free(visited[i]);
        free(adjMap[i]);
    }
    free(visited);
    free(adjMap);
    return 0;
}