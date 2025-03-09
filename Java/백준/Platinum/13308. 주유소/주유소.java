import java.io.*;
import java.util.*;

/**
 * @description ?
 * @performance 메모리: ? KB, 동작시간: ? ms
 * @author python98
 */
public class Main {
	static final long INF = Long.MAX_VALUE;
	static int N, M;
	static List<Edge>[] graph;
	static int[] gasPrice;

	static class Edge {
		int to, dist;

		Edge(int to, int dist) {
			this.to = to;
			this.dist = dist;
		}
	}

	// 상태: 현재 노드, 현재까지의 누적 비용, 그리고 지금까지 만난 주유소 중 최저 가격
	static class State implements Comparable<State> {
		int node, currentMin;
		long cost;
		
		State(int node, long cost, int currentMin) {
			this.node = node;
			this.cost = cost;
			this.currentMin = currentMin;
		}

		public int compareTo(State other) {
			return Long.compare(this.cost, other.cost);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		gasPrice = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			gasPrice[i] = Integer.parseInt(st.nextToken());
		}

		graph = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			graph[u].add(new Edge(v, d));
			graph[v].add(new Edge(u, d));
		}

		long result = dijkstra(1, N);
		System.out.print(result);
	}

	private static long dijkstra(int start, int end) {
		PriorityQueue<State> pq = new PriorityQueue<>();

		Map<Integer, Long>[] best = new HashMap[N + 1];
		for (int i = 1; i <= N; i++) {
			best[i] = new HashMap<>();
		}

		State init = new State(start, 0, gasPrice[start]);
		pq.add(init);
		best[start].put(gasPrice[start], 0L);

		while (!pq.isEmpty()) {
			State cur = pq.poll();
			int node = cur.node, curMin = cur.currentMin;
			long cost = cur.cost;
			if (node == end)
				return cost;
			if (best[node].getOrDefault(curMin, INF) < cost)
				continue;

			for (Edge edge : graph[node]) {
				int next = edge.to;
				long newCost = cost + (long) edge.dist * curMin;
				int newMin = Math.min(curMin, gasPrice[next]);
				if (best[next].getOrDefault(newMin, INF) <= newCost)
					continue;
				best[next].put(newMin, newCost);
				pq.add(new State(next, newCost, newMin));
			}
		}
		return INF;
	}
}