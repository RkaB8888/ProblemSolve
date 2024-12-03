import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * PQ 메모리 ? KB 시간 ? ms
 * 
 * @author python98
 *
 */
public class Main {
	static final int INF = Integer.MAX_VALUE;
	static int N, M, S, D, result;
	static Node[] nodes;
	static List<Integer>[] prev;
	static boolean[][] doNotUse;

	static class Node {
		int num, dist;
		Node next;

		public Node(int num, int dist, Node next) {
			this.num = num;
			this.dist = dist;
			this.next = next;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			if (N == 0 && M == 0)
				break;
			nodes = new Node[N];
			prev = new ArrayList[N];
			doNotUse = new boolean[N][N];
			st = new StringTokenizer(br.readLine());
			S = Integer.parseInt(st.nextToken());
			D = Integer.parseInt(st.nextToken());
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int U = Integer.parseInt(st.nextToken());
				int V = Integer.parseInt(st.nextToken());
				int P = Integer.parseInt(st.nextToken());
				nodes[U] = new Node(V, P, nodes[U]);
			}

			if(bfs1()) {
				removeMinPath();
				sb.append(bfs2()).append('\n');
			} else {
				sb.append(-1).append('\n');
			}
		}
		System.out.print(sb);
	}

	private static void removeMinPath() {
		boolean[] visited = new boolean[N];
		Queue<Integer> q = new ArrayDeque<>();
		q.add(D);
		visited[D] = true;
		
		while(!q.isEmpty()) {
			int curNode = q.poll();
			for(int preNode : prev[curNode]) {
				doNotUse[preNode][curNode] = true;
				if(!visited[preNode]) {
					q.add(preNode);
					visited[preNode] = true;
				}
			}
		}
	}

	private static boolean bfs1() {
		int[] dist = new int[N];
		Arrays.fill(dist, INF);
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
		pq.add(new int[] {S,0});
		dist[S] = 0;
		
		for(int i = 0 ; i < N ;i++) {
			prev[i] = new ArrayList<Integer>();
		}

		while (!pq.isEmpty()) {
			int[] curInfo = pq.poll();
			int curNode = curInfo[0];
			int curDist = curInfo[1];
			
			if(curDist>dist[curNode]) continue;
			
			for (Node next = nodes[curNode]; next != null; next = next.next) {
				int nextDist = curDist + next.dist;
				int nextNode = next.num;
				if (dist[nextNode]> nextDist) {
					dist[nextNode] = nextDist;
					prev[nextNode].clear();
					prev[nextNode].add(curNode);
					pq.add(new int[] {nextNode,nextDist});
				} else if(dist[nextNode]== nextDist) {
					prev[nextNode].add(curNode);
				}
			}
		}
		return dist[D] != INF;
	}

	private static int bfs2() {
		int[] dist = new int[N];
		Arrays.fill(dist, INF);
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
		pq.add(new int[] {S,0});
		dist[S] = 0;
		
		while (!pq.isEmpty()) {
			int[] curInfo = pq.poll();
			int curNode = curInfo[0];
			int curDist = curInfo[1];
			
			if(curDist>dist[curNode]) continue;
			
			for (Node next = nodes[curNode]; next != null; next = next.next) {
				int nextDist = curDist + next.dist;
				int nextNode = next.num;
				if(doNotUse[curNode][nextNode]) continue;
				if (dist[nextNode]> nextDist) {
					dist[nextNode] = nextDist;
					pq.add(new int[] {nextNode,nextDist});
				}
			}
		}
		if(dist[D] != INF) {
	        return dist[D];
	    } else {
	        return -1;
	    }
	}
}