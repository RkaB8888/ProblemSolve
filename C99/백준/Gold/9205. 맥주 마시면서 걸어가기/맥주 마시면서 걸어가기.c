#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <stdint.h>

/**
 * @description DFS
 * @performance 메모리: 1,112 KB, 동작시간: 0 ms
 * @author java08
 */

// 20병으로 갈 수 있는 최대 거리: 1000미터
// DFS로 각 편의점 지점을 거쳐가며 목적지까지 도착할 수 있는지 확인

static inline int code(int x, int y)
{
    return (x << 16) | (y & 0xFFFF);
}

static inline void decode(int target, int *x, int *y)
{
    *x = target >> 16;
    *y = (target << 16) >> 16;
}

static inline int get_dist(int a, int b)
{
    return a > b ? a - b : b - a;
}

int main(void)
{
    int t;
    if (scanf("%d", &t) != 1)
        return 1;
    while (t-- > 0)
    {
        int n;
        if (scanf("%d", &n) != 1)
            return 1;
        int node[101];
        int x, y;
        if (scanf("%d %d", &x, &y) != 2)
            return 1;
        node[0] = code(x, y);
        for (int i = 1; i <= n; i++)
        {
            if (scanf("%d %d", &x, &y) != 2)
                return 1;
            node[i] = code(x, y);
        }
        if (scanf("%d %d", &x, &y) != 2)
            return 1;
        bool visited[101] = {0};
        int stack[101];
        int top = 0;
        stack[top++] = 0;
        visited[0] = true;

        bool flag = false;
        while (top)
        {
            int curIdx = stack[--top];
            int curX, curY;
            decode(node[curIdx], &curX, &curY);
            int distG = get_dist(curX, x) + get_dist(curY, y);
            if (distG <= 1000)
            {
                flag = true;
                break;
            }

            for (int i = 1; i <= n; i++)
            {
                if (visited[i])
                    continue;
                int nextX, nextY;
                decode(node[i], &nextX, &nextY);
                int dist = get_dist(curX, nextX) + get_dist(curY, nextY);
                if (dist <= 1000)
                {
                    visited[i] = true;
                    stack[top++] = i;
                }
            }
        }
        if (flag)
            printf("happy\n");
        else
            printf("sad\n");
    }
    return 0;
}