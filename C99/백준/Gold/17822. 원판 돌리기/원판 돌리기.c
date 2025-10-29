#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <stdint.h>

/**
 * @description ?
 * @performance 메모리: 1,116 KB, 동작시간: 4 ms
 * @author java08
 */
static int N, M, T, sum, count;
static int **arr;
static int (*queue)[2];
static bool **visited;
static int DIR[4][2] = {
    {0, 1},  // 오른쪽
    {1, 0},  // 아래
    {0, -1}, // 왼쪽
    {-1, 0}  // 위
};

static void rotate(int row, int dir, int k)
{
    int *a = arr[row];
    int *temp = malloc(M * sizeof(int));
    memcpy(temp, a, M * sizeof(int));
    if (dir == 0) // 시계방향
    {
        for (int i = 0; i < M; i++)
        {
            a[(i + k) % M] = temp[i];
        }
    }
    else // 반시계방향
    {
        for (int i = 0; i < M; i++)
        {
            a[(i - k + M) % M] = temp[i];
        }
    }
    free(temp);
    return;
}

static bool delete()
{
    bool flag = false;
    int front = 0, rear = 0;
    for (int i = 0; i < N; i++)
    {
        memset(visited[i], false, M * sizeof(bool));
    }

    for (int i = 0; sum && i < N; i++)
    {
        for (int j = 0; sum && j < M; j++)
        {
            if (arr[i][j] == 0 || visited[i][j])
                continue;
            visited[i][j] = true;
            int target = arr[i][j];
            front = 0;
            rear = 0;
            queue[rear][0] = i;
            queue[rear][1] = j;
            rear++;
            arr[i][j] = 0;
            while (front < rear)
            {
                int ci = queue[front][0];
                int cj = queue[front][1];
                front++;
                for (int d = 0; d < 4; d++)
                {
                    int ni = ci + DIR[d][0];
                    int nj = (cj + DIR[d][1] + M) % M;
                    if (ni < 0 || ni >= N || arr[ni][nj] == 0 || visited[ni][nj])
                        continue;
                    if (arr[ni][nj] == target)
                    {
                        visited[ni][nj] = true;
                        queue[rear][0] = ni;
                        queue[rear][1] = nj;
                        rear++;
                        sum -= arr[ni][nj];
                        count--;
                        arr[ni][nj] = 0;
                        flag = true;
                    }
                }
            }
            if (front == 1)
            {
                arr[i][j] = target;
            }
            else
            {
                sum -= target;
                count--;
            }
        }
    }
    return flag;
}

int main(void)
{
    scanf("%d %d %d", &N, &M, &T);
    arr = malloc(N * sizeof(int *));
    queue = malloc(N * M * sizeof(int[2]));
    visited = malloc(N * sizeof(bool *));
    for (int i = 0; i < N; i++)
    {
        int *a = malloc(M * sizeof(int));
        visited[i] = malloc(M * sizeof(bool));
        for (int j = 0; j < M; j++)
        {
            scanf("%d", &a[j]); // 시계방향으로 받음
            sum += a[j];
            count++;
        }
        arr[i] = a;
    }

    for (int i = 0; i < T; i++)
    {
        int x, d, k;
        scanf("%d %d %d", &x, &d, &k);
        for (int j = x - 1; sum && j < N; j += x)
        {
            rotate(j, d, k);
        }

        if (!delete())
        {
            double avg = (double)sum / count;
            for (int i = 0; i < N; i++)
            {
                int *row = arr[i];
                for (int j = 0; j < M; j++)
                {
                    if (row[j] == 0)
                        continue;
                    if ((double)row[j] > avg)
                    {
                        row[j]--;
                        sum--;
                    }
                    else if ((double)row[j] < avg)
                    {
                        row[j]++;
                        sum++;
                    }
                }
            }
        }
    }
    printf("%d\n", sum);
    for (int i = 0; i < N; i++)
    {
        free(arr[i]);
        free(visited[i]);
    }
    free(arr);
    free(queue);
    free(visited);
    return 0;
}