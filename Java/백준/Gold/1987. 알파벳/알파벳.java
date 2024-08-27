import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int R, C, maxMoveCnt;
    static char[][] map;
    static int[][] dr_dc = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        
        map = new char[R][C];
        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
        }
        
        
        dfs(0, 0, 1 << (map[0][0] - 'A'), 1);
        
        System.out.println(maxMoveCnt);
    }
    
    public static void dfs(int row, int col, int bitmask, int moveCnt) {
        maxMoveCnt = Math.max(maxMoveCnt, moveCnt);

        for (int i = 0; i < 4; i++) {
            int r = row + dr_dc[i][0];
            int c = col + dr_dc[i][1];
            
            if (r < 0 || c < 0 || r >= R || c >= C) continue;
            
            int nextBit = 1 << (map[r][c] - 'A');
            
            if ((bitmask & nextBit) != 0) continue;
            
            dfs(r, c, bitmask | nextBit, moveCnt + 1);
        }
    }
}