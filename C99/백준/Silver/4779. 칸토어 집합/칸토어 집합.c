#include <stdio.h>
#include <string.h>

/**
 * @description 칸토어 집합 생성 (분할정복) + 문자 배열
 * @performance 메모리: 1,812 KB, 동작시간: 0 ms
 * @author java08
 */

int idx;
char str[531442];
int pow3[13] = {1};

void cantor(int N)
{
    if (N == 0)
    {
        str[idx++] = '-';
    }
    else
    {
        cantor(N - 1);
        idx += pow3[N - 1];
        cantor(N - 1);
    }
}

int main(void)
{
    for (int i = 1; i < 13; i++)
    {
        pow3[i] = pow3[i - 1] * 3;
    }
    int N;
    while (scanf("%d", &N) == 1)
    {
        if (N == 0)
        {
            printf("-\n");
            continue;
        }
        int len = pow3[N];
        memset(str, ' ', len);
        str[len] = '\0';

        idx = 0;
        cantor(N);
        printf("%s\n", str);
    }
    return 0;
}