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

int main(void)
{
    int list[5] = {-1, 1, -10, 10, 60};
    int T, N, temp;

    if (scanf("%d", &T) != 1)
    {
        return 1;
    }
    int (*visited)[6] = malloc(61 * sizeof(int[6]));
    while (T--)
    {
        if (scanf("%d", &N) != 1)
        {
            free(visited);
            return 1;
        }
        temp = N / 60;
        N %= 60;
        if (!N)
        {
            printf("%d 0 0 0 0\n", temp);
            continue;
        }

        for (int i = 0; i < 61; i++)
        {
            for (int j = 0; j < 6; j++)
            {
                visited[i][j] = 0;
            }
        }

        int q[3601];
        int front = 0, rear = 0;
        q[rear++] = 0;
        bool flag = false;

        while (!flag && front < rear)
        {
            int cnt = rear - front;
            while (cnt--)
            {
                int cur = q[front++];
                if (cur == N)
                {
                    flag = true;
                    break;
                }
                for (int i = 0; i < 5; i++)
                {
                    int next = cur + list[i];
                    if (next > 60 || next <= 0)
                        continue;
                    if (visited[next][5])
                        continue;
                    for (int j = 0; j < 6; j++)
                    {
                        visited[next][j] = visited[cur][j];
                    }
                    visited[next][i]++;
                    visited[next][5]++;
                    q[rear++] = next;
                }
            }
        }
        printf("%d %d %d %d %d\n", visited[N][4] + temp, visited[N][3], visited[N][2], visited[N][1], visited[N][0]);
    }
    free(visited);
    return 0;
}