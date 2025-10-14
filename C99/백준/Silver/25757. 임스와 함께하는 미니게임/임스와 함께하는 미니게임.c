#include <stdio.h>
#include <string.h>
#include <stdlib.h>

/**
 * @description ? 
 * @performance 메모리: ? KB, 동작시간: ? ms
 * @author java08
 */
static int cmp_str(const void *a, const void *b) {
    return strcmp((const char *) a, (const char *) b);
}

int main(void) {
    int N;
    char kind;
    if(scanf("%d %c", &N, &kind) != 2) return 0;

    char (*name)[21] = (char (*)[21])malloc(N*21);
    if(!name) return 0;
    for(int i = 0 ; i < N ; i++) {
        if(scanf("%s", name[i]) != 1) {
            free(name);
            return 0;
        }
    }
    qsort(name, N, 21, cmp_str);
    
    int unique = 0;
    for(int i = 0 ; i < N ; i++) {
        if(i==0 || strcmp(name[i], name[i-1]) != 0) unique++;
    }
    
    int need = (kind == 'Y' ? 1 : (kind == 'F' ? 2 : 3));
    printf("%d\n", unique / need);
    
    free(name);
    return 0;
}