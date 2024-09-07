import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int N, M, result;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int TC = Integer.parseInt(br.readLine()); // 테스트 케이스 수
        for (int tc = 1; tc <= TC; tc++) {
            N = Integer.parseInt(br.readLine());
            M = Integer.parseInt(br.readLine());
            map = new int[N + 1][N + 1];

            // map 초기화: 행과 열의 0번 인덱스에 -1로 표시
            for (int i = 1; i <= N; i++) {
                map[i][0] = -1;
                map[0][i] = -1;
            }

            result = 0;

            // M개의 간선 입력 받기
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                map[u][v] = 1; // 연결된 간선 표시
            }

            // 각각의 행과 열에 대해 dfs 수행
            for (int i = 1; i <= N; i++) {
                if (map[i][0] == -1) {
                    dfs(i, true);  // 행 기반 탐색
                }
                if (map[0][i] == -1) {
                    dfs(i, false); // 열 기반 탐색
                }
                if (map[i][0] + map[0][i] == N - 1) {
                    result++; // 모든 노드가 연결된 경우 결과 카운트 증가
                }
            }

            sb.append('#').append(tc).append(' ').append(result).append('\n');
        }
        System.out.println(sb);
    }

    // 하나의 dfs 함수로 통합, isRow가 true이면 행을 탐색, false이면 열 탐색
    public static void dfs(int idx, boolean isRow) {
        if (isRow) {
            map[idx][0] = 0;
            for (int i = 1; i <= N; i++) {
                if (map[idx][i] == 0) continue;
                if (map[i][0] == -1) {
                    dfs(i, true); // 행을 기준으로 dfs
                }
                if (map[i][0] != 0) {
                    for (int j = 1; j <= N; j++) {
                        map[idx][j] |= map[i][j];
                    }
                }
            }
            for (int i = 1; i <= N; i++) {
                map[idx][0] += map[idx][i];
            }
        } else { // 열 기준 탐색
            map[0][idx] = 0;
            for (int i = 1; i <= N; i++) {
                if (map[i][idx] == 0) continue;
                if (map[0][i] == -1) {
                    dfs(i, false); // 열을 기준으로 dfs
                }
                if (map[0][i] != 0) {
                    for (int j = 1; j <= N; j++) {
                        map[j][idx] |= map[j][i];
                    }
                }
            }
            for (int i = 1; i <= N; i++) {
                map[0][idx] += map[i][idx];
            }
        }
    }
}