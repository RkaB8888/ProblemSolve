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

static void process(int s, char *n)
{
    for (int i = 0; i < 5; i++)
    {
        if (i == 1 || i == 3) // 세로줄
        {
            for (int k = 0; k < s; k++) // 행 반복
            {
                for (int j = 0; j < strlen(n); j++)
                {
                    if (i == 1)
                    {
                        if (table[n[j] - '0'][1])
                            putchar('|');
                        else
                            putchar(' ');
                        for (int k = 0; k < s; k++) // 열 반복
                            putchar(' ');
                        if (table[n[j] - '0'][2])
                            putchar('|');
                        else
                            putchar(' ');
                    }
                    else
                    {
                        if (table[n[j] - '0'][4])
                            putchar('|');
                        else
                            putchar(' ');
                        for (int k = 0; k < s; k++) // 열 반복
                            putchar(' ');
                        if (table[n[j] - '0'][5])
                            putchar('|');
                        else
                            putchar(' ');
                    }
                    putchar(' ');
                }
                putchar('\n');
            }
        }
        else // 가로줄
        {
            for (int j = 0; j < strlen(n); j++)
            {
                putchar(' ');
                if (i == 0 && table[n[j] - '0'][0])
                {
                    for (int k = 0; k < s; k++) // 열 반복
                        putchar('-');
                }
                else if (i == 2 && table[n[j] - '0'][3])
                {
                    for (int k = 0; k < s; k++) // 열 반복
                        putchar('-');
                }
                else if (i == 4 && table[n[j] - '0'][6])
                {
                    for (int k = 0; k < s; k++) // 열 반복
                        putchar('-');
                }
                else
                {
                    for (int k = 0; k < s; k++) // 열 반복
                        putchar(' ');
                }
                putchar(' ');
                putchar(' ');
            }
            putchar('\n');
        }
    }
}

int main(void)
{
    int s;
    scanf("%d", &s);
    char *n = malloc(sizeof(char) * (s + 1));
    scanf("%s", n);
    process(s, n);
    free(n);
    return 0;
}