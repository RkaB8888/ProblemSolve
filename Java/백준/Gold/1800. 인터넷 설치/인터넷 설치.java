import java.io.*;
import java.util.*;

/**
 * @description 이분탐색 + BFS
 * @performance 메모리: 18,252 KB, 동작시간: 172 ms
 * @author python98
 */
public class Main {
	static int N, P, K;
	static int[] list, next, node, val;
	static int[] visited;
	static int[][] q = new int[10000][2];

	private static final InputStream in = System.in;
	private static int nextInt() throws IOException{
		int c, n;
		while((c = in.read())<=32);
		n = c & 15;
		while((c = in.read())>32){
			n = (n << 3) + (n<<1) + (c&15);
		}
		return n;
	}

	private static boolean bfs(int line){
		int front = 0, rear = 0;
		Arrays.fill(visited,K+1);

		q[rear][0] = 1;
		q[rear][1] = 0;
		rear++;
		visited[1] = 0;

		while(front<rear){
			int curNode = q[front][0];
			int curCnt = q[front][1];
			front++;
			if(curCnt > K) continue;
			if(curNode == N) return true;
			for(int idx = list[curNode] ; idx != -1 ; idx = next[idx]){
				int nextNode = node[idx];
				int nextCnt = val[idx]> line?curCnt+1:curCnt;
				if(visited[nextNode] > nextCnt) {
					visited[nextNode] = nextCnt;
					q[rear][0] = nextNode;
					q[rear][1] = nextCnt;
					rear++;
				}
			}
		}
		return false;
	}

	public static void main(String[] args) throws IOException {
		N = nextInt();
		P = nextInt();
		K = nextInt();

		list = new int[N+1];
		Arrays.fill(list, -1);
		next = new int[P<<1];
		node = new int[P<<1];
		val = new int[P<<1];

		int minCost = 0, maxCost = 0;
		for(int i = 0 ; i < P ; i++) {
			int i1 = i<<1;
			int i2 = i1+1;

			int a = nextInt();
			int b = nextInt();
			int c = nextInt();
			maxCost = Math.max(maxCost, c);

			next[i1] = list[a];
			list[a] = i1;
			node[i1] = b;
			val[i1] = c;

			next[i2] = list[b];
			list[b] = i2;
			node[i2] = a;
			val[i2] = c;
		}
		int result = -1;
		while(minCost <= maxCost){
			int line = (minCost+maxCost)>>1;
			visited = new int[N+1];
			if(bfs(line)){
				result = line;
				maxCost = line - 1;
			} else {
				minCost = line + 1;
			}
		}

		System.out.print(result);
	}
}