#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <stdint.h>

/**
 * @description 펜윅 트리
 * @performance 메모리: 1,112 KB, 동작시간: 0 ms
 * @author java08
 */

// 밑에서부터 n~1까지 쌓여있는 목록 중 보고 싶은 번호 위에 몇개가 쌓여있는지 출력
// n+m크기의 배열 누적합 -> m+1번부터 1씩 넣고 DVD의 인덱스 번호는 따로 추적함.

int all_sum(int *tree, int idx)
{
    int result = 0;
    while (idx > 0)
    {
        result += tree[idx];
        idx -= idx & -idx;
    }
    return result;
}

void update(int *tree, int idx, int diff, int max_idx)
{
    while (idx <= max_idx)
    {
        tree[idx] += diff;
        idx += idx & -idx;
    }
}

int main(void)
{
    int T;
    if (scanf("%d", &T) != 1)
        return 1;

    int idx[100001] = {0}; // DVD 인덱스 추적
    while (T--)
    {
        int n, m;
        if (scanf("%d %d", &n, &m) != 2)
            return 1;
        int max_idx = n + m;
        int *tree = calloc((max_idx + 1), sizeof(int));
        for (int i = 1; i <= n; i++)
        {
            idx[i] = m + i; // dvd가 현재 위치한 인덱스
            tree[m + i] = 1;
        }
        for (int i = 1; i <= max_idx; i++)
        {
            int next = i + (i & -i);
            if (next <= max_idx)
                tree[next] += tree[i];
        }

        for (int i = m; i > 0; i--)
        {
            int target;
            if (scanf("%d", &target) != 1)
            {
                free(tree);
                return 1;
            }
            printf("%d ", all_sum(tree, idx[target] - 1));
            update(tree, idx[target], -1, max_idx);
            idx[target] = i;
            update(tree, idx[target], 1, max_idx);
        }
        printf("\n");
        free(tree);
    }
    return 0;
}