import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 16,788 KB 94 ms
 * 비트마스크를 이용한 최적화
 */
public class Solution {
    static int N, result[] = new int[11];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 1부터 10까지 미리 계산
        for (int i = 1; i <= 10; i++) {
            N = i;
            result[N] = 0;
            solve(0, 0, 0, 0);
        }

        int TC = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= TC; tc++) {
            N = Integer.parseInt(br.readLine());
            sb.append('#').append(tc).append(' ').append(result[N]).append('\n');
        }
        System.out.println(sb);
    }

    // 비트마스크로 퀸을 놓는 함수
    // col: 열을 체크, diag1: '\' 대각선 체크, diag2: '/' 대각선 체크
    public static void solve(int row, int col, int diag1, int diag2) {
        if (row == N) {
            result[N]++;
            return;
        }
        // 가능한 열 위치를 계산
        int available = ((1 << N) - 1) & ~(col | diag1 | diag2); // 열, 대각선 겹치는 위치 제외
        while (available != 0) {
            int pos = available & -available; // 최하위 비트 선택 (현재 놓을 수 있는 위치)
            available -= pos; // 그 위치에서 퀸을 놓았으므로 제거
            // 다음 행으로 이동
            solve(row + 1, col | pos, (diag1 | pos) << 1, (diag2 | pos) >> 1);
        }
    }
}