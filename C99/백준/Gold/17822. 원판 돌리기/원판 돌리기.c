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
static int N, M, T, sum, count;
static int **arr;
static int DIR[4][2] = {
    {0, 1},  // 오른쪽
    {1, 0},  // 아래
    {0, -1}, // 왼쪽
    {-1, 0}  // 위
};

static bool rotate(int row, int dir, int k)
{
    if (dir != 0 && dir != 1)
    {
        return false;
    }
    int *temp = malloc(M * sizeof(int));
    if (temp == NULL)
    {
        return false;
    }
    memcpy(temp, arr[row], M * sizeof(int));
    if (dir == 0) // 시계방향
    {
        for (int i = 0; i < M; i++)
        {
            arr[row][(i + k) % M] = temp[i];
        }
    }
    else // 반시계방향
    {
        for (int i = 0; i < M; i++)
        {
            arr[row][(i - k + M) % M] = temp[i];
        }
    }
    free(temp);
    return true;
}

static bool delete()
{
    bool flag = false;

    int (*queue)[2] = malloc(N * M * sizeof(int[2]));
    if (queue == NULL)
    {
        return false;
    }
    int front = 0, rear = 0;

    bool **visited = malloc(N * sizeof(bool *));
    if (visited == NULL)
    {
        free(queue);
        return false;
    }
    for (int i = 0; i < N; i++)
    {
        visited[i] = malloc(M * sizeof(bool));
        if (visited[i] == NULL)
        {
            for (int j = 0; j < i; j++)
            {
                free(visited[j]);
            }
            free(queue);
            free(visited);
            return false;
        }
        memset(visited[i], false, M * sizeof(bool));
    }

    // printf("Initial sum: %d, count: %d\n", sum, count);
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
    for (int i = 0; i < N; i++)
    {
        free(visited[i]);
    }
    free(queue);
    free(visited);
    return flag;
}

int main(void)
{
    if (scanf("%d %d %d", &N, &M, &T) != 3)
    {
        return 1;
    }
    arr = malloc(N * sizeof(int *));
    for (int i = 0; i < N; i++)
    {
        arr[i] = malloc(M * sizeof(int));
        if (arr[i] == NULL)
        {
            for (int j = 0; j < i; j++)
            {
                free(arr[j]);
            }
            free(arr);
            return 1;
        }
        for (int j = 0; j < M; j++)
        {
            if (scanf("%d", &arr[i][j]) != 1) // 시계방향으로 받음
            {
                for (int k = 0; k <= i; k++)
                {
                    free(arr[k]);
                }
                free(arr);
                return 1;
            }
            sum += arr[i][j];
            count++;
        }
    }

    for (int i = 0; i < T; i++)
    {
        int x, d, k;
        if (scanf("%d %d %d", &x, &d, &k) != 3)
        {
            for (int j = 0; j < N; j++)
            {
                free(arr[j]);
            }
            free(arr);
            return 1;
        }
        for (int j = x - 1; sum && j < N; j += x)
        {
            if (!rotate(j, d, k))
            {
                for (int l = 0; l < N; l++)
                {
                    free(arr[l]);
                }
                free(arr);
                return 1;
            }
        }
        // printf("After rotation %d:\n", i + 1);
        // for (int a = 0; a < N; a++)
        // {
        //     for (int b = 0; b < M; b++)
        //     {
        //         printf("%d ", arr[a][b]);
        //     }
        //     printf("\n");
        // }
        if (!delete())
        {
            double avg = (double)sum / count;
            // printf("Count: %d, Sum: %d\n", count, sum);
            // printf("No deletion, adjusting by average: %.2f\n", avg);
            for (int i = 0; i < N; i++)
            {
                for (int j = 0; j < M; j++)
                {
                    if (arr[i][j] == 0)
                        continue;
                    if ((double)arr[i][j] > avg)
                    {
                        arr[i][j]--;
                        sum--;
                    }
                    else if ((double)arr[i][j] < avg)
                    {
                        arr[i][j]++;
                        sum++;
                    }
                }
            }
        }
        // printf("Count: %d, Sum: %d\n", count, sum);
        // printf("After deletion %d:\n", i + 1);
        // for (int a = 0; a < N; a++)
        // {
        //     for (int b = 0; b < M; b++)
        //     {
        //         printf("%d ", arr[a][b]);
        //     }
        //     printf("\n");
        // }
        // printf("///////////////////////////////////////////////////\n");
    }
    printf("%d\n", sum);
    for (int i = 0; i < N; i++)
    {
        free(arr[i]);
    }
    free(arr);
    return 0;
}