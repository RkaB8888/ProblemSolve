import java.io.*;
import java.util.*;

/**
 * @description DFS
 * @performance 메모리: ? KB, 동작시간: ? ms
 * @author python98
 */
public class Main {
	static int n, maxEat, start;
	static int[] fruits, branchCnt;
	static List<Integer>[] adjList;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		n = Integer.parseInt(br.readLine());
		fruits = new int[n + 1];
		branchCnt = new int[n + 1];
		adjList = new ArrayList[n + 1];
		visited = new boolean[n + 1];
		for (int i = 0; i <= n; i++) {
			adjList[i] = new ArrayList<>();
		}
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) {
			fruits[i] = Integer.parseInt(st.nextToken());
		}
		for (int i = 1; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			adjList[from].add(to);
			adjList[to].add(from);
			branchCnt[from]++;
			branchCnt[to]++;
		}
		if(n==1) {
			sb.append(fruits[1]).append(" 1");
			System.out.print(sb);
			return;
		}
		List<Integer> startList = new ArrayList<Integer>();
		for (int i = 1; i <= n; i++) {
			if (branchCnt[i] == 1) {
				startList.add(i);
			}
		}
		start = startList.get(0);
		for (int i : startList) {
			visited[i] = true;
			dfs(i, i, fruits[i]);
		}
		sb.append(maxEat).append(" ").append(start);
		System.out.print(sb);
	}

	private static void dfs(int curNode, int startNode, int eat) {
		if (maxEat < eat) {
			maxEat = eat;
			start = Math.min(startNode, curNode);
		} else if(maxEat==eat) {
			start = Math.min(start, curNode);
			start = Math.min(start, startNode);
		}

		for (int next : adjList[curNode]) {
			if (visited[next])
				continue;
			visited[next] = true;
			dfs(next,startNode,eat+fruits[next]);
			visited[next] = false;
		}
	}
}