import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int H, W;
    static char[][] map;
    static int N;
    static char[] input;

    static class Player {
        int i, j, way;
        int[][] dr_dc = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

        public Player(int i, int j, int way) {
            this.i = i;
            this.j = j;
            this.way = way;
        }

        public void Command(char c) {
            if (c == 'S') {
                this.Shoot();
            } else {
                this.Move(c);
            }
        }

        public void Shoot() {
            int r = i + dr_dc[way][0];
            int c = j + dr_dc[way][1];
            while (r >= 0 && r < H && c >= 0 && c < W) {
                if (map[r][c] == '#') break;
                if (map[r][c] == '*') {
                    map[r][c] = '.';
                    break;
                }
                r += dr_dc[way][0];
                c += dr_dc[way][1];
            }
        }

        public void Move(char head) {
            switch (head) {
                case 'U': way = 0; map[i][j] = '^'; break;
                case 'R': way = 1; map[i][j] = '>'; break;
                case 'D': way = 2; map[i][j] = 'v'; break;
                case 'L': way = 3; map[i][j] = '<'; break;
            }
            moveCheck();
        }

        public void moveCheck() {
            int r = i + dr_dc[way][0];
            int c = j + dr_dc[way][1];
            if (r >= 0 && r < H && c >= 0 && c < W && map[r][c] == '.') {
                map[r][c] = map[i][j];
                map[i][j] = '.';
                this.i = r;
                this.j = c;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            map = new char[H][];

            Player p = null;
            for (int i = 0; i < H; i++) {
                map[i] = br.readLine().toCharArray();
                for (int j = 0; j < W; j++) {
                    char c = map[i][j];
                    if (c == '^' || c == 'v' || c == '<' || c == '>') {
                        int way = c == '^' ? 0 : c == '>' ? 1 : c == 'v' ? 2 : 3;
                        p = new Player(i, j, way);
                    }
                }
            }

            N = Integer.parseInt(br.readLine());
            input = br.readLine().toCharArray();

            for (char cmd : input) {
                p.Command(cmd);
            }

            sb.append('#').append(tc).append(' ');
            for (char[] row : map) {
                sb.append(row).append('\n');
            }
        }
        System.out.println(sb);
    }
}