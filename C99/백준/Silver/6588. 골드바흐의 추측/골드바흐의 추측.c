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
    int n;
    bool memo[1000001];
    for (int i = 0; i <= 1000000; i++)
    {
        memo[i] = true;
    }
    int target = 2;
    int temp = target;
    temp += target;
    while (temp <= 1000000)
    {
        memo[temp] = false;
        temp += target;
    }
    target = 3;
    temp = target;
    temp += target;
    while (temp <= 1000000)
    {
        memo[temp] = false;
        temp += target;
    }
    for (int i = 6; i <= 1000000; i += 6)
    {
        target = i - 1;
        if (memo[target])
        {
            temp = target;
            temp += target;
            while (temp <= 1000000)
            {
                memo[temp] = false;
                temp += target;
            }
        }
        target = i + 1;
        if (memo[target])
        {
            temp = target;
            temp += target;
            while (temp <= 1000000)
            {
                memo[temp] = false;
                temp += target;
            }
        }
    }
    while (true)
    {
        if (scanf("%d", &n) != 1)
            return 1;
        if (n == 0)
            break;
        int idx1 = 3;
        int idx2 = n - 3;
        bool flag = true;
        while (idx1 <= idx2)
        {
            if (memo[idx1] && memo[idx2])
            {
                printf("%d = %d + %d\n", n, idx1, idx2);
                flag = false;
                break;
            }
            idx1 += 2;
            idx2 -= 2;
        }
        if (flag)
            printf("Goldbach's conjecture is wrong.\n");
    }
    return 0;
}