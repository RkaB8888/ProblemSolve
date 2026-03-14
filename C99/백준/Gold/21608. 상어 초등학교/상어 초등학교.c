#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <stdint.h>

/**
 * @description 구현
 * @performance 메모리: 1,116 KB, 동작시간: 0 ms
 * @author java08
 */

static int dir[4][2] = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};

int N;
int **arr, **farr, **space;
int (*friend)[4];
int (*pos)[2];

void batch(int num)
{
    int stack[400][2]; // 친구의 주변 공간 저장 스택
    int top = 0;
    for (int i = 0; i <= N; i++)
    { // 선호 지도 초기화
        memset(farr[i], 0, (N + 1) * sizeof(int));
    }
    for (int i = 0; i < 4; i++)
    {
        int fnum = friend[num][i];
        int fr = pos[fnum][0]; // 친구 좌표 불러오기
        int fc = pos[fnum][1];
        if (fr == 0 || fc == 0)
            continue; // 친구 좌표가 없으면 건너뛰기
        for (int j = 0; j < 4; j++)
        { // 친구 주변 공간 검사
            int nfr = fr + dir[j][0];
            int nfc = fc + dir[j][1];
            if (nfr < 1 || nfr > N || nfc < 1 || nfc > N)
                continue;
            if (arr[nfr][nfc] != 0)
                continue; // 주변 공간이 비어있지 않으면 건너뛰기
            if (farr[nfr][nfc] == 0)
            { // 선호 지도에 담긴적 없는 좌표면 추가
                stack[top][0] = nfr;
                stack[top][1] = nfc;
                top++;
            }
            farr[nfr][nfc]++; // 선호도 상승
        }
    }

    int max = 0, r = N + 1, c = N + 1; // 선호도 높은 곳 찾기
    while (top--)
    {
        int cr = stack[top][0];
        int cc = stack[top][1];
        if (max < farr[cr][cc])
        { // 선호도가 더 높다면
            max = farr[cr][cc];
            r = cr;
            c = cc;
        }
        else if (max == farr[cr][cc])
        { // 선호도가 같은데
            if (space[cr][cc] > space[r][c])
            { // 주변 빈공간이 더 많다면
                r = cr;
                c = cc;
            }
            else if (space[cr][cc] == space[r][c])
            { // 주변 빈공간이 같은데
                if (cr < r)
                { // 행이 더 작다면
                    r = cr;
                    c = cc;
                }
                else if (cr == r)
                { // 행이 같은데
                    if (cc < c)
                    { // 열이 작다면
                        c = cc;
                    }
                }
            }
        }
    }
    if (r == N + 1 || c == N + 1)
    {                       // 선호도가 갱신된 적이 없음(친구가 없음)
        int max_space = -1; // 비어있는 칸이 가장 많은 칸을
        for (int j = 1; j <= N; j++)
        { // 행이 작은 순
            for (int k = 1; k <= N; k++)
            { // 열이 작은 순
                if (arr[j][k] == 0 && space[j][k] > max_space)
                { // 빈공간이 더 많다면
                    r = j;
                    c = k;
                    max_space = space[j][k];
                }
                if (max_space == 4)
                    break; // 빈공간이 최대면 바로 탈출
            }
            if (max_space == 4)
                break;
        }
    }
    arr[r][c] = num;
    space[r][c] = -1; // 할당된 공간의 빈공간을 표시 제거
    pos[num][0] = r;
    pos[num][1] = c;
    for (int j = 0; j < 4; j++)
    { // 주변 공간에 빈공간 1씩 낮춤
        int tr = r + dir[j][0];
        int tc = c + dir[j][1];
        if (tr < 1 || tr > N || tc < 1 || tc > N)
            continue;
        space[tr][tc]--;
    }
}

int calc()
{
    int sum = 0;
    int list[5] = {0, 1, 10, 100, 1000};
    for (int i = 1; i <= N; i++)
    {
        for (int j = 1; j <= N; j++)
        {
            int cnt = 0;
            int num = arr[i][j];
            for (int k = 0; k < 4; k++)
            {
                int ni = i + dir[k][0];
                int nj = j + dir[k][1];
                if (ni < 1 || nj < 1 || ni > N || nj > N)
                    continue;
                int fnum = arr[ni][nj];
                for (int l = 0; l < 4; l++)
                {
                    if (friend[num][l] == fnum)
                    {
                        cnt++;
                        break;
                    }
                }
            }
            sum += list[cnt];
        }
    }
    return sum;
}

int main(void)
{
    if (scanf("%d", &N) != 1)
        return 1;
    int total = N * N;
    arr = malloc(((N + 1)) * sizeof(int *));   // 학생 배치도
    space = malloc(((N + 1)) * sizeof(int *)); // 주변 빈공간 갯수
    farr = malloc(((N + 1)) * sizeof(int *));  // 학생 선호도
    for (int i = 0; i <= N; i++)
    {
        arr[i] = calloc(((N + 1)), sizeof(int));
        space[i] = malloc(((N + 1)) * sizeof(int));
        farr[i] = calloc((N + 1), sizeof(int));
    }
    { // 주변 빈공간 갯수 초기화
        space[1][1] = 2;
        space[1][N] = 2;
        space[N][1] = 2;
        space[N][N] = 2;
        for (int i = 2; i < N; i++)
        {
            space[i][1] = 3;
            space[i][N] = 3;
            space[1][i] = 3;
            space[N][i] = 3;
            for (int j = 2; j < N; j++)
            {
                space[i][j] = 4;
            }
        }
    }
    friend = malloc((total + 1) * sizeof(int[4])); // 친구 저장
    pos = calloc((total + 1), sizeof(int[2]));     // 위치 저장
    for (int i = 0; i < total; i++)
    {
        int a, b, c, d, e;
        if (scanf("%d %d %d %d %d", &a, &b, &c, &d, &e) != 5)
            return 1;
        friend[a][0] = b;
        friend[a][1] = c;
        friend[a][2] = d;
        friend[a][3] = e;
        batch(a);
    }
    printf("%d\n", calc());
    free(pos);
    free(friend);
    for (int i = 0; i <= N; i++)
    {
        free(farr[i]);
        free(arr[i]);
        free(space[i]);
    }
    free(farr);
    free(space);
    free(arr);
    return 0;
}