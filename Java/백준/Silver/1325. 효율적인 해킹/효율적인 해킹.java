import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author python98
 * @description 그래프
 * @performance 메모리: ? KB, 동작 시간: ? ms
 */
public class Main {
    static int N, M, result;
    static int[] cnt;
    static boolean[] visited;
    static List<Integer>[] adjList;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        cnt = new int[N + 1];
        adjList = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            adjList[i] = new ArrayList<>();
        }
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            adjList[A].add(B);
        }
        for (int i = 1; i <= N; i++) {
            visited = new boolean[N + 1];
            visited[i] = true;
            dfs(i);
        }
        for(int i = 1; i <= N; i++) {
            result = Math.max(cnt[i], result);
        }
        for (int i = 1; i <= N; i++) {
            if (cnt[i] == result) {
                sb.append(i).append(" ");
            }
        }
        System.out.print(sb);
    }

    private static void dfs(int cur) {
        for (int i : adjList[cur]) {
            if (!visited[i]) {
                visited[i] = true;
                cnt[i]++;
                dfs(i);
            }
        }
    }
}