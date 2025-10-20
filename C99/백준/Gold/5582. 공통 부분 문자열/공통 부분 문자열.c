#include <stdio.h>
#include <string.h>
#include <stdint.h>

/**
 * @description 접미사 자동자(SAM) + 상태 전이
 * @performance 메모리: O(N) (상태 ≤ 2N), 동작시간: O(N+M)  // N=len(scr), M=len(dst)
 * author: java08
 *
 * 문제: 두 문자열 scr, dst의 최장 공통 "부분문자열"(substring) 길이.
 * 방법: scr로 SAM 구성 → dst를 SAM에서 전이 추적하며 최장 길이 갱신.
 */

enum
{
    MAXN = 4000,
    MAXS = 2 * MAXN + 5,
    MAXE = 200000
}; // 여유롭게(필요시 늘리기)

static int lenS[MAXS];  // 각 상태의 길이
static int linkS[MAXS]; // 접미사 링크 = 실패 링크

// 인접리스트 방식 상태 전이 표현
static int head[MAXS];          // 상태별 인접리스트 시작 간선 인덱스(e)
static int toV[MAXE];           // 간선e가 가리키는 상태
static unsigned char chE[MAXE]; // 해당 간선에 해당하는 문자
static int next[MAXE];          // 다음 간선 인덱스
static int ecnt;

static int scnt, last; // SAM: 현재 상태 수 scnt, 마지막 상태 id

static void sam_init(void)
{
    scnt = 1;      // 상태 개수
    last = 0;      // 마지막 상태 = 시작 상태
    lenS[0] = 0;   // 시작 상태의 길이
    linkS[0] = -1; // 시작 상태의 접미사 링크 = 없음
    head[0] = -1;  // 시작 상태의 전이 = 없음
    ecnt = 0;      // 간선 없음
}

static inline int new_state(int L)
{
    int v = scnt++;
    lenS[v] = L;
    linkS[v] = 0;
    head[v] = -1;
    return v;
}

static inline int get_trans(int v, unsigned char c)
{
    for (int e = head[v]; e != -1; e = next[e])
        if (chE[e] == c)
            return toV[e];
    return -1;
}

static inline void set_trans(int v, unsigned char c, int u)
{
    // 있으면 갱신, 없으면 push
    for (int e = head[v]; e != -1; e = next[e])
    {
        if (chE[e] == c) // 이미 있으면
        {
            toV[e] = u; // 도착지 갱신
            return;
        }
    }
    // 없어서 새 간선 ecnt 추가
    chE[ecnt] = c;        // 간선의 문자
    toV[ecnt] = u;        // 간선의 도착 상태
    next[ecnt] = head[v]; // 기존의 첫 간선을 다음 간선으로 연결
    head[v] = ecnt++;     // 새로운 간선을 첫 간선으로 설정
}

static inline void clone_edges(int src, int dst)
{
    // src의 (문자→상태) 전이를 그대로 dst에 복사
    for (int e = head[src]; e != -1; e = next[e])
    {
        set_trans(dst, chE[e], toV[e]);
    }
}

static void sam_extend(unsigned char c)
{
    int cur = new_state(lenS[last] + 1); // 마지막 상태의 길이 + 1
    int p = last;                        // 마지막 상태를 p로 설정

    for (; p != -1 && get_trans(p, c) == -1; p = linkS[p]) // 해당 상태에 c 전이 없으면
        set_trans(p, c, cur);                              // 새로운 상태로 전이 추가

    if (p == -1) // 시작 상태까지 갔으면
    {
        linkS[cur] = 0; // 현재 상태의 접미사 링크를 시작 상태로 설정
    }
    else // p에서 c 전이가 존재하면
    {
        int q = get_trans(p, c);    // p에서의 c 전이 상태를 q로 설정
        if (lenS[p] + 1 == lenS[q]) // p의 길이 + 1이 q의 길이와 같으면
        {
            linkS[cur] = q; // 그냥 q를 현재 상태의 접미사 링크로 설정
        }
        else // 그렇지 않으면(길이 조건 불일치)
        {
            int clone = new_state(lenS[p] + 1); // p의 길이 + 1인 복제 상태 생성
            linkS[clone] = linkS[q];            // q의 접미사 링크를 clone의 접미사 링크로 복제
            clone_edges(q, clone);              // q의 전이를 clone으로 복제

            for (; p != -1 && get_trans(p, c) == q; p = linkS[p])
                set_trans(p, c, clone);

            linkS[q] = linkS[cur] = clone; // 새로운 상태의 접미사 링크와 q의 접미사 링크를 clone으로 설정
        }
    }
    last = cur; // 새로운 상태를 마지막 상태로 갱신
}

int main(void)
{
    static char scr[MAXN + 1], dst[MAXN + 1];
    if (scanf("%4000s%4000s", scr, dst) != 2)
        return 1;

    // 1) scr로 글자 하나씩 SAM 구축
    sam_init(); // 시작 상태 생성
    for (char *p = scr; *p; ++p)
        sam_extend((unsigned char)*p);

    // 2) dst를 따라가며 최장 공통 부분문자열 길이 계산
    int v = 0, l = 0, best = 0;  // 시작 상태, 현재 길이, 최장 길이
    for (char *q = dst; *q; ++q) // dst의 문자 하나씩
    {
        unsigned char c = (unsigned char)*q;
        int to = get_trans(v, c); // 현재 상태에서 c 전이 확인
        if (to != -1)             // 전이 있으면
        {
            v = to;         // 다음 상태로 전이
            if (++l > best) // 길이 1 증가 및 갱신
                best = l;
        }
        else // 전이 없으면
        {
            // suffix link로 점프하며 복구
            while (v != -1 && get_trans(v, c) == -1) // 시작의 링크이거나 전이가 있을 때 까지
                v = linkS[v];
            if (v == -1) // 시작 상태까지 갔으면
            {
                v = 0; // 시작 상태로 복귀
                l = 0; // 길이 초기화
            }
            else // 전이 있으면
            {
                l = lenS[v] + 1;     // 길이를 해당 상태의 길이 + 1로 설정
                v = get_trans(v, c); // 다음 상태로 전이
                if (l > best)        // 길이 갱신
                    best = l;
            }
        }
    }
    printf("%d\n", best);
    return 0;
}
