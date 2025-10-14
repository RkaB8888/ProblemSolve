#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <stdint.h>

/**
 * @description ? 
 * @performance 메모리: ? KB, 동작시간: ? ms
 * @author java08
 */

int main(void) {
    int n, T;
    if(scanf("%d %d", &n, &T) != 2) return 0;
    int cnt = 0;
    for(int i = 0, t = 0 ; i < n ; i++) {
        if(scanf("%d", &t) != 1) return 0;
        T-=t;
        if(T>=0) cnt++;
    }
    printf("%d\n", cnt);
    return 0;
}