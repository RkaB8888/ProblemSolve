#include <stdio.h>
#include <time.h>

/**
 * @description ? 
 * @performance 메모리: ? KB, 동작시간: ? ms
 * @author java08
 */

int main(void) {
    time_t now = time(NULL);
    struct tm *kst = gmtime(&now);
    kst->tm_hour += 9;
    mktime(kst);

    char buf[16];
    strftime(buf, sizeof(buf), "%Y-%m-%d", kst);
    printf("%s\n", buf);
    return 0;
}