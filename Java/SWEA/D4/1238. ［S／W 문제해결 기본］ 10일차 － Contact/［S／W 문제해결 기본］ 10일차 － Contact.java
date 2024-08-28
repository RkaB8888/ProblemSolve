import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 메모리:19,328KB, 시간:125ms
 */
public class Solution {
	static int L;
	static int startNode;
	static List<Integer>[] adjList;
	static boolean[] Contact;
	static int maxNum;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = 10; // 테스트케이스
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			L = Integer.parseInt(st.nextToken());
			startNode = Integer.parseInt(st.nextToken());
			L /= 2;
			adjList = new ArrayList[101];
			for (int i = 0; i < 101; i++) {
				adjList[i]= new ArrayList<Integer>();
			}
			Contact = new boolean[101];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < L; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				adjList[from].add(to);
			}

			bfs();
			sb.append('#').append(tc).append(' ').append(maxNum).append('\n');
		}
		System.out.println(sb);
	}

	public static void bfs() {
		Queue<Integer> q = new ArrayDeque<>();
		q.add(startNode);
		Contact[startNode] = true;

		while (!q.isEmpty()) {
			int size = q.size();
			int num = 0;
			while(--size>=0) {
				int curNode = q.poll();
				if(curNode>num) num = curNode;
				int adjSize = adjList[curNode].size();
				while(--adjSize>=0) {
					int nextNode = adjList[curNode].get(adjSize);
					if(Contact[nextNode])continue;
					q.add(nextNode);
					Contact[nextNode] = true;
				}
			}
			maxNum = num;
		}
	}

}