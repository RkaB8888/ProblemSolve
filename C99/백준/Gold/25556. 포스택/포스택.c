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
// 스택에서 꺼낼 때 오름차순을 유지하려면 각 스택에 들어가는 수의 크기가 증가하는 방향으로 들어가야 함
// 들어가는 값이 작아지면 다른 스택에 넣어야 함
// 즉, 5번 연속 작아진다면 불가능함?
int main(void)
{
    int N, s1 = 0, s2 = 0, s3 = 0, s4 = 0;
    if (scanf("%d", &N) != 1)
        return 1;
    int cur = 0;
    bool flag = false;
    for (int i = 0; i < N; i++)
    {
        if (scanf("%d", &cur) != 1)
            return 1;
        if (flag)
            continue;
        int d1 = cur - s1;
        int d2 = cur - s2;
        int d3 = cur - s3;
        int d4 = cur - s4;
        int minS = 0, minD = 100001;
        if (minD > d1 && d1 > 0)
        {
            minD = d1;
            minS = 1;
        }
        if (minD > d2 && d2 > 0)
        {
            minD = d2;
            minS = 2;
        }
        if (minD > d3 && d3 > 0)
        {
            minD = d3;
            minS = 3;
        }
        if (minD > d4 && d4 > 0)
        {
            minD = d4;
            minS = 4;
        }
        if (minD == 100001)
        {
            flag = true;
        }
        else
        {
            if (minS == 1)
            {
                s1 = cur;
            }
            else if (minS == 2)
            {
                s2 = cur;
            }
            else if (minS == 3)
            {
                s3 = cur;
            }
            else if (minS == 4)
            {
                s4 = cur;
            }
            else
            {
                return 2;
            }
        }
    }
    if (flag)
    {
        printf("NO\n");
    }
    else
    {
        printf("YES\n");
    }
    return 0;
}