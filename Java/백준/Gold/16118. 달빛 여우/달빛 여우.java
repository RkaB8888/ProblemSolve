import java.io.*;
import java.util.*;

/**
 * @description 다익스트라 + PQ
 * @performance 메모리: ? KB, 동작시간: ? ms
 * @author python98
 */
public class Main {

	static class Node implements Comparable<Node> {
		int idx, dist, state;

		public Node(int idx, int dist, int state) {
			this.idx = idx;
			this.dist = dist;
			this.state = state;
		}

		@Override
		public int compareTo(Node o){
			return Integer.compare(this.dist, o.dist);
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		N++;

		List<Node>[] adjList = new ArrayList[N];
		for(int i = 0 ; i < N ; i++) {
			adjList[i] = new ArrayList<>();
		}
		for(int i = 0 ; i < M ; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());

			adjList[a].add(new Node(b,d,0));
			adjList[b].add(new Node(a,d,0));
		}

		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(1,0,0));

		int[][] visited = new int[N][3];
		for(int i = 0 ; i < N ; i++) {
			Arrays.fill(visited[i],Integer.MAX_VALUE);
		}
		visited[1][0] = 0;

		while(!pq.isEmpty()) { // 여우 먼저
			Node cur = pq.poll();

			if(visited[cur.idx][0] < cur.dist) continue;

			for(Node next : adjList[cur.idx]) {
				int nextDist = cur.dist + (next.dist<<1);
				if(visited[next.idx][0] > nextDist) {
					visited[next.idx][0] = nextDist;
					pq.add(new Node(next.idx, nextDist, 0));
				}
			}
		}

		pq.clear();
		pq.add(new Node(1, 0, 1)); // state 1은 느리게 도착한 것
		visited[1][1] = 0;

		while(!pq.isEmpty()) { // 여우 먼저
			Node cur = pq.poll();
			int nextState = cur.state==1?2:1;

			if(visited[cur.idx][cur.state] < cur.dist) continue;

			for(Node next : adjList[cur.idx]) {
				int nextDist = cur.dist + (nextState==2?next.dist:next.dist<<2);
				if(visited[next.idx][nextState] > nextDist) {
					visited[next.idx][nextState] = nextDist;
					pq.add(new Node(next.idx, nextDist, nextState));
				}
			}
		}
		int result = 0;
		for(int i = 2 ; i < N ; i++) {
			if(visited[i][0] < visited[i][1] && visited[i][0] < visited[i][2]) result++;
		}
		System.out.print(result);
	}
}