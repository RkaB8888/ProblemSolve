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

#define HASH_SIZE 100003
#define BASE1 31
#define BASE2 37
#define MOD1 1000000007
#define MOD2 1000000009

typedef struct Node
{
    char name[21];
    int index;
} Node;

static inline int is_number(const char *str)
{
    return str[0] >= '0' && str[0] <= '9';
}

static Node hash_table[HASH_SIZE];

static inline int char2int(char c)
{
    if (c >= 'A' && c <= 'Z')
        return c - 'A';
    else if (c >= 'a' && c <= 'z')
        return c - 'a' + 26;

    return -1;
}

static void add_map(const char *name, int index)
{
    long long hash1 = 0;
    long long hash2 = 0;
    for (int i = 0; name[i]; i++)
    {
        hash1 = (hash1 * BASE1 + char2int(name[i])) % MOD1;
        hash2 = (hash2 * BASE2 + char2int(name[i])) % MOD2;
    }
    int hash_index = (hash1 + hash2) % HASH_SIZE;
    while (hash_table[hash_index].index != 0)
    {
        hash_index = (hash_index + 1) % HASH_SIZE;
    }
    strncpy(hash_table[hash_index].name, name, 21);
    hash_table[hash_index].index = index;
}

static int get_index(const char *name)
{
    long long hash1 = 0;
    long long hash2 = 0;
    for (int i = 0; name[i]; i++)
    {
        hash1 = (hash1 * BASE1 + char2int(name[i])) % MOD1;
        hash2 = (hash2 * BASE2 + char2int(name[i])) % MOD2;
    }
    int hash_index = (hash1 + hash2) % HASH_SIZE;
    while (true)
    {
        if (hash_table[hash_index].index == 0)
        {
            return -1; // 도감에 없는 경우
        }
        if (strncmp(hash_table[hash_index].name, name, 21) == 0)
        {
            return hash_table[hash_index].index;
        }
        hash_index = (hash_index + 1) % HASH_SIZE;
    }
}

int main(void)
{
    int N, M; // 1보다 크고 100,000보다 작거나 같은 자연수
    if (scanf("%d %d", &N, &M) != 2)
    {
        return 1;
    }

    char (*arr)[21] = malloc(N * sizeof(char[21])); // 최소 2, 최대 20
    for (int i = 0; i < N; i++)
    {
        if (scanf("%20s", arr[i]) != 1)
        {
            free(arr);
            return 1;
        }
        add_map(arr[i], i + 1);
    }
    for (int i = 0; i < M; i++)
    {
        char input[21];
        if (scanf("%s", input) != 1)
        {
            free(arr);
            return 1;
        }
        if (is_number(input)) // 번호를 받은 경우
        {
            int index = atoi(input) - 1;
            printf("%s\n", arr[index]);
        }
        else // 이름을 받은 경우
        {
            printf("%d\n", get_index(input));
        }
    }
    free(arr);
    return 0;
}