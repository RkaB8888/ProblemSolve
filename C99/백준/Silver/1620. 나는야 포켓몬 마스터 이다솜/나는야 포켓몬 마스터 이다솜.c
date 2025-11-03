#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <stdint.h>

/**
 * @description djb2 해시
 * @performance 메모리: 5,916 KB, 동작시간: 232 ms
 * @author java08
 */
#define HASH_SIZE 100003
static char (*arr)[21];

static int hash_table[HASH_SIZE];

static inline int is_number(const char *str)
{
    return str[0] >= '0' && str[0] <= '9';
}

static inline int char2int(char c)
{
    if (c >= 'A' && c <= 'Z')
        return c - 'A';
    else if (c >= 'a' && c <= 'z')
        return c - 'a' + 26;

    return -1;
}

static unsigned long long djb2(const char *str)
{
    unsigned long long hash = 5381;
    for (int i = 0; str[i]; i++)
    {
        hash = ((hash << 5) + hash) + str[i];
    }
    return hash;
}

static void add_map(const char *name, int index)
{
    unsigned long long temp = djb2(name);
    unsigned long long hash1 = temp % HASH_SIZE;
    unsigned long long hash2 = 1 + (temp % (HASH_SIZE - 1));
    int hash_index = hash1;
    while (hash_table[hash_index] != 0)
    {
        hash_index = (hash_index + hash2) % HASH_SIZE;
    }
    hash_table[hash_index] = index;
}

static int get_index(const char *name)
{
    unsigned long long temp = djb2(name);
    unsigned long long hash1 = temp % HASH_SIZE;
    unsigned long long hash2 = 1 + (temp % (HASH_SIZE - 1));
    int hash_index = hash1;
    while (hash_table[hash_index] != 0)
    {
        int index = hash_table[hash_index];
        if (strcmp(arr[index], name) == 0)
        {
            return index;
        }
        hash_index = (hash_index + hash2) % HASH_SIZE;
    }
    return -1;
}

int main(void)
{
    int N, M; // 1보다 크고 100,000보다 작거나 같은 자연수
    if (scanf("%d %d", &N, &M) != 2)
    {
        return 1;
    }

    arr = malloc((N + 1) * sizeof(char[21])); // 최소 2, 최대 20
    for (int i = 1; i <= N; i++)
    {
        if (scanf("%20s", arr[i]) != 1)
        {
            free(arr);
            return 1;
        }
        add_map(arr[i], i);
    }
    char input[21];
    for (int i = 0; i < M; i++)
    {
        if (scanf("%20s", input) != 1)
        {
            free(arr);
            return 1;
        }
        if (is_number(input)) // 번호를 받은 경우
        {
            int index = atoi(input);
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