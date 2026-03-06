#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <stdint.h>

/**
 * @description 역인덱스 + 상태 카운팅
 * @performance 메모리: 3,068 KB, 동작시간: 184 ms
 * @author java08
 */

typedef struct node
{ // 알파벳 노드
    bool rem;
    int len;
    int word[10001];
} node;

int main(void)
{
    int N, M;
    if (scanf("%d %d", &N, &M) != 2)
        return 1;

    int rem_cnt[10001] = {0}; // 암기 카운팅 (0이면 전부 암기 상태)
    node alpha[26];
    for (int i = 0; i < 26; i++)
    {
        alpha[i].rem = true;
        alpha[i].len = 1;
        alpha[i].word[0] = -1;
    }

    for (int i = 0; i < N; i++)
    {
        char temp[1001];
        if (scanf("%1000s", temp) != 1)
            return 1;
        for (char *c = temp; *c; c++)
        {
            if (alpha[*c - 'a'].word[alpha[*c - 'a'].len - 1] != i)
            {
                alpha[*c - 'a'].word[alpha[*c - 'a'].len] = i;
                alpha[*c - 'a'].len++;
            }
        }
    }
    int result = N;
    for (int i = 0; i < M; i++)
    {
        int d;
        char c;
        if (scanf("%d %c", &d, &c) != 2)
            return 1;

        node *cur = &alpha[c - 'a'];
        if (d == 1) // 잊어버림
        {
            if (!cur->rem)
            { // 이미 잊어버림
                printf("%d\n", result);
                continue;
            }
            cur->rem = false;
            for (int j = 1; j < cur->len; j++)
            {
                int word_idx = cur->word[j];
                if (rem_cnt[word_idx] == 0)
                    result--;
                rem_cnt[word_idx]--;
            }
            printf("%d\n", result);
        }
        else if (d == 2) // 기억함
        {
            if (cur->rem)
            { // 이미 기억함
                printf("%d\n", result);
                continue;
            }
            cur->rem = true;
            for (int j = 1; j < cur->len; j++)
            {
                int word_idx = cur->word[j];
                rem_cnt[word_idx]++;
                if (rem_cnt[word_idx] == 0)
                    result++;
            }
            printf("%d\n", result);
        }
        else
            return 2;
    }
    return 0;
}