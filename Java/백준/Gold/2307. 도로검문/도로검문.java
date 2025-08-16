import java.io.*;
import java.util.*;

/**
 * @author python98
 * @description
 * @performance 메모리: ? KB, 동작시간: ? ms
 */
public class Main {
    static int N, M;
    static List<int[]>[] adjList;

    static class PathResult{
        List<Integer> path;
        int time;

        PathResult(List<Integer> path, int time){
            this.path = path;
            this.time = time;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        adjList = new ArrayList[N+1];
        for(int i = 1; i <= N; i++){
            adjList[i] = new ArrayList<>();
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            adjList[a].add(new int[]{b, t});
            adjList[b].add(new int[]{a, t});
        }
        //최단 경로
        int start = 1, end = N;
        PathResult pathResult = dijkstraWithPath(start, end);

        int maxDelay = 0;
        for(int i = 0 ; i < pathResult.path.size()-1 ; i++) {
            int curNode = pathResult.path.get(i);
            int nextNode = pathResult.path.get(i+1);
            int nextTime = exceptDijkstra(start, end, curNode, nextNode);
            if(nextTime == Integer.MAX_VALUE) {
                System.out.print(-1);
                return;
            }
            int delay = nextTime - pathResult.time;
            if(delay > maxDelay) maxDelay = delay;
        }
        System.out.print(maxDelay);
    }

    private static int exceptDijkstra(int start, int end, int from, int to) {
        int[] time = new int[end+1];
        Arrays.fill(time, Integer.MAX_VALUE);

        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->a[1]-b[1]);
        pq.offer(new int[] {start, 0});
        time[start] = 0;

        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            int curNode = cur[0];
            int curTime = cur[1];

            for(int[] next : adjList[curNode]) {
                int nextNode = next[0];
                int nextTime = curTime + next[1];
                if(curNode == from && nextNode == to || curNode == to && nextNode == from) continue;
                if(nextTime >= time[end]) continue;
                if(time[nextNode] > nextTime) {
                    time[nextNode] = nextTime;
                    pq.offer(new int[] {nextNode, nextTime});
                }
            }
        }
        return time[end];
    }

    private static PathResult dijkstraWithPath(int start, int end) {
        int[] time = new int[N+1];
        int[] preNode = new int[N+1];
        Arrays.fill(time, Integer.MAX_VALUE);
        Arrays.fill(preNode, -1);

        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->a[1]-b[1]);
        pq.offer(new int[] {start, 0});
        time[start] = 0;

        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            int curNode = cur[0];
            int curTime = cur[1];

            for(int[] next : adjList[curNode]) {
                int nextNode = next[0];
                int nextTime = curTime + next[1];
                if(nextTime >= time[end]) continue;
                if(time[nextNode] > nextTime) {
                    time[nextNode] = nextTime;
                    preNode[nextNode] = curNode;
                    pq.offer(new int[] {nextNode, nextTime});
                }
            }
        }

        List<Integer> path = new ArrayList<>();
        int curNode = end;
        while(curNode != start) {
            path.add(curNode);
            curNode = preNode[curNode];
        }
        path.add(start);
        return new PathResult(path,time[end]);
    }

}