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
int cmp(const void *a, const void *b)
{
    const int *x = a;
    const int *y = b;
    if (x[1] == y[1])
    {
        if (x[2] == y[2])
        {
            return x[3] - y[3];
        }
        return x[2] - y[2];
    }
    else
    {
        return y[1] - x[1];
    }
}

int main(void)
{
    int T;
    if (scanf("%d", &T) != 1)
        return 0;
    while (T--)
    {

        int n, k, t, m; // n: 팀 수, k: 문제 수, t: 팀 ID, m: 로그 수
        if (scanf("%d %d %d %d", &n, &k, &t, &m) != 4)
            return 0;
        int (*result)[101] = (int (*)[101])calloc(n + 1, sizeof(int[101]));
        if (result == NULL)
            return 0;
        int (*sumScore)[4] = (int (*)[4])calloc(n + 1, sizeof(int[4]));
        if (sumScore == NULL)
            return 0;
        for (int i = 0; i < m; i++)
        {
            int id, pn, s; // id: 팀 ID, pn: 문제 번호, s: 점수
            if (scanf("%d %d %d", &id, &pn, &s) != 3)
                return 0;
            if (result[id][pn] < s)
            {
                sumScore[id][0] = id;                  // 팀 ID
                sumScore[id][1] += s - result[id][pn]; // 해당 팀의 총 점수
                result[id][pn] = s;
            }
            sumScore[id][2]++;   // 문제를 푼 개수
            sumScore[id][3] = i; // 가장 마지막에 제출한 로그의 번호
        }
        qsort(sumScore, n + 1, sizeof(int[4]), cmp);
        for (int i = 0; i <= n; i++)
        {
            if (sumScore[i][0] == t)
            {
                printf("%d\n", i + 1);
                break;
            }
        }
        free(result);
        free(sumScore);
    }
    return 0;
}