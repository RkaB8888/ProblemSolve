#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <stdint.h>

/**
 * @description Prim + Binary Heap(Decrease-Key) + Adjacency List
 * @performance 메모리: 3,636 KB, 동작시간: 40 ms
 * @author java08
 */

#define MAX 1000001

static int V, E;
static int q_size;
static int *link, *next, *val, *node;
static int *vertex_q;
static int *pos_in_heap;
static int *min_cost;
static bool *visited;

static inline int fast_i()
{
    int s = 1, c, n;
    while ((n = getchar()) <= 32)
        ;
    if (n == '-')
    {
        s = -1;
        n = getchar();
    }
    n &= 15;
    while ((c = getchar()) > 32)
    {
        n = (n << 3) + (n << 1) + (c & 15);
    }
    return s * n;
}

static inline void heap_swap(int i, int j)
{
    int vi = vertex_q[i], vj = vertex_q[j];
    vertex_q[i] = vj;
    vertex_q[j] = vi;
    pos_in_heap[vi] = j;
    pos_in_heap[vj] = i;
}

static void heap_bubble_up(int i)
{
    while (i > 1)
    {
        int p = i >> 1;
        if (min_cost[vertex_q[p]] <= min_cost[vertex_q[i]])
            break;

        heap_swap(i, p);
        i = p;
    }
}

static void heap_bubble_down(int i)
{
    while (1)
    {
        int l = i << 1, r = l + 1, s = i;
        if (l <= q_size && min_cost[vertex_q[l]] < min_cost[vertex_q[s]])
            s = l;
        if (r <= q_size && min_cost[vertex_q[r]] < min_cost[vertex_q[s]])
            s = r;
        if (s == i)
            break;
        heap_swap(i, s);
        i = s;
    }
}

static void MHQ_push(int v) // edge의 가중치가 낮은 것이 위로
{
    if (pos_in_heap[v] != -1)
        return;
    vertex_q[++q_size] = v;
    pos_in_heap[v] = q_size;
    heap_bubble_up(q_size);
}

static void MHQ_decrease_key(int v)
{
    int i = pos_in_heap[v];
    if (i == -1)
    {
        MHQ_push(v);
        return;
    }
    heap_bubble_up(i);
}

static int MHQ_pop()
{
    if (q_size == 0)
        return -1;
    int top = vertex_q[1];
    pos_in_heap[top] = -1;

    vertex_q[1] = vertex_q[q_size];
    if (q_size >= 1)
        pos_in_heap[vertex_q[1]] = 1;
    q_size--;

    if (q_size > 1)
        heap_bubble_down(1);

    return top;
}

int main(void)
{
    V = fast_i();
    E = fast_i();

    link = malloc((V + 1) * sizeof(int));
    next = malloc((E << 1) * sizeof(int));
    val = malloc((E << 1) * sizeof(int));
    node = malloc((E << 1) * sizeof(int));
    visited = malloc((V + 1) * sizeof(bool));
    memset(visited, 0, (V + 1) * sizeof(bool));
    min_cost = malloc((V + 1) * sizeof(int));
    vertex_q = malloc((V + 1) * sizeof(int));
    pos_in_heap = malloc((V + 1) * sizeof(int));

    for (int i = 0; i <= V; i++)
    {
        link[i] = -1;
        min_cost[i] = MAX;
        pos_in_heap[i] = -1;
    }

    for (int i = 0; i < E; i++)
    {
        int a = fast_i(), b = fast_i(), c = fast_i();

        int e1 = i << 1;
        int e2 = e1 + 1;

        next[e1] = link[a];
        link[a] = e1;
        node[e1] = b;
        val[e1] = c;

        next[e2] = link[b];
        link[b] = e2;
        node[e2] = a;
        val[e2] = c;
    }

    int result = 0;
    int picked = 0;

    min_cost[1] = 0;
    MHQ_push(1);

    while (q_size > 0 && picked < V)
    {
        int u = MHQ_pop();
        if (u == -1)
            break;
        visited[u] = true;
        result += min_cost[u];
        picked++;

        for (int e = link[u]; e >= 0; e = next[e])
        {
            int v = node[e];
            int w = val[e];
            if (!visited[v] && w < min_cost[v])
            {
                min_cost[v] = w;
                if (pos_in_heap[v] == -1)
                    MHQ_push(v);
                else
                    MHQ_decrease_key(v);
            }
        }
    }

    printf("%d\n", result);

    free(link);
    free(next);
    free(val);
    free(node);
    free(visited);
    free(min_cost);
    free(vertex_q);
    free(pos_in_heap);
    return 0;
}