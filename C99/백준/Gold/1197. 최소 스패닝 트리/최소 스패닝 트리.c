#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <stdint.h>

/**
 * @description MST 크루스칼
 * @performance 메모리: 1,112 KB, 동작시간: 0 ms
 * @author java08
 */

static int V, E;
static int *group;

static int cmp(const void *a, const void *b)
{
    return ((int *)a)[0] - ((int *)b)[0];
}

static void union_init()
{
    group = malloc((V + 1) * sizeof(int));
    for (int i = 0; i <= V; i++)
    {
        group[i] = i;
    }
}

static int union_find(int a)
{
    if (group[a] == a)
        return a;
    return group[a] = union_find(group[a]);
}

static bool union_set(int a, int b)
{
    int ga = union_find(a);
    int gb = union_find(b);
    if (ga == gb)
        return false;
    group[gb] = ga;
    return true;
}

static void union_delete()
{
    free(group);
}

int main(void)
{
    if (scanf("%d %d", &V, &E) != 2)
        return 1;
    int (*edges)[3] = malloc(E * sizeof(int[3]));
    for (int i = 0; i < E; i++)
    {
        int a, b, c;
        if (scanf("%d %d %d", &a, &b, &c) != 3)
            return 1;
        edges[i][0] = c;
        edges[i][1] = a;
        edges[i][2] = b;
    }
    qsort(edges, E, sizeof(int[3]), cmp);

    union_init();
    int e_idx = 0, v_cnt = 1, val = 0;
    while (e_idx < E && v_cnt < V)
    {
        int *cur = edges[e_idx++];
        if (union_set(cur[1], cur[2]))
        {
            v_cnt++;
            val += cur[0];
        }
    }
    printf("%d", val);
    free(edges);
    union_delete();
    return 0;
}