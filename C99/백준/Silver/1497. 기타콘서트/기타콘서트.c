#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <stdint.h>

/**
 * @description dfs + bitmask
 * @performance 메모리: 1,116 KB, 동작시간: 0 ms
 * @author java08
 */

static int N, M, result, songTotal;

// 기타 정보, 몇 번째 기타, 기타 사용 갯수, 가능한 노래 비트마스크
static void dfs(long long *guitar, int depth, int cnt, long long bitmask)
{
    if (songTotal == M && result <= cnt)
    {
        return;
    }
    if (depth == N)
    {
        int songCnt = 0;
        while (bitmask)
        {
            bitmask &= bitmask - 1;
            songCnt++;
        }
        if (songCnt > songTotal)
        {
            songTotal = songCnt;
            result = cnt;
        }
        else if (songCnt == songTotal && result > cnt)
        {
            result = cnt;
        }
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
        long long bitmask = 0;
        char dummy[51];
        char song[M + 1];
        if (scanf("%51s %s", dummy, song) != 2)
        {
            break;
        }
        for (int j = 0; j < M; j++)
        {
            if (song[j] == 'Y')
            {
                bitmask |= (1LL << j);
            }
        }
        guitar[i] = bitmask;
    }
    result = N + 1;
    // 기타 정보, 몇 번째 기타, 기타 사용 갯수, 가능한 노래 비트마스크
    dfs(guitar, 0, 0, 0LL);
    if (!result)
    {
        result = -1;
    }
    printf("%d\n", result);
    free(guitar);
    return 0;
}