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
#define CAP (1u << 17)
#define MASK (CAP - 1)

typedef struct
{
    char name[21];
    bool attended;
    bool before;
    bool after;
} Student;

static Student table[CAP];

static int cnv2min(int h, int m)
{
    return h * 60 + m;
}

static unsigned int hash(const char *str)
{
    unsigned int h = 0;
    while (*str)
    {
        h = h * 131u + (unsigned char)(*str++);
    }
    return h;
}

static unsigned int find_insert_index(const char *name)
{
    unsigned int h = hash(name);
    unsigned int idx = h & MASK;
    while (true)
    {
        if (!table[idx].attended)
        {
            table[idx].attended = true;
            strcpy(table[idx].name, name);
            return idx;
        }
        if (strcmp(table[idx].name, name) == 0)
            return idx;
        idx = (idx + 1) & MASK;
    }
}

int main(void)
{
    int sH, sM, eH, eM, qH, qM;
    if (scanf("%d:%d %d:%d %d:%d", &sH, &sM, &eH, &eM, &qH, &qM) != 6)
    {
        return 1;
    }
    int s = cnv2min(sH, sM);
    int e = cnv2min(eH, eM);
    int q = cnv2min(qH, qM);
    int tH, tM;
    char name[21];
    int cnt = 0;
    while (scanf("%d:%d %s", &tH, &tM, name) == 3)
    {
        int t = cnv2min(tH, tM);
        unsigned int idx = find_insert_index(name);
        if (t <= s)
        {
            table[idx].before = true;
        }
        else if (t >= e && t <= q && table[idx].before && !table[idx].after)
        {
            cnt++;
            table[idx].after = true;
        }
    }

    printf("%d\n", cnt);
    return 0;
}