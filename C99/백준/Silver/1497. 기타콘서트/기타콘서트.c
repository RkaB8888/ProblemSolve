#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <stdint.h>

/**
 * @description dfs + bitmask
 * @performance 메모리: 1,112 KB, 동작시간: 0 ms
 * @author java08
 */

static int N, M, result, songTotal;

// 기타 정보, 몇 번째 기타, 기타 사용 갯수, 가능한 노래 비트마스크, 연주 가능한 곡 갯수
static void dfs(long long *guitar, int depth, int cnt, long long bitmask, int songCnt)
{
    if (songTotal == M && result <= cnt)
    {
        return;
    }
    if (depth == N)
    {
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
    dfs(guitar, depth + 1, cnt, bitmask, songCnt);
    int nextSongCnt = songCnt;
    long long nextBitmask = ~bitmask & guitar[depth];
    while (nextBitmask)
    {
        nextBitmask &= (nextBitmask - 1);
        nextSongCnt++;
    }
    nextBitmask = bitmask | guitar[depth];
    dfs(guitar, depth + 1, cnt + 1, nextBitmask, nextSongCnt);
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
    // 기타 정보, 몇 번째 기타, 기타 사용 갯수, 가능한 노래 비트마스크, 연주 가능한 곡 갯수
    dfs(guitar, 0, 0, 0LL, 0);
    if (!result)
    {
        result = -1;
    }
    printf("%d\n", result);
    free(guitar);
    return 0;
}