import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 
 * @author python98 프림 ? KB ? ms
 */
public class Main {
	static int V, E, result;
	static boolean visited[];
	static Node[] adjList;

	static class Node implements Comparable<Node> {
		int to, val;
		Node next;

		public Node(int to, int val, Node next) {
			this.to = to;
			this.val = val;
			this.next = next;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(val, o.val);
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		visited = new boolean[V + 1];
		adjList = new Node[V + 1];
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int val = Integer.parseInt(st.nextToken());

			adjList[from] = new Node(to, val, adjList[from]);
			adjList[to] = new Node(from, val, adjList[to]);
		}
		process();

		System.out.print(result);
	}

	private static void process() {
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		for (Node cur = adjList[1]; cur != null; cur = cur.next) {
			pq.add(cur);
		}
		visited[1] = true;
		int cnt = 1;
		while (!pq.isEmpty()) {
			if (cnt == V)
				return;
			Node cur = pq.poll();
			if (visited[cur.to])
				continue;
			visited[cur.to] = true;
			result += cur.val;
			cnt++;
			for (Node node = adjList[cur.to]; node != null; node = node.next) {
				pq.add(node);

			}
		}
	}
}