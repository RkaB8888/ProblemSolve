#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <stdint.h>

/**
 * @description 다익스트라
 * @performance 메모리: 1,112 KB, 동작시간: 0 ms
 * @author java08
 */

// N은 4000 이하, M은 100000 이하
// a와 b는 N 이하, d는 100000 이하

#define MAX_HEAP 2000000
#define MAX 2000000000

typedef struct
{
    int node;
    int dist;
    int state; // 0: 여우, 1: 빠른 늑대, 2: 느린 늑대
} State;

State heapq[MAX_HEAP];
int q_size = 0;
int state_arr[3] = {0, 2, 1};
int dist_mux[3] = {2, 1, 4};

void push(int node, int dist, int state)
{
    int i = ++q_size;
    while (i > 1 && heapq[i >> 1].dist > dist) // 부모보다 작으면 교체
    {
        heapq[i] = heapq[i >> 1];
        i >>= 1;
    }
    heapq[i] = (State){node, dist, state};
}

State get()
{
    State min = heapq[1];
    State last = heapq[q_size--];

    int i = 1;
    int child = 2;

    while (child <= q_size)
    {
        if (child < q_size && heapq[child].dist > heapq[child + 1].dist)
        {
            child++;
        }
        if (last.dist <= heapq[child].dist)
        {
            break;
        }
        heapq[i] = heapq[child];
        i = child;
        child = i << 1;
    }
    heapq[i] = last;
    return min;
}

int main(void)
{
    int N, M;
    if (scanf("%d %d", &N, &M) != 2)
        return 1;
    N++;
    int *next, *link, *node, *val;
    next = malloc(N * sizeof(int));
    memset(next, -1, N * sizeof(int));
    link = malloc((M << 1) * sizeof(int));
    node = malloc((M << 1) * sizeof(int));
    val = malloc((M << 1) * sizeof(int));
    for (int i = 0; i < M; i++)
    {
        int a, b, d;
        if (scanf("%d %d %d", &a, &b, &d) != 3)
        {
            free(val);
            free(node);
            free(link);
            free(next);
            return 1;
        }
        int idx = i << 1;

        link[idx] = next[a];
        next[a] = idx;
        node[idx] = b;
        val[idx] = d;

        link[idx + 1] = next[b];
        next[b] = idx + 1;
        node[idx + 1] = a;
        val[idx + 1] = d;
    }

    int (*visited)[3] = malloc(N * sizeof(int[3]));
    for (int i = 0; i < N; i++)
    {
        visited[i][0] = MAX;
        visited[i][1] = MAX;
        visited[i][2] = MAX;
    }
    visited[1][0] = 0; // 여우
    visited[1][2] = 0; // 늑대(느리게 왔음)
    push(1, 0, 0);
    push(1, 0, 2);
    while (q_size)
    {
        State cur = get();

        if (visited[cur.node][cur.state] < cur.dist)
            continue;
        for (int l = next[cur.node]; l != -1; l = link[l])
        {
            int next_node = node[l];
            int next_state = state_arr[cur.state];
            int next_val = val[l] * dist_mux[next_state];
            int next_dist = cur.dist + next_val;
            if (visited[next_node][next_state] > next_dist)
            {
                visited[next_node][next_state] = next_dist;
                push(next_node, next_dist, next_state);
            }
        }
    }
    int result = 0;
    // for (int i = 1; i < N; i++)
    // {
    //     printf("%d번 %d %d %d\n", i, visited[i][0], visited[i][1], visited[i][2]);
    // }
    for (int i = 2; i < N; i++)
    {
        if (visited[i][0] < visited[i][1] && visited[i][0] < visited[i][2])
        {
            result++;
        }
    }
    printf("%d\n", result);
    free(visited);
    free(val);
    free(node);
    free(link);
    free(next);
    return 0;
}