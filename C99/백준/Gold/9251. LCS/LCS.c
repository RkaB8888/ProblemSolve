#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <stdint.h>

/**
 * @description LCS + DP(1차원 롤링)
 * @performance 메모리: 4,944 KB, 동작시간: 4 ms
 * @author java08
 */

/*
최적화
현재는 같으면 좌상단에서 1을 더하고, 다르면 위와 왼쪽 중 큰 값을 가져오는 방식
좌상단에서 1을 더하기 -> 다음 칸에 1을 더한 값을 저장
위에서 가져오기 -> 다음 칸에 1을 더하기 전에 따로 변수에 담아 둠
왼쪽에서 가져오기 -> 왼쪽 칸에서 가져옴
-> 1차원 배열로도 가능
*/

int main(void)
{
    char input1[1001], input2[1001];
    if (scanf("%s %s", input1, input2) != 2)
    {
        return 1;
    }
    int len1 = strlen(input1);
    int len2 = strlen(input2);

    int *dp = calloc(len2 + 1, sizeof(int));

    for (int i = 1; i <= len1; i++)
    {
        int prev = 0;
        for (int j = 1; j <= len2; j++)
        {
            int tmp = dp[j];
            if (input1[i - 1] == input2[j - 1])
            {
                dp[j] = prev + 1;
            }
            else
            {
                dp[j] = (dp[j] > dp[j - 1]) ? dp[j] : dp[j - 1];
            }
            prev = tmp;
        }
    }
    printf("%d\n", dp[len2]);
    free(dp);
    return 0;
}