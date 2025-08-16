import java.io.*;
import java.util.*;

/**
 * @author python98
 * @description Dijkstra + PriorityQueue
 * @performance 메모리: 55,408 KB, 동작시간: 660 ms
 */
public class Main {

    static final int INF = 1_000_000_000;

    static int N, M;
    static List<int[]>[] adj;

    static class PathResult{
        final List<Integer> path;
        final int time;

        PathResult(List<Integer> path, int time){
            this.path = path;
            this.time = time;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        adj = new ArrayList[N+1];
        for(int i = 1; i <= N; i++) adj[i] = new ArrayList<>();

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            adj[a].add(new int[]{b, t});
            adj[b].add(new int[]{a, t});
        }

        PathResult base = dijkstraWithPath(1, N);
        if (base.time == INF) { // 도달 불가
            System.out.print(-1);
            return;
        }

        int maxDelay = 0;
        for(int i = 0 ; i < base.path.size()-1 ; i++) {
            int u = base.path.get(i);
            int v = base.path.get(i+1);
            int time = dijkstraDist(1, N, u, v);
            if(time == INF) {
                System.out.print(-1);
                return;
            }
            maxDelay = Math.max(maxDelay, time - base.time);
        }
        System.out.print(maxDelay);
    }

    static int dijkstraDist(int start, int end, int banU, int banV) {
        int[] time = new int[N+1];
        Arrays.fill(time, INF);
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->Integer.compare(a[1], b[1]));
        time[start] = 0;
        pq.offer(new int[] {start, 0});

        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            int u = cur[0], t = cur[1];
            if(t != time[u]) continue;
            if(u == end) break;

            for(int[] next : adj[u]) {
                int v = next[0];
                int w = next[1];
                if((u == banU && v == banV) || (u == banV && v == banU)) continue;
                int nt = t + w;
                if(time[v] > nt) {
                    time[v] = nt;
                    pq.offer(new int[] {v, nt});
                }
            }
        }
        return time[end];
    }

    private static PathResult dijkstraWithPath(int start, int end) {
        int[] time = new int[N+1];
        int[] preNode = new int[N+1];
        Arrays.fill(time, INF);
        Arrays.fill(preNode, -1);

        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->Integer.compare(a[1], b[1]));
        time[start] = 0;
        pq.offer(new int[] {start, 0});

        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            int u = cur[0], t = cur[1];
            if(t != time[u]) continue;
            if(u == end) break;

            for(int[] next : adj[u]) {
                int v = next[0], w = next[1];
                int nt = t + w;
                if(time[v] > nt) {
                    time[v] = nt;
                    preNode[v] = u;
                    pq.offer(new int[] {v, nt});
                }
            }
        }

        if (time[end] == INF) return new PathResult(Collections.emptyList(), INF);

        LinkedList<Integer> path = new LinkedList<>();
        for(int v = end ; v != -1 ; v = preNode[v]) path.addFirst(v);
        return new PathResult(path,time[end]);
    }

}