import java.io.*;
import java.util.*;

/**
 * @description 이 클래스에 대한 동작 설명
 * @performance 메모리: ? KB, 동작시간: ? ms
 * @author python98
 */
public class Main {
	static final int INF = Integer.MAX_VALUE;
	static int N, M, start, end;
	static int[] minTotal;
	static int[][] vals;
	static Node[] nodes;

	static class User {
		int total;
		int nodeNum;
		List<Integer> hist;

		User(int total, int nodeNum, List<Integer> hist) {
			this.total = total;
			this.nodeNum = nodeNum;
			this.hist = hist;
		}
	}

	static class Node {
		int num;
		Node next;

		Node(int num, Node next) {
			this.num = num;
			this.next = next;
		}
	}

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		vals = new int[N + 1][N + 1];
		minTotal = new int[N + 1];
		Arrays.fill(minTotal, INF);
		nodes = new Node[N + 1];
		for (int i = 1; i <= N; i++) {
			Arrays.fill(vals[i], INF);
			nodes[i] = new Node(i, null);
		}

		StringTokenizer st = null;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int val = Integer.parseInt(st.nextToken());
			if (vals[from][to] <= val)
				continue;
			nodes[from].next = new Node(to, nodes[from].next);
			vals[from][to] = val;
		}

		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());

		User result = dijkstra();
		if (result != null) {
			sb.append(result.total).append('\n').append(result.hist.size()).append('\n');
			for (int num : result.hist) {
				sb.append(num).append(' ');
			}
		}
		System.out.print(sb);
	}

	private static User dijkstra() {
		PriorityQueue<User> pq = new PriorityQueue<>((a, b) -> a.total - b.total);
		List<Integer> temp = new ArrayList<Integer>();
		temp.add(start);
		minTotal[start] = 0;
		pq.add(new User(0, start, temp));

		while (!pq.isEmpty()) {
			User cur = pq.poll();
			int curNodeNum = cur.nodeNum;
			int totalVal = cur.total;
			List<Integer> curHist = cur.hist;
			if (curNodeNum == end) {
				return cur;
			}
			for (Node next = nodes[curNodeNum].next; next != null; next = next.next) {
				int nextNodeNum = next.num;
				int nextVal = totalVal + vals[curNodeNum][nextNodeNum];
				if(nextVal >= minTotal[nextNodeNum]) continue;
				minTotal[nextNodeNum] = nextVal;
				List<Integer> nextHist = new ArrayList<>(curHist);
				nextHist.add(nextNodeNum);
				pq.add(new User(nextVal,nextNodeNum,nextHist));
			}
		}
		return null;
	}
}