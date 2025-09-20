import java.io.*;
import java.util.*;

/**
 * @author python98
 * @description ?
 * @performance 메모리: ? KB, 동작시간: ? ms
 */

// 주변 8칸과 비교했을 때 제일 높다면 해당 칸부터 제거 시작
// 높은게 있다면 해당 칸도 제거 대상
// 제거 대상이라면 주변의 같거나 작은 칸도 제거 대상이 됨
public class Main {
    static final int[][] DIR = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
    static int N, M, cnt;
    static int[][] map;
    static boolean[][] checked;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        checked = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int r = 0; r < N ; r++) {
            for(int c = 0; c < M ; c++) {
                if(checked[r][c]) continue;
                for(int[] d : DIR) {
                    int nr = r + d[0], nc = c + d[1];
                    if(nr < 0 || nc < 0 || nr >= N || nc >= M || checked[nr][nc]) continue;
                    if(map[r][c]>map[nr][nc]) rm(nr,nc);
                    else if(map[r][c] < map[nr][nc]) rm(r,c);
                }
            }
        }

        for(int r = 0; r < N ; r++) {
            for(int c = 0; c < M ; c++) {
                if(checked[r][c]) continue;
                gr(r,c);
                cnt++;
            }
        }
        System.out.print(cnt);
    }

    private static void gr(int r, int c){
        checked[r][c] = true;
        for(int[] d : DIR) {
            int nr = r + d[0], nc = c + d[1];
            if(nr < 0 || nc < 0 || nr >= N || nc >= M || checked[nr][nc]) continue;
            gr(nr,nc);
        }
    }

    private static void rm(int r, int c){
        checked[r][c] = true;
        for(int[] d : DIR) {
            int nr = r + d[0], nc = c + d[1];
            if(nr < 0 || nc < 0 || nr >= N || nc >= M || checked[nr][nc]) continue;
            if(map[r][c]>=map[nr][nc]) rm(nr,nc);
        }
    }
}