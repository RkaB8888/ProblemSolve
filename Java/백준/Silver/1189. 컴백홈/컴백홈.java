import java.io.*;
import java.util.*;

/**
 * @author python98
 * @description DFS + Backtracking + Visited Bitmask + Manhattan/Parity Pruning
 * @performance 메모리: 12,344 KB, 동작시간: 96 ms
 */
public class Main {

    static final int[][] DIRECTIONS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    static int R, C, K, result;
    static int visit, block;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        for (int i = 0; i < R; i++) {
            char[] row = br.readLine().toCharArray();
            for (int j = 0; j < C; j++) {
                if (row[j] == 'T') block |= 1 << (i * C + j);
            }
        }
        visit = 1 << ((R - 1) * C);
        dfs(R - 1, 0, 1);
        System.out.print(result);
    }

    private static void dfs(int r, int c, int cnt) {
        if (cnt == K) {
            if (r == 0 && c == C - 1) result++;
            return;
        }

        int rest = K - cnt;
        int minCnt = r + (C - 1 - c);
        if (rest < minCnt || ((rest - minCnt) & 1) == 1) return;

        for (int[] d : DIRECTIONS) {
            int nr = r + d[0], nc = c + d[1];
            if (nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
            int idx = nr * C + nc, bit = 1 << idx;
            if ((block & bit) != 0 || (visit & bit) != 0) continue;
            visit |= bit;
            dfs(nr, nc, cnt + 1);
            visit ^= bit;
        }
    }

}
