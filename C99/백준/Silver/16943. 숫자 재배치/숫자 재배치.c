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

static int cmp(const void *a, const void *b)
{
    return (*(char *)b - *(char *)a);
}

static bool dfs(int depth, int lenA, char *A, char *B, char *C, bool *used, bool *notEqual)
{
    if (depth == lenA)
    {
        return *notEqual;
    }
    for (int i = 0; i < lenA; i++)
    {
        if (used[i])
            continue;
        if (*notEqual)
        {
            used[i] = true;
            C[depth] = A[i];
            if (dfs(depth + 1, lenA, A, B, C, used, notEqual))
                return true;
            used[i] = false;
        }
        else
        {
            if (depth == 0 && A[i] == '0')
                continue;
            if (A[i] < B[depth])
            {
                used[i] = true;
                C[depth] = A[i];
                *notEqual = true;
                if (dfs(depth + 1, lenA, A, B, C, used, notEqual))
                    return true;
                *notEqual = false;
                used[i] = false;
            }
            else if (A[i] == B[depth])
            {
                used[i] = true;
                C[depth] = A[i];
                if (dfs(depth + 1, lenA, A, B, C, used, notEqual))
                    return true;
                used[i] = false;
            }
        }
    }
    return false;
}

int main(void)
{
    char A[11], B[11];
    if (scanf("%10s %10s", A, B) != 2)
    {
        return 1;
    }
    size_t lenA = strlen(A);
    size_t lenB = strlen(B);

    if (lenA > lenB)
    {
        printf("-1\n");
        return 0;
    }
    else if (lenA < lenB)
    {
        qsort(A, lenA, sizeof(char), cmp);
        int temp = 0;
        for (int i = 0; i < lenA; i++)
        {
            temp = (temp << 3) + (temp << 1) + (A[i] & 15);
        }
        printf("%d\n", temp);
        return 0;
    }
    qsort(A, lenA, sizeof(char), cmp);

    char C[11];
    C[lenA] = '\0';
    bool used[11] = {false}, notEqual = false;
    if (dfs(0, lenA, A, B, C, used, &notEqual))
    {
        int result = 0;
        for (size_t i = 0; i < lenA; i++)
        {
            result = (result << 3) + (result << 1) + (C[i] & 15);
        }
        printf("%d\n", result);
    }
    else
    {
        printf("-1\n");
    }
    return 0;
}