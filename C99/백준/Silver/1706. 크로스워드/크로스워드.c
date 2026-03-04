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

int R, C;
char map[20][21];
int dir[2][2] = {{0, 1}, {1, 0}};
bool visited[20][20][2]; // 가로,세로

char *compose(int r, int c, int d)
{
    char *result = malloc((C + 1) * sizeof(char));
    int idx = 0;

    do
    {
        result[idx++] = map[r][c];
        visited[r][c][d] = true;
        r += dir[d][0];
        c += dir[d][1];
    } while (r < R && c < C && map[r][c] != '#');

    result[idx] = '\0';
    return result;
}

int main(void)
{
    if (scanf("%d %d", &R, &C) != 2)
        return 1;
    char *result = malloc((C + 1) * sizeof(char));
    result[0] = '\0';
    for (int i = 0; i < R; i++)
    {
        if (scanf("%20s", map[i]) != 1)
            return 1;
    }
    for (int i = 0; i < R; i++)
    {
        for (int j = 0; j < C; j++)
        {
            if (map[i][j] == '#')
                continue;
            for (int k = 0; k < 2; k++)
            {
                if (visited[i][j][k])
                    continue;
                char *temp = compose(i, j, k);
                if (strlen(temp) == 1) // 단어가 성립 안 됨
                    continue;
                if (strlen(result) == 0) // 비교 단어 없음
                    strcpy(result, temp);
                else if (strcmp(result, temp) > 0) // temp가 더 빠른 단어
                    strcpy(result, temp);
                free(temp);
            }
        }
    }
    printf("%s", result);
    free(result);
    return 0;
}