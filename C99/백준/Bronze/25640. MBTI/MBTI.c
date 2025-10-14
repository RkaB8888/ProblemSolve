#include <stdio.h>

/**
 * @description 문자열 비교 
 * @performance 메모리: 1,112 KB, 동작시간: 0 ms
 * @author java08
 */

static inline int enc4(const char s[4]) {
    return (s[0] << 24) | (s[1] << 16) | (s[2] << 8) | s[3];
}

int main(void) {
    char my[5] = {0};
    if(scanf("%4s", my) != 1) return 0;

    int N;
    if(scanf("%d", &N) != 1) return 0;

    int key = enc4(my);
    int cnt = 0;
    char buf[5] = {0};
    for(int i = 0 ; i < N ; i++) {
        if(scanf("%4s", buf) != 1) return 0;
        if(enc4(buf) == key) cnt++;
    }

    printf("%d\n", cnt);
    return 0;
}