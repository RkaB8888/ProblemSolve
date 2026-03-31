#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <stdint.h>

/**
 * @description DP
 * @performance 메모리: 2,676 KB, 동작시간: 44 ms
 * @author java08
 */

// 최대 M번만에 1에서 N지점으로 이동, 최대 비용 출력
// 이동 방향은 무조건 커지는 방향
// N은 최대 300, M은 최대 N, K는 최대 100,000이니 인접 행렬도 괜찮을 듯?

#define MAX(a, b) ((a) > (b) ? (a) : (b))

int N, M, K;
int adjMap[301][301]; // 이전 노드, 이후 노드
int dp[301][301];     // 노드 번호, 이동 횟수

int main(void)
{
    if (scanf("%d %d %d", &N, &M, &K) != 3)
        return 1;

    if (N == 1)
    {
        printf("0\n");
        return 0;
    }

    memset(adjMap, 0, sizeof(adjMap));
    memset(dp, -1, sizeof(adjMap));

    for (int i = 0; i < K; i++)
    {
        int a, b, c;
        if (scanf("%d %d %d", &a, &b, &c) != 3)
            return 1;

        if (a < b)
            adjMap[a][b] = MAX(adjMap[a][b], c);
    }

    dp[1][1] = 0;
    for (int curN = 2; curN <= N; curN++)
    {
        for (int preN = 1; preN < curN; preN++)
        {
            for (int m = 2; m <= M; m++)
            {
                if (!adjMap[preN][curN] || dp[preN][m - 1] == -1)
                    continue;
                dp[curN][m] = MAX(dp[curN][m], dp[preN][m - 1] + adjMap[preN][curN]);
            }
        }
    }

    int result = 0;
    for (int i = 1; i <= M; i++)
    {
        result = MAX(result, dp[N][i]);
    }
    printf("%d\n", result);

    return 0;
}