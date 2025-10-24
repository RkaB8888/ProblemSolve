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
    int *tree1 = (int *)a;
    int *tree2 = (int *)b;
    if (tree1[1] != tree2[1])
    {
        return (tree1[1] < tree2[1]) ? -1 : 1;
    }
    return (tree1[0] > tree2[0]) ? -1 : 1;
}

int main(void)
{
    int n;
    if (scanf("%d", &n) != 1)
    {
        return 1; // 입력 오류 처리
    }
    int (*tree)[2] = malloc(sizeof(int[2]) * n);
    for (int i = 0; i < n; i++)
    {
        int h;
        if (scanf("%d", &h) != 1)
        {
            return 1; // 입력 오류 처리
        }
        tree[i][0] = h;
    }
    for (int i = 0; i < n; i++)
    {
        int a;
        if (scanf("%d", &a) != 1)
        {
            return 1; // 입력 오류 처리
        }
        tree[i][1] = a;
    }
    qsort(tree, n, sizeof(int[2]), compare);
    long result = 0;
    for (int i = 0; i < n; i++)
    {
        result += tree[i][0] + tree[i][1] * i;
    }

    printf("%ld\n", result);
    free(tree);
    return 0;
}