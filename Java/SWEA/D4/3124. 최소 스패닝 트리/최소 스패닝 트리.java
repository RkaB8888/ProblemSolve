import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 메모리:231,244kb, 시간:3,071ms
 * 우선순위큐를 사용함
 */
public class Solution {
	static int V, E;
	static boolean[] Visited;
	static List<Node>[] list;
	static long result;

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
			result = 0;

			for (int i = 0; i < V; i++) {
				list[i] = new ArrayList<>();
			}

			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken()) - 1;
				int to = Integer.parseInt(st.nextToken()) - 1;
				int weight = Integer.parseInt(st.nextToken());
				list[from].add(new Node( to, weight ));
				list[to].add(new Node( from, weight ));
			}
			Prim(0);
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}
		System.out.println(sb);
	}

	public static void Prim(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<Node>((a, b) -> a.w - b.w);
		Visited[start] = true;
		int nodeCnt = 1;
		int[] minVal = new int[V];
		Arrays.fill(minVal, Integer.MAX_VALUE);
		minVal[start] = 0;
		for (Node edge : list[start]) {
			if(minVal[edge.to]<=edge.w) continue;
			minVal[edge.to] = edge.w;
			pq.offer(edge);
		}
		while (!pq.isEmpty() && nodeCnt < V) {
			Node next = pq.poll();

			if (Visited[next.to])
				continue;

			Visited[next.to] = true;
			nodeCnt++;
			result += next.w;
			for (Node edge : list[next.to]) {
				if (Visited[edge.to]) continue;
				if(minVal[edge.to]<=edge.w) continue;
				minVal[edge.to] = edge.w;
				pq.offer(edge);
			}
		}
	}

}