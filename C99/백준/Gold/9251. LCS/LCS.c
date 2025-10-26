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

int main(void)
{
    char input1[1001], input2[1001];
    if (scanf("%s %s", input1, input2) != 2)
    {
        return 1; // Input error
    }
    int len1 = strlen(input1);
    int len2 = strlen(input2);

    int **dp = malloc(len1 * sizeof(int *));
    for (int i = 0; i < len1; i++)
    {
        dp[i] = calloc(len2, sizeof(int));
    }

    for (int i = 0; i < len2; i++)
    {
        if (input1[0] == input2[i])
        {
            for (int j = i; j < len2; j++)
            {
                dp[0][j] = 1;
            }
            break;
        }
    }

    for (int i = 0; i < len1; i++)
    {
        if (input1[i] == input2[0])
        {
            for (int j = i; j < len1; j++)
            {
                dp[j][0] = 1;
            }
            break;
        }
    }

    for (int i = 1; i < len1; i++)
    {
        for (int j = 1; j < len2; j++)
        {
            if (input1[i] == input2[j])
            {
                dp[i][j] = dp[i - 1][j - 1] + 1;
            }
            else
            {
                dp[i][j] = (dp[i - 1][j] > dp[i][j - 1]) ? dp[i - 1][j] : dp[i][j - 1];
            }
        }
    }
    printf("%d\n", dp[len1 - 1][len2 - 1]);
    for (int i = 0; i < len1; i++)
    {
        free(dp[i]);
    }
    free(dp);
    return 0;
}