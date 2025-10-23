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
// 내 차례에
// 1개 있으면 이김
// 2개 있으면 짐
// 3개 있으면 이김
// 4개 있으면 짐
// 5개 있으면 이김
// 6개 있으면 짐
// 7개 있으면 이김
// 8개 있으면 짐
// 9개 있으면 이김
// 10개 있으면 짐
// 11개 있으면 이김
// 12개 있으면 짐
// ... 2의 배수로 남아 있으면 먼저 하는 사람이 진다.
int main(void)
{
    long N;
    if (scanf("%ld", &N) != 1)
        return 1;
    if (N & 1)
        printf("SK\n");
    else
        printf("CY\n");
    return 0;
}