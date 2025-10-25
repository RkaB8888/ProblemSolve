#include <stdio.h>
#include <string.h>
#include <stdbool.h>

/**
 * @description 백트래킹(타이트) + 그리디 + 계수배열
 * @performance 메모리: 1,116 KB, 동작시간: 0 ms
 * @author java08
 */

static int Bd[10], lenA, lenB;
static int cnt[10]; // 자릿수 카운팅
static int ansd[10];

static bool dfs(int pos, bool tight)
{
    if (pos == lenA)
        return true; // 전부 채웠음

    int limit = tight ? Bd[pos] : 9;
    for (int d = limit; d >= 0; --d)
    {
        if (cnt[d] == 0)
            continue;
        if (pos == 0 && d == 0)
            continue; // 선행 0 금지

        cnt[d]--;
        ansd[pos] = d;
        bool next_tight = tight && (d == limit);
        if (dfs(pos + 1, next_tight))
            return true;
        cnt[d]++;
    }
    return false;
}

int main(void)
{
    char A[12], B[12];
    if (scanf("%11s %11s", A, B) != 2)
        return 1;

    lenA = (int)strlen(A);
    lenB = (int)strlen(B);

    if (lenA > lenB)
    {
        puts("-1");
        return 0;
    }

    memset(cnt, 0, sizeof(cnt));
    for (int i = 0; A[i]; ++i)
        cnt[A[i] - '0']++;

    if (lenA < lenB)
    {
        for (int d = 9; d >= 1; --d)
        {
            if (cnt[d])
            {
                ansd[0] = d;
                cnt[d]--;
                break;
            }
        }

        int pos = 1;
        for (int d = 9; d >= 0; --d)
        {
            while (cnt[d]--)
                ansd[pos++] = d;
        }

        int val = 0;
        for (int i = 0; i < lenA; ++i)
            val = val * 10 + ansd[i];
        printf("%d\n", val);
        return 0;
    }

    for (int i = 0; i < lenA; ++i)
        Bd[i] = B[i] - '0';

    if (!dfs(0, true))
    {
        puts("-1");
        return 0;
    }

    int val = 0;
    for (int i = 0; i < lenA; ++i)
        val = val * 10 + ansd[i];
    printf("%d\n", val);
    return 0;
}
