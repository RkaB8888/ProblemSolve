import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
/**
 * 투포인터, BFS 메모리 ? KB 시간 ? ms
 * 
 * @author python98
 */
public class Main {
    static int N;
    static int[][] map;
    static int[] heights;
    static int hLen;
    static int minH, maxH;
    static int startVal, endVal;
    static int[][] queue; 
    static boolean[][] visited;
    static final int[][] DIR = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int result = 200;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        int[] tempHeights = new int[N*N];
        int idx = 0;

        for (int i = 0; i < N; i++) {
            String[] line = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                int val = Integer.parseInt(line[j]);
                map[i][j] = val;
                tempHeights[idx++] = val;
            }
        }

        Arrays.sort(tempHeights, 0, idx);
        int uniqueCount = 1;
        for (int i = 1; i < idx; i++) {
            if (tempHeights[i] != tempHeights[uniqueCount-1]) {
                tempHeights[uniqueCount++] = tempHeights[i];
            }
        }

        heights = new int[uniqueCount];
        System.arraycopy(tempHeights, 0, heights, 0, uniqueCount);
        hLen = uniqueCount;

        startVal = map[0][0];
        endVal = map[N-1][N-1];

        int lowIdx = 0, highIdx = 0;

        while (highIdx < hLen && heights[highIdx] < startVal) {
            highIdx++;
        }

        visited = new boolean[N][N];
        queue = new int[N*N][2];
        // 투 포인터 탐색
        while (lowIdx <= highIdx && highIdx < hLen) {
            minH = heights[lowIdx];
            if (startVal < minH || endVal < minH) {
                break;
            }

            maxH = heights[highIdx];
            if (startVal > maxH || endVal > maxH) {
                highIdx++;
                continue;
            }

            if (checkPath()) {
                int diff = maxH - minH;
                if (diff < result) result = diff;
                lowIdx++;
            } else {
                highIdx++;
            }
        }

        System.out.print(result);
    }

    // BFS로 (0,0)에서 (N-1,N-1)로 갈 수 있는지 확인
    private static boolean checkPath() {
        if (map[0][0] < minH || map[0][0] > maxH) return false;
        if (map[N-1][N-1] < minH || map[N-1][N-1] > maxH) return false;

        for (int i = 0; i < N; i++) {
            Arrays.fill(visited[i], false);
        }

        int qStart = 0, qEnd = 0;
        queue[qEnd][0] = 0;
        queue[qEnd++][1] = 0;
        visited[0][0] = true;

        while (qStart < qEnd) {
            int r = queue[qStart][0];
            int c = queue[qStart++][1];

            if (r == N-1 && c == N-1) {
                return true;
            }

            for (int[] d : DIR) {
                int nr = r + d[0];
                int nc = c + d[1];
                if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
                if (visited[nr][nc]) continue;
                int val = map[nr][nc];
                if (val >= minH && val <= maxH) {
                    visited[nr][nc] = true;
                    queue[qEnd][0] = nr;
                    queue[qEnd++][1] = nc;
                }
            }
        }
        return false;
    }
}