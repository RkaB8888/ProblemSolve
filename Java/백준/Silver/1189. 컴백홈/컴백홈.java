import java.io.*;
import java.util.*;

/**
 * @author python98
 * @description ?
 * @performance 메모리: ? KB, 동작시간: ? ms
 */
public class Main {

    static final int[][] DIRECTIONS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    static int R, C, K, result;
    static char[][] map;
    static boolean[][] visit;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new char[R][];
        visit = new boolean[R][C];
        for(int i = 0; i < R; i++) map[i] = br.readLine().toCharArray();
        visit[R-1][0] = true;
        dfs(R-1, 0, 1);
        System.out.print(result);
    }

    private static void dfs(int r, int c, int cnt) {
        if(r == 0 && c == C-1 && cnt == K) {
            result++;
            return;
        }
        if(cnt > K) return;
        for(int i = 0 ; i < 4 ; i++) {
            int nr = r + DIRECTIONS[i][0];
            int nc = c + DIRECTIONS[i][1];
            if(nr < 0 || nr >= R || nc < 0 || nc >= C || map[nr][nc] == 'T' || visit[nr][nc]) continue;
            visit[nr][nc] = true;
            dfs(nr, nc, cnt+1);
            visit[nr][nc] = false;
        }
    }

}
