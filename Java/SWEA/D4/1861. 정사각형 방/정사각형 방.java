import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int strNum;
    static int maxVal;
    static int N;
    static int[][] map;
    static int[][] count;
    static int[][] dr_dc = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            strNum = 0;
            maxVal = 0;
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            count = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (count[i][j] == 0) {
                        int len = find(i, j);
                        if (maxVal < len || (maxVal == len && strNum > map[i][j])) {
                            maxVal = len;
                            strNum = map[i][j];
                        }
                    }
                }
            }

            sb.append('#').append(t).append(' ').append(strNum).append(' ').append(maxVal).append('\n');
        }
        System.out.println(sb);
    }

    public static int find(int row, int col) {
        if (count[row][col] > 0) {
            return count[row][col];
        }

        int maxLength = 1;
        for (int i = 0; i < 4; i++) {
            int r = row + dr_dc[i][0];
            int c = col + dr_dc[i][1];
            if (r >= 0 && c >= 0 && r < N && c < N && map[r][c] == map[row][col] + 1) {
                maxLength = Math.max(maxLength, 1 + find(r, c));
            }
        }

        count[row][col] = maxLength;
        return maxLength;
    }
}