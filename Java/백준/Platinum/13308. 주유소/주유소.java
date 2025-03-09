import java.io.*;
import java.util.*;

/**
 * @description ?
 * @performance 메모리: ? KB, 동작시간: ? ms
 * @author python98
 */
public class Main {
	static final int INF = Integer.MAX_VALUE;
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
		int node, cost, currentMin;

		State(int node, int cost, int currentMin) {
			this.node = node;
			this.cost = cost;
			this.currentMin = currentMin;
		}

		public int compareTo(State other) {
			return this.cost - other.cost;
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

		// 인접 리스트 초기화 (양방향 그래프)
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

		int result = dijkstra(1, N);
		System.out.print(result);
	}

	private static int dijkstra(int start, int end) {
		PriorityQueue<State> pq = new PriorityQueue<>();

		Map<Integer, Integer>[] best = new HashMap[N + 1];
		for (int i = 1; i <= N; i++) {
			best[i] = new HashMap<>();
		}

		State init = new State(start, 0, gasPrice[start]);
		pq.add(init);
		best[start].put(gasPrice[start], 0);

		while (!pq.isEmpty()) {
			State cur = pq.poll();
			int node = cur.node, cost = cur.cost, curMin = cur.currentMin;
			if (node == end)
				return cost;
			// 현재 상태보다 이미 더 좋은 상태가 있다면 건너뜁니다.
			if (best[node].getOrDefault(curMin, INF) < cost)
				continue;

			// 인접 노드로 확장
			for (Edge edge : graph[node]) {
				int next = edge.to;
				int newCost = cost + edge.dist * curMin;
				// 다음 주유소에서의 최저 가격은 현재까지의 최저와 다음 주유소 가격 중 더 낮은 값
				int newMin = Math.min(curMin, gasPrice[next]);
				// 만약 next 노드에서 newMin 상태로 도달하는 더 좋은 비용이 있다면 넘어갑니다.
				if (best[next].getOrDefault(newMin, INF) <= newCost)
					continue;
				best[next].put(newMin, newCost);
				pq.add(new State(next, newCost, newMin));
			}
		}
		return INF;
	}

}