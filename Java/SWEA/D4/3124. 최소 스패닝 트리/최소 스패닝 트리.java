import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 
 */
public class Solution {
    static int V, E;
    static boolean[] Visited;
    static List<Node>[] list;

    static class Node {
        int to, w;
        public Node(int to, int w) {
            this.to = to;
            this.w = w;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            list = new List[V];
            Visited = new boolean[V];

            for (int i = 0; i < V; i++) {
                list[i] = new ArrayList<>();
            }

            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken()) - 1;
                int to = Integer.parseInt(st.nextToken()) - 1;
                int weight = Integer.parseInt(st.nextToken());
                list[from].add(new Node(to, weight));
                list[to].add(new Node(from, weight));
            }
            sb.append("#").append(tc).append(" ").append(Prim(0)).append("\n");
        }
        System.out.println(sb);
    }

    public static long Prim(int start) {
        long result = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.w - b.w);
        int[] minVal = new int[V];
        Arrays.fill(minVal, Integer.MAX_VALUE);
        minVal[start] = 0;
        
        pq.offer(new Node(start, 0));
        
        while (!pq.isEmpty()) {
            Node next = pq.poll();
            if (Visited[next.to]) continue;

            Visited[next.to] = true;
            result += next.w;

            for (Node edge : list[next.to]) {
                if (!Visited[edge.to] && minVal[edge.to] > edge.w) {
                    minVal[edge.to] = edge.w;
                    pq.offer(new Node(edge.to, edge.w));
                }
            }
        }
        return result;
    }
}