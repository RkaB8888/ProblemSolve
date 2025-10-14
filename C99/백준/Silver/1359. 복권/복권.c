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

int dp[9] = {1, 1, 2, 6, 24, 120, 720, 5040, 40320}; // 0~8!

static inline int combination(int n, int r) {
    if(r > n) return 0;
    return dp[n]/(dp[r]*dp[n-r]);
}

int main(void) {
    int N, M, K;
    
    if(scanf("%d %d %d", &N, &M, &K) != 3) return 0;
    int den = combination(N, M);
    int num = 0;
    for(int i = K ; i <= M ; i++) {
        num+= combination(M, i)*combination(N-M, M-i);
    }
    printf("%.12f\n", (double)num/(double)den);
    return 0;
}