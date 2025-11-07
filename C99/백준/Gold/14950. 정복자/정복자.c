#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <stdint.h>

/**
 * @description MST(크루스칼) + Union-Find(경로 압축, 랭크)
 * @performance 메모리: 1,584 KB, 동작시간: 16 ms
 * @author java08
 */

static int N;                              // 10,000 이하
static int M;                              // 30,000 이하
static int T;                              // 10 이하
static int *edge, *node1, *node2, *weight; // 무방향 간선 갯수 M
static int *group, *rank;                  // 집합 배열 N+1

static int cmp(const void *a, const void *b)
{
    int idx1 = *(int *)a;
    int idx2 = *(int *)b;
    return weight[idx1] - weight[idx2];
}

static void union_init()
{
    group = malloc((N + 1) * sizeof(int));
    rank = malloc((N + 1) * sizeof(int));
    for (int i = 1; i <= N; i++)
    {
        group[i] = i;
        rank[i] = 1;
    }
}
static int union_find(int a)
{
    while (group[a] != a)
    {
        group[a] = group[group[a]];
        a = group[a];
    }
    return a;
}

static bool union_set(int a, int b)
{
    int root_a = union_find(a);
    int root_b = union_find(b);

    if (root_a == root_b)
        return false;

    if (rank[root_a] < rank[root_b])
    {
        group[root_a] = root_b;
    }
    else if (rank[root_a] > rank[root_b])
    {
        group[root_b] = root_a;
    }
    else
    {
        group[root_b] = root_a;
        rank[root_a]++;
    }
    return true;
}

static void union_delete()
{
    free(group);
    free(rank);
}
int main(void)
{
    if (scanf("%d %d %d", &N, &M, &T) != 3)
        return 1;

    edge = malloc(M * sizeof(int));
    node1 = malloc(M * sizeof(int));
    node2 = malloc(M * sizeof(int));
    weight = malloc(M * sizeof(int));

    for (int i = 0; i < M; i++)
    {
        int a, b, c;
        if (scanf("%d %d %d", &a, &b, &c) != 3)
            return 1;

        edge[i] = i;
        node1[i] = a;
        node2[i] = b;
        weight[i] = c;
    }
    qsort(edge, M, sizeof(int), cmp);
    union_init();

    int i = 0, cnt = 1, sum = 0;
    while (i < M && cnt < N)
    {
        int idx = edge[i];
        int u = node1[idx];
        int v = node2[idx];
        int w = weight[idx];

        if (union_set(u, v))
        {
            sum += w;
            cnt++;
        }
        i++;
    }
    sum += T * (((N - 1) * (N - 2)) >> 1); // sum은 최대 13억
    printf("%d\n", sum);

    union_delete();
    free(weight);
    free(node2);
    free(node1);
    free(edge);
    return 0;
}