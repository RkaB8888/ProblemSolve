
#include <stdio.h>
#include <stdlib.h>
#include <limits.h>
#include <stdbool.h>

int D;
int W;
int K;
bool** map;
bool* A;
bool* B;
int result;

bool isPossible() {
    for (int j = 0; j < W; j++) {
        bool flag = false;
        int trueCnt = 0, falseCnt = 0;
        for (int i = 0; i < D; i++) {
            if (map[i][j]) {
                trueCnt++;
                falseCnt = 0;
            }
            else {
                trueCnt = 0;
                falseCnt++;
            }
            if (trueCnt >= K || falseCnt >= K) {
                flag = true;
                break;
            }
        }
        if (flag) continue;
        return false;
    }
    return true;
}

void subSet(int row, int cnt) {
    if(cnt>=result) return;
    if (row == D) {
        if (isPossible()) {
            if (cnt < result)
                result = cnt;
        }
        return;
    }

    // 안 바꿈
    subSet(row + 1, cnt);

    // 임시 저장
    bool* temp = map[row];

    // A로 바꿈
    map[row] = A;
    subSet(row + 1, cnt + 1);

    // B로 바꿈
    map[row] = B;
    subSet(row + 1, cnt + 1);

    // 복구
    map[row] = temp;
}

int main() {
    int testCase;
    scanf("%d", &testCase);

    for (int t = 1; t <= testCase; t++) {
        scanf("%d %d %d", &D, &W, &K);

        map = (bool**)malloc(sizeof(bool*) * D);
        for (int i = 0; i < D; i++) {
            map[i] = (bool*)malloc(sizeof(bool) * W);
        }

        A = (bool*)calloc(W, sizeof(bool));
        B = (bool*)malloc(sizeof(bool) * W);
        for (int i = 0; i < W; i++) {
            B[i] = true;
        }

        for (int i = 0; i < D; i++) {
            for (int j = 0; j < W; j++) {
                int temp;
                scanf("%d", &temp);
                map[i][j] = temp ? true : false;
            }
        }

        result = INT_MAX;
        subSet(0, 0);
        printf("#%d %d\n", t, result);

        // 메모리 해제
        for (int i = 0; i < D; i++) {
            free(map[i]);
        }
        free(map);
        free(A);
        free(B);
    }

    return 0;
}
