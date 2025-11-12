#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <stdint.h>

/**
 * @description Trie + DFS + 정렬(qsort)
 * @performance 메모리: 1,112 KB, 동작시간: 0 ms
 * @author java08
 */

#define NAME_LEN 16
#define MAX_N 1000
#define MAX_K 15
#define MAX_NODES (MAX_N * MAX_K + 1)
#define MAX_EDGES (MAX_N * MAX_K)

static int link[MAX_NODES];
static int next[MAX_EDGES];
static int node[MAX_EDGES];

static char val[MAX_NODES][NAME_LEN];

static int N; // [1,1000]
static int node_top = 1;
static int edge_top = 0;

static inline void set_val(int u, const char *s)
{
    snprintf(val[u], NAME_LEN, "%s", s);
}

static inline int new_node(const char *name)
{
    int u = node_top++;
    set_val(u, name);
    link[u] = -1;
    return u;
}

static inline void add_edge(int u, int v)
{
    int e = edge_top++;
    node[e] = v;
    next[e] = link[u];
    link[u] = e;
}

static int get_or_create_child(int u, const char *name)
{
    for (int e = link[u]; e != -1; e = next[e])
    {
        int v = node[e];
        if (val[v][0] && strcmp(val[v], name) == 0)
            return v;
    }
    int v = new_node(name);
    add_edge(u, v);
    return v;
}

static int cmp(const void *a, const void *b) // 간선에서 얻은 노드 번호
{
    int aa = *(const int *)a;
    int bb = *(const int *)b;

    return strcmp(val[aa], val[bb]);
}

static void dfs(int u, int depth)
{
    if (u != 0)
    {
        for (int i = 1; i < depth; i++)
            printf("--");
        printf("%s\n", val[u]);
    }

    int cnt = 0;
    for (int e = link[u]; e != -1; e = next[e])
        cnt++;
    if (cnt == 0)
        return;

    int *child = malloc(cnt * sizeof(int));
    int i = 0;
    for (int e = link[u]; e != -1; e = next[e])
        child[i++] = node[e];
    qsort(child, cnt, sizeof(int), cmp);

    for (int k = 0; k < cnt; k++)
        dfs(child[k], depth + 1);

    free(child);
}

int main(void)
{
    if (scanf("%d", &N) != 1)
        return 1;

    val[0][0] = '\0';
    link[0] = -1;

    for (int i = 0; i < N; i++)
    {
        int K;
        if (scanf("%d", &K) != 1)
            return 1;

        int cur = 0;
        for (int j = 0; j < K; j++)
        {
            char name[NAME_LEN]; // 대문자로만 구성
            if (scanf("%15s", name) != 1)
                return 1;

            cur = get_or_create_child(cur, name);
        }
    }
    dfs(0, 0);
    return 0;
}