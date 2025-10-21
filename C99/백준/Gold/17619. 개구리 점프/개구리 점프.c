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

static int compare(const void *a, const void *b)
{
    const int *aa = (const int *)a;
    const int *bb = (const int *)b;

    if (aa[1] != bb[1])
    {
        return aa[1] - bb[1];
    }
    return aa[0] - bb[0];
}

static void init_union(int *parent, int n)
{
    for (int i = 0; i < n; i++)
    {
        parent[i] = i;
    }
}

static int find_parent(int *parent, int a)
{
    if (parent[a] == a)
        return a;
    return parent[a] = find_parent(parent, parent[a]);
}

static void set_union(int *parent, int a, int b)
{
    if (a == b)
        return;
    int rootA = find_parent(parent, a);
    int rootB = find_parent(parent, b);
    if (rootA != rootB)
    {
        parent[rootB] = rootA;
    }
}

int main(void)
{
    int N, Q;
    if (scanf("%d %d", &N, &Q) != 2)
    {
        return 1;
    }
    int arr[200000][3] = {
        0,
    };
    for (int i = 0; i < N; i++)
    {
        int x1, x2, y;
        if (scanf("%d %d %d", &x1, &x2, &y) != 3)
        {
            return 1;
        }
        arr[i << 1][0] = 1;
        arr[i << 1][1] = x1;
        arr[i << 1][2] = i;
        arr[i << 1 | 1][0] = -1;
        arr[i << 1 | 1][1] = x2 + 1;
        arr[i << 1 | 1][2] = i;
    }
    int parent[100000];
    init_union(parent, N);
    qsort(arr, N << 1, sizeof(arr[0]), compare);

    int count[100001] = {
        0,
    };
    int pre_count = 0, pre_idx = -1;
    for (int i = 0; i < (N << 1); i++)
    {
        if (pre_count > 0) // 이전에 열린 선분 있음
        {
            set_union(parent, pre_idx, arr[i][2]);
        }
        count[arr[i][2]] = pre_count + arr[i][0];
        pre_count = count[arr[i][2]];
        pre_idx = arr[i][2];
    }

    for (int i = 0; i < Q; i++)
    {
        int l, r;
        if (scanf("%d %d", &l, &r) != 2)
        {
            return 1;
        }
        if (l > r)
        {
            int temp = l;
            l = r;
            r = temp;
        }
        if (find_parent(parent, l - 1) == find_parent(parent, r - 1))
        {
            printf("1\n");
        }
        else
        {
            printf("0\n");
        }
    }
    return 0;
}