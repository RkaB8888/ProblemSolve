import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 메모리 ? KB 시간 ? ms
 * 
 * @author python98
 */
public class Main {
	static int N, result, zeroCnt;
	static boolean[] cross_ldru;
	static int[] crossCnt;
	static Node[] rootNodes;

	static class Node {
		int r, c;
		Node nextNode;

		public Node(int r, int c, Node nextNode) {
			this.r = r;
			this.c = c;
			this.nextNode = nextNode;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		cross_ldru = new boolean[2 * N - 1]; // i+j
		crossCnt = new int[2 * N - 1];
		rootNodes = new Node[2 * N - 1];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int num = Integer.parseInt(st.nextToken());
				if (num == 1) {
					rootNodes[i - j + N - 1] = new Node(i, j, rootNodes[i - j + N - 1]);
					crossCnt[i + j]++;
				} else {
					zeroCnt++;
				}
			}
		}
		if (zeroCnt == 0) {
			System.out.print(2 * N - 2);
		} else {
			for (int i = 0, iEnd = 2 * N - 1; i < iEnd; i++) {
				if (crossCnt[i] == 0)
					cross_ldru[i] = true;
			}

			result = 0;
			dfs(0, 0);
			System.out.print(result);
		}
	}

	private static void dfs(int cnt, int sum) {
		if (result >= sum + 2 * N - 1 - cnt) {
			return;
		}
		if (cnt == 2 * N - 1) {
			result = Math.max(result, sum);
			return;
		}
//		if (cross_ldru[cnt]) {
//			dfs(cnt + 1, sum);
//			return;
//		}

		boolean found = false;
		Node node = rootNodes[cnt];
		for (Node cur = node; cur != null; cur = cur.nextNode) {
			int i = cur.r, j = cur.c;
			if (!cross_ldru[i + j]) {
				cross_ldru[i + j] = true;
				found = true;
				dfs(cnt + 1, sum + 1);
				cross_ldru[i + j] = false;
			}
		}
		if (!found) {
			dfs(cnt + 1, sum);
		}
	}
}