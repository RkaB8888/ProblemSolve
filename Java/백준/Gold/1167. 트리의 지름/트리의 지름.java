import java.io.*;
import java.util.*;

/**
 * @author python98
 * @description Tree Diameter + BFS x2
 * @performance 메모리: ? KB, 동작시간: ? ms
 */
public class Main {
    static int V, edgeIdx;
    static int[] head, to, w, next, dist;
    static boolean[] vis;

    static void addEdge(int u, int v, int cost) {
        to[edgeIdx] = v;
        w[edgeIdx] = cost;
        next[edgeIdx] = head[u];
        head[u] = edgeIdx++;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        V = Integer.parseInt(br.readLine());

        int M = 2 * (V - 1);
        head = new int[V + 1];
        Arrays.fill(head, -1);
        to = new int[M];
        w = new int[M];
        next = new int[M];

        for (int i = 0; i < V; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            while (true) {
                int toNode = Integer.parseInt(st.nextToken());
                if (toNode == -1) break;
                int cost = Integer.parseInt(st.nextToken());
                addEdge(from, toNode, cost);
            }
        }

        vis = new boolean[V + 1];
        dist = new int[V + 1];
        int A = bfsFar();

        int diameter = bfsDist(A);

        System.out.print(diameter);
    }

    static private int bfsFar() {
        Arrays.fill(vis, false);
        Arrays.fill(dist, 0);
        ArrayDeque<Integer> q = new ArrayDeque<>();
        vis[1] = true;
        q.add(1);

        int far = 1;
        while (!q.isEmpty()) {
            int u = q.poll();
            if (dist[u] > dist[far]) far = u;
            process(q, u);
        }
        return far;
    }

    static int bfsDist(int start) {
        Arrays.fill(vis, false);
        Arrays.fill(dist, 0);
        ArrayDeque<Integer> q = new ArrayDeque<>();
        vis[start] = true;
        q.add(start);

        int best = 0;
        while (!q.isEmpty()) {
            int u = q.poll();
            if (dist[u] > best) best = dist[u];
            process(q, u);
        }
        return best;
    }

    private static void process(Queue<Integer> q, int u) {
        for (int e = head[u]; e != -1; e = next[e]) {
            int v = to[e];
            if (vis[v]) continue;
            vis[v] = true;
            dist[v] = dist[u] + w[e];
            q.add(v);
        }
    }
}