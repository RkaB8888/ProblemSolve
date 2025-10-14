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
    char* my = (char*)malloc(4*sizeof(char));
    scanf("%s", my);

    int N = 0;
    scanf("%d", &N);

    char** friends = (char**)malloc(N*sizeof(char*));
    for(int i = 0 ; i < N ; i++) {
        friends[i] = (char*)malloc(4*sizeof(char));
        scanf("%s", friends[i]);
    }
    
    int cnt = 0;
    for(int i = 0 ; i < N ; i++) {
        if(strcmp(my, friends[i]) == 0) cnt++;
        free(friends[i]);
    }
    free(friends);
    free(my);
    printf("%d\n", cnt);
    return 0;
}