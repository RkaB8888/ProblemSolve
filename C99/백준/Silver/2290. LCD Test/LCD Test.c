#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <stdint.h>

/**
 * @description 구현
 * @performance 메모리: 1,116 KB, 동작시간: 0 ms
 * @author java08
 */

static int table[10][7] = {
    {1, 1, 1, 0, 1, 1, 1}, // 0
    {0, 0, 1, 0, 0, 1, 0}, // 1
    {1, 0, 1, 1, 1, 0, 1}, // 2
    {1, 0, 1, 1, 0, 1, 1}, // 3
    {0, 1, 1, 1, 0, 1, 0}, // 4
    {1, 1, 0, 1, 0, 1, 1}, // 5
    {1, 1, 0, 1, 1, 1, 1}, // 6
    {1, 0, 1, 0, 0, 1, 0}, // 7
    {1, 1, 1, 1, 1, 1, 1}, // 8
    {1, 1, 1, 1, 0, 1, 1}  // 9
};

static inline void putchar_n(char c, int n)
{
    for (int i = 0; i < n; i++)
        putchar(c);
}

static void process(int s, char *n)
{
    int len = strlen(n);
    for (int i = 0; i < 5; i++)
    {
        if (i == 1 || i == 3) // 세로줄
        {
            int left = (i == 1) ? 1 : 4;
            int right = (i == 1) ? 2 : 5;
            for (int k = 0; k < s; k++) // 행 반복
            {
                for (int j = 0; j < len; j++)
                {
                    int d = n[j] - '0';
                    int *seg = table[d];
                    putchar(seg[left] ? '|' : ' ');
                    putchar_n(' ', s); // 열 반복
                    putchar(seg[right] ? '|' : ' ');
                    putchar(' ');
                }
                putchar('\n');
            }
        }
        else // 가로줄
        {
            int seg_row = (i == 0) ? 0 : ((i == 2) ? 3 : 6);
            for (int j = 0; j < strlen(n); j++)
            {
                int d = n[j] - '0';
                int *seg = table[d];
                putchar(' ');
                putchar_n(seg[seg_row] ? '-' : ' ', s);
                putchar_n(' ', 2);
            }
            putchar('\n');
        }
    }
}

int main(void)
{
    int s;
    char n[11];
    scanf("%d", &s);
    scanf("%10s", n);
    process(s, n);
    return 0;
}