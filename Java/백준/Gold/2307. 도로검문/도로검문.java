import java.io.*;
import java.util.*;

/**
 * @author python98
 * @description Dijkstra (O(N^2)) + Path Reconstruction
 * @performance 메모리: ? KB, 동작시간: ? ms
 */
public class Main {

    static final int INF = Integer.MAX_VALUE;

    static class Node {
        int end, weight;
        Node(int end, int weight) { this.end = end; this.weight = weight; }
    }

    // O(N^2) 다익스트라: 최단거리 + parent 기록
    static int dijkstraBase(ArrayList<Node>[] graph, int N, int[] parent) {
        int[] dist = new int[N];
        boolean[] vis = new boolean[N];
        Arrays.fill(dist, INF);
        dist[0] = 0;

        for (int iter = 0; iter < N; iter++) {
            int current = 0, min = INF;
            for (int v = 0; v < N; v++) {
                if (!vis[v] && dist[v] < min) { min = dist[v]; current = v; }
            }
            vis[current] = true;
            if (current == N - 1) break;

            for (int j = 0; j < graph[current].size(); j++) {
                Node nd = graph[current].get(j);
                if (!vis[nd.end] && dist[nd.end] > min + nd.weight) {
                    dist[nd.end] = min + nd.weight;
                    parent[nd.end] = current;
                }
            }
        }
        return dist[N - 1];
    }

    // 최단 경로의 각 간선을 하나씩 제외하며 재탐색 (단방향 스킵)
    static int rerunByBanningEachEdgeOnPath(ArrayList<Node>[] graph, int N, int[] parent) {
        int dest = N - 1;
        int best = Integer.MIN_VALUE;

        while (dest != 0) {
            int start = parent[dest];

            int[] dist = new int[N];
            boolean[] vis = new boolean[N];
            Arrays.fill(dist, INF);
            dist[0] = 0;

            for (int iter = 0; iter < N; iter++) {
                int current = 0, min = INF;
                for (int v = 0; v < N; v++) {
                    if (!vis[v] && dist[v] < min) { min = dist[v]; current = v; }
                }
                vis[current] = true;
                if (current == N - 1) break;

                for (int j = 0; j < graph[current].size(); j++) {
                    Node nd = graph[current].get(j);
                    if (current == start && nd.end == dest) continue;
                    if (!vis[nd.end] && dist[nd.end] > min + nd.weight) {
                        dist[nd.end] = min + nd.weight;
                    }
                }
            }

            best = Math.max(best, dist[N - 1]);
            dest = start;
        }

        return best;
    }

    static int toInt(String s){ return Integer.parseInt(s); }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = toInt(st.nextToken());
        int M = toInt(st.nextToken());

        ArrayList<Node>[] graph = new ArrayList[N];
        for (int i = 0; i < N; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = toInt(st.nextToken()) - 1;
            int b = toInt(st.nextToken()) - 1;
            int w = toInt(st.nextToken());
            graph[a].add(new Node(b, w));
            graph[b].add(new Node(a, w));
        }

        int[] parent = new int[N];
        int initial = dijkstraBase(graph, N, parent);
        int worst = rerunByBanningEachEdgeOnPath(graph, N, parent);

        if (worst == INF) System.out.println(-1);
        else System.out.println(worst - initial);
    }
}
