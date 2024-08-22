import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int R, C;
    static char[][] map;
    static int cnt;
    static int[] dr = {-1, 0, 1}; // 우상, 우, 우하
    static int endCol;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        endCol = C - 2; // 마지막 열의 인덱스

        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
        }

        for (int i = 0; i < R; i++) {
            if (findPipePath(i, 0)) {
                cnt++;
            }
        }

        System.out.println(cnt);
    }

    public static boolean findPipePath(int row, int col) {
        if (col == endCol) {
            return true;
        }

        for (int i = 0; i < 3; i++) {
            int r = row + dr[i];
            int c = col + 1;

            if (r >= 0 && r < R && map[r][c] == '.') {
                map[r][c] = 'x'; // 경로를 차지했음을 표시
                if (findPipePath(r, c)) {
                    return true;
                }
            }
        }

        return false; // 모든 경로가 실패하면 false를 반환
    }
}