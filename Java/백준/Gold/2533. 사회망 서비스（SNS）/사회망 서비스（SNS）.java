import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * 
 * tree dp 메모리 ? KB 시간 ? ms
 * 원시 링크드 리스트로 최적화
 * @author python98
 *
 */
public class Main {
    static int N;
    static int[][] dp;
    static Node[] graph;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new int[2][N + 1]; // dp[0][i]: i번 노드가 adopter가 아닌 경우, dp[1][i]: i번 노드가 adopter인 경우
        graph = new Node[N + 1];
        visited = new boolean[N + 1];

        for (int i = 1; i < N; i++) {
            String[] input = br.readLine().split(" ");
            int from = Integer.parseInt(input[0]);
            int to = Integer.parseInt(input[1]);
            graph[from] = new Node(to, graph[from]);
            graph[to] = new Node(from, graph[to]);
        }

        dfs(1);
        System.out.print(Math.min(dp[0][1], dp[1][1]));
    }

    private static void dfs(int node) {
        visited[node] = true;
        dp[1][node] = 1; // node가 adopter인 경우, 기본값은 1

        for (Node neighbor = graph[node]; neighbor != null; neighbor = neighbor.next) {
            if (!visited[neighbor.num]) {
                dfs(neighbor.num);
                dp[0][node] += dp[1][neighbor.num];
                dp[1][node] += Math.min(dp[0][neighbor.num], dp[1][neighbor.num]);
            }
        }
    }

    private static class Node {
        int num;
        Node next;

        public Node(int num, Node next) {
            this.num = num;
            this.next = next;
        }
    }
}