#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <stdint.h>

/**
 * @description DFS + 비트마스크
 * @performance 메모리: 1,116 KB, 동작시간: 0 ms
 * @author java08
 */

static int N, M, result;
static long long allBitmask;

// 기타 정보, 몇 번째 기타, 기타 사용 갯수, 가능한 노래 비트마스크
static void dfs(long long *guitar, int depth, int cnt, long long bitmask)
{
    if (bitmask == allBitmask)
    {
        if (result > cnt)
        {
            result = cnt;
        }
        return;
    }
    if (cnt >= result || depth == N)
    {
        return;
    }
    dfs(guitar, depth + 1, cnt, bitmask);
    dfs(guitar, depth + 1, cnt + 1, bitmask | guitar[depth]);
}

int main(void)
{
    if (scanf("%d %d", &N, &M) != 2)
    {
        return 1; // Error reading input
    }
    long long *guitar = malloc(sizeof(long long) * N);
    if (guitar == NULL)
    {
        return 1; // Error allocating memory
    }
    memset(guitar, 0, sizeof(long long) * N);
    for (int i = 0; i < N; i++)
    {
        char dummy[51];
        char song[M + 1];
        if (scanf("%50s %s", dummy, song) != 2)
        {
            break;
        }
        for (int j = 0; j < M; j++)
        {
            if (song[j] == 'Y')
            {
                guitar[i] |= (1LL << j);
            }
        }
        allBitmask |= guitar[i];
    }

    if (!allBitmask)
    {
        printf("%d\n", -1);
        free(guitar);
        return 0;
    }

    result = N;
    // 기타 정보, 몇 번째 기타, 기타 사용 갯수, 가능한 노래 비트마스크
    dfs(guitar, 0, 0, 0LL);
    printf("%d\n", result);
    free(guitar);
    return 0;
}