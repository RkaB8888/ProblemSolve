import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 18,664 KB 90 ms
 * 비트마스크를 이용한 최적화
 */
public class Solution {
    static int N, result[] = new int[11];
    static int colCheck, backSlash, slash; // 비트마스크로 사용

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i < 11; i++) {
            N = i;
            result[N] = 0;
            colCheck = 0;
            backSlash = 0;
            slash = 0;
            dfs(0);
        }

        int TC = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= TC; tc++) {
            N = Integer.parseInt(br.readLine());
            sb.append('#').append(tc).append(' ').append(result[N]).append('\n');
        }
        System.out.println(sb);
    }

    public static void dfs(int row) {
        if (row == N) {
            result[N]++;
            return;
        }
        
        for (int col = 0; col < N; col++) {
            // 열(col), 슬래시(\), 백슬래시(/) 체크
            if ((colCheck & (1 << col)) != 0 ||
                (backSlash & (1 << (row + col))) != 0 ||
                (slash & (1 << (row - col + N - 1))) != 0) continue;
            
            // 비트마스크로 표시
            colCheck |= (1 << col);
            backSlash |= (1 << (row + col));
            slash |= (1 << (row - col + N - 1));
            
            // 다음 행으로 진행
            dfs(row + 1);
            
            // 백트래킹: 비트마스크 복구
            colCheck &= ~(1 << col);
            backSlash &= ~(1 << (row + col));
            slash &= ~(1 << (row - col + N - 1));
        }
    }
}