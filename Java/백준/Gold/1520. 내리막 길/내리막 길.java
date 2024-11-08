import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * DFS 메모이제이션 백트래킹 메모리 39,724 KB 시간 300 ms
 */
public class Main {
    static int M, N;
    static int[][] map, cntMap;
    static int[][] drdc = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[M][N];
        cntMap = new int[M][N];

        // Map과 cntMap 초기화
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                cntMap[i][j] = -1; // 방문 전 상태를 -1로 초기화
            }
        }

        System.out.print(dfs(0, 0));
    }

    private static int dfs(int R, int C) {
        if (R == M - 1 && C == N - 1) return 1; // 도착지에 도달하면 경로 1 반환
        if (cntMap[R][C] != -1) return cntMap[R][C]; // 이미 방문한 경우, 저장된 경로 수 반환

        cntMap[R][C] = 0; // 탐색 시작 표시
        for (int[] dir : drdc) {
            int nR = R + dir[0];
            int nC = C + dir[1];

            // 이동 가능한 위치와 조건 검사
            if (nR >= 0 && nC >= 0 && nR < M && nC < N && map[nR][nC] < map[R][C]) {
                cntMap[R][C] += dfs(nR, nC);
            }
        }

        return cntMap[R][C];
    }
}