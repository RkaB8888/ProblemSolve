import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static final int[][] DIR = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int N, minH, maxH, result = 200;
    static int[][] map;
    static List<Integer> hList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        Set<Integer> heights = new HashSet<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int val = Integer.parseInt(st.nextToken());
                map[i][j] = val;
                heights.add(val);
            }
        }

        hList = new ArrayList<>(heights);
        Collections.sort(hList);

        int startVal = map[0][0];
        int endVal = map[N-1][N-1];

        int lowIdx = 0, highIdx = 0;
        int hListLen = hList.size();

        while (highIdx < hListLen && hList.get(highIdx) < startVal) {
            highIdx++;
        }

        // 투 포인터 시작
        while (lowIdx <= highIdx && highIdx < hListLen) {
            minH = hList.get(lowIdx);
            if (startVal < minH || endVal < minH) {
                break;
            }

            maxH = hList.get(highIdx);
            if (startVal > maxH || endVal > maxH) {
                highIdx++;
                continue;
            }

            // BFS로 경로 체크
            if (isPossible()) {
                result = Math.min(result, maxH - minH);
                lowIdx++;
            } else {
                highIdx++;
            }
        }

        System.out.print(result);
    }

    private static boolean isPossible() {
        if (map[0][0] < minH || map[0][0] > maxH) return false;
        if (map[N-1][N-1] < minH || map[N-1][N-1] > maxH) return false;

        boolean[][] visited = new boolean[N][N];
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0, 0});
        visited[0][0] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0], c = cur[1];
            if (r == N-1 && c == N-1) {
                return true;
            }

            for (int[] d : DIR) {
                int nr = r + d[0], nc = c + d[1];
                if (nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc]) continue;

                int val = map[nr][nc];
                if (val >= minH && val <= maxH) {
                    visited[nr][nc] = true;
                    q.offer(new int[]{nr, nc});
                }
            }
        }

        return false;
    }
}