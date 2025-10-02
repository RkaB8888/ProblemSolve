import java.io.*;
import java.util.*;

/**
 * @author python98
 * @description BFS + Best-Remaining-Fuel Pruning + Coord Packing
 * @performance 메모리: 24,764 KB, 동작시간: 184 ms
 */
public class Main {
    static final int BIT = (1 << 16) - 1;
    static final int[][] DIR = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int limit, T, H, W, O, F, xS, yS, xE, yE;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        limit = 1;
        while (limit < 10000) limit <<= 1;
        limit--;
        T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            O = Integer.parseInt(st.nextToken());
            F = Integer.parseInt(st.nextToken());
            xS = Integer.parseInt(st.nextToken()) - 1;
            yS = Integer.parseInt(st.nextToken()) - 1;
            xE = Integer.parseInt(st.nextToken()) - 1;
            yE = Integer.parseInt(st.nextToken()) - 1;

            map = new int[H][W];
            for (int i = 0; i < O; i++) {
                st = new StringTokenizer(br.readLine());
                map[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1] = Integer.parseInt(st.nextToken());
            }
            if (bfs()) {
                sb.append("잘했어!!\n");
            } else {
                sb.append("인성 문제있어??\n");
            }
        }
        System.out.print(sb);
    }

    private static boolean bfs() {
        boolean[][] mapCopy = new boolean[H][W];
        mapCopy[xS][yS] = true;
        int[][] q = new int[limit + 1][2];
        int t = 0, b = 0;
        q[t][0] = F;
        q[t++][1] = (xS << 16) + yS;
        while (b < t) {
            int[] c = q[b++];
            b &= limit;
            int cF = c[0];
            if (cF == 0) continue;
            int cX = c[1] >> 16, cY = c[1] & BIT;
            for (int[] d : DIR) {
                int nX = cX + d[0], nY = cY + d[1];
                if (nX < 0 || nY < 0 || nX >= H || nY >= W || mapCopy[nX][nY]) continue;
                if (cF < map[nX][nY] - map[cX][cY]) continue;
                mapCopy[nX][nY] = true;
                if (nX == xE && nY == yE) return true;
                q[t][0] = cF - 1;
                q[t++][1] = (nX << 16) + nY;
                t &= limit;
            }
        }
        return false;
    }

}