import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 
 * @author python98 MST ? KB ? ms
 */
public class Main {
	static int V, E, group[], result;
	static Node[] adjList;

	static class Node implements Comparable<Node> {
		int from, to, val;
		Node next;

		public Node(int from, int to, int val, Node next) {
			this.from = from;
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
		adjList = new Node[V + 1];
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int val = Integer.parseInt(st.nextToken());

			adjList[from] = new Node(from, to, val, adjList[from]);
			adjList[to] = new Node(to, from, val, adjList[to]);
		}
		makeSet();
		process();

		System.out.print(result);
	}

	private static void process() {
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		for(Node cur = adjList[1] ; cur!=null ; cur = cur.next) {
			pq.add(cur);
		}
		int cnt = 1;
		while(!pq.isEmpty()) {
			if(cnt==V) return;
			Node cur = pq.poll();
			if(union(cur.from,cur.to)) {
				result+=cur.val;
				cnt++;
				for(Node node = adjList[cur.to] ; node!=null ; node = node.next) {
					pq.add(node);
				}
			}
		}
	}

	private static void makeSet() {
		group = new int[V+1];
		for(int i = 1 ; i <= V ; i++) {
			group[i] = i;
		}
	}

	private static int find(int a) {
		if(group[a]==a) return a;
		return group[a]=find(group[a]);
	}

	private static boolean union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		
		if(rootA==rootB) return false;
		
		group[rootA] = rootB;
		return true;
	}
}