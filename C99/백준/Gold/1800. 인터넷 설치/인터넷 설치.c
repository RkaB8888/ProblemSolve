#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <stdint.h>

/**
 * @description 이분탐색 + BFS
 * @performance 메모리: ? KB, 동작시간: ? ms
 * @author java08
 */

// 연결할 수 있는 P개의 쌍을 선택해서 1번과 N번 노드가 연결되어야 하며, 연결된 쌍 중 K개는 무료가 되고 나머지 중 가장 비싼 값만 지불한다.
// 첫 줄 N(1<=N<=1,000) P(1<=P<=10,000) K(0<=K<N)
// 다음 P개의 줄에는 쌍의 번호와 가격(1<=가격<=1,000,000)
// 1번과 N번 노드 연결이 불가능하면 -1 출력

// 지불해야 하는 어떤 값 val을 기준으로 이분 탐색.

int N, P, K;
int *list, *next, *node, *val;

int max(int a, int b)
{
    return a >= b ? a : b;
}

bool bfs(int line)
{
    // 노드 위치, 카운트
    int q[10000][2];
    int front = 0, rear = 0;

    static int cnt[1001];
    for (int i = 1; i < N; i++)
    {
        cnt[i] = 1001;
    }

    q[rear][0] = 1;
    q[rear][1] = 0;
    rear++;
    cnt[1] = 0;
    while (front < rear)
    {
        int cur_node = q[front][0];
        int cur_cnt = q[front][1];
        front++;

        if (cur_cnt > K)
        {
            continue;
        }
        if (cur_node == N - 1)
        {
            return true;
        }

        for (int idx = list[cur_node]; idx != -1; idx = next[idx])
        {
            int next_node = node[idx];
            int next_cnt = cur_cnt + (val[idx] > line);
            if (next_cnt < cnt[next_node])
            {
                cnt[next_node] = next_cnt;
                q[rear][0] = next_node;
                q[rear][1] = next_cnt;
                rear++;
            }
        }
    }
    return false;
}

int main(void)
{
    if (scanf("%d %d %d", &N, &P, &K) != 3)
        return 1;
    N++;

    list = malloc(sizeof(int) * N);
    memset(list, -1, sizeof(int) * N);
    next = malloc(sizeof(int) * (P << 1));
    node = malloc(sizeof(int) * (P << 1));
    val = malloc(sizeof(int) * (P << 1));

    int a, b, c, min_cost = 0, max_cost = 0;
    for (int i = 0; i < P; i++)
    {
        int idx1 = i << 1, idx2 = idx1 + 1;
        if (scanf("%d %d %d", &a, &b, &c) != 3)
            return 1;
        next[idx1] = list[a];
        list[a] = idx1;
        node[idx1] = b;
        val[idx1] = c;

        next[idx2] = list[b];
        list[b] = idx2;
        node[idx2] = a;
        val[idx2] = c;

        max_cost = max(max_cost, c);
    }

    int result = -1;
    while (min_cost <= max_cost)
    {
        int line = (min_cost + max_cost) >> 1;
        if (bfs(line))
        {
            max_cost = line - 1;
            result = line;
        }
        else
        {
            min_cost = line + 1;
        }
    }

    printf("%d\n", result);

    free(val);
    free(node);
    free(next);
    free(list);
    return 0;
}