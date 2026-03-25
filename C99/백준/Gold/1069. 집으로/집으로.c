#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <stdint.h>
#include <math.h>

/**
 * @description ?
 * @performance 메모리: 1,112 KB, 동작시간: 0 ms
 * @author java08
 */

// (X, Y) D거리를 T시간에 이동
// D와 T는 1이상 10000이하의 값
// X와 Y는 1이상 1000이하의 값으로 주어지지만, 좌표평면상 음수는 허용되는지 모름
// 1초에 1만큼 움직일지 T초에 D만큼 일직선으로 움직일지 계산해서 최소의 시간(오차는 10^(-9)허용)
// 대각선 가능

// 원점에서의 거리(반지름)만 고려하여 계산하면 될 듯

int main(void)
{
    double X, Y, D, T, R;
    double sum, JnW, onlyW, onlyJ;
    if (scanf("%lf %lf %lf %lf", &X, &Y, &D, &T) != 4)
        return 1;
    // 걷기만 했을 때 시간
    onlyW = R = sqrt((X * X) + (Y * Y));

    // 점프 후 남은 거리 걸었을 때 시간
    double J = floor(R / D);
    double restR = R - (J * D);
    JnW = J * T;
    if (restR > D / 2.0)
    {
        JnW += T;
        restR = fabs(((J + 1) * D) - R);
    }
    JnW += restR;

    // 점프만 했을 때 시간
    if (J == 0.0)
    {
        onlyJ = T + T;
    }
    else
    {
        onlyJ = J * T + T;
    }

    double result = onlyW;
    if (result > JnW)
    {
        result = JnW;
    }
    if (result > onlyJ)
    {
        result = onlyJ;
    }
    printf("%.13lf", result);
    return 0;
}