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
static int *link, *next, *v;

int cmp(const void *a, const void *b)
{
    return *(const int *)a - *(const int *)b;
}

static void dfs(int start)
{
    bool visited[1001] = {
        false,
    };
    int arr[1001];
    int stack[10001];
    int top = 0;
    bool first = true;

    stack[top++] = start;
    while (top > 0)
    {
        int curN = stack[--top];
        if (visited[curN])
            continue;
        if (!first)
            printf(" ");
        printf("%d", curN);
        first = false;
        visited[curN] = true;
        int arrLen = 0;
        for (int e = link[curN]; e != -1; e = next[e])
            if (!visited[v[e]])
                arr[arrLen++] = v[e];
        qsort(arr, arrLen, sizeof(int), cmp);
        for (int i = arrLen - 1; i >= 0; i--)
            stack[top++] = arr[i];
    }
    printf("\n");
}

static void bfs(int start)
{
    bool visited[1001] = {
        false,
    };
    int arr[1001];
    int queue[10001];
    int front = 0;
    int rear = 0;
    bool first = true;

    queue[rear++] = start;
    while (front < rear)
    {
        int curN = queue[front++];
        if (visited[curN])
            continue;
        if (!first)
            printf(" ");
        first = false;
        printf("%d", curN);
        visited[curN] = true;
        int arrLen = 0;
        for (int e = link[curN]; e != -1; e = next[e])
            if (!visited[v[e]])
                arr[arrLen++] = v[e];
        qsort(arr, arrLen, sizeof(int), cmp);
        for (int i = 0; i < arrLen; i++)
            queue[rear++] = arr[i];
    }
    printf("\n");
}

int main(void)
{
    int N, M, V;
    if (scanf("%d %d %d", &N, &M, &V) != 3)
        return 1;
    link = malloc((N + 1) * sizeof(int));
    memset(link, -1, (N + 1) * sizeof(int));
    next = malloc((M * 2) * sizeof(int));
    memset(next, -1, (M * 2) * sizeof(int));
    v = malloc((M * 2) * sizeof(int));
    memset(v, -1, (M * 2) * sizeof(int));
    for (int i = 0; i < M; i++)
    {
        int from, to;
        if (scanf("%d %d", &from, &to) != 2)
            return 1;
        next[i << 1] = link[from];
        link[from] = i << 1;
        v[i << 1] = to;

        next[(i << 1) + 1] = link[to];
        link[to] = (i << 1) + 1;
        v[(i << 1) + 1] = from;
    }

    dfs(V);
    bfs(V);

    return 0;
}