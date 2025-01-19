import java.io.*;
import java.util.*;

/**
 * @description 트리의 지름 DFS 재귀
 * @performance 메모리: ? KB, 동작시간: ? ms
 * @author python98
 */
public class Main {
	static int n;
	static int[] fruits;
	static List<Integer>[] adjList;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		n = Integer.parseInt(br.readLine());
		fruits = new int[n + 1];
		visited = new boolean[n + 1];
		
		adjList = new ArrayList[n + 1];
		for (int i = 0; i <= n; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		// 과일 입력
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) {
			fruits[i] = Integer.parseInt(st.nextToken());
		}
		// 간선 입력
        for (int i = 1; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            adjList[from].add(to);
            adjList[to].add(from);
        }
        
        // 임의의 노드에서 출발
		int[] first = dfs(1,fruits[1]);
		
		Arrays.fill(visited, false);
		
		// 가장 먼 리프노드에서 출발
		int[] second = dfs(first[1],fruits[first[1]]);
		
		sb.append(second[0]).append(" ").append(Math.min(first[1], second[1]));
		System.out.print(sb);
	}

	/**
     * DFS 함수
     * @param node 현재 노드
     * @param totalEat 현재까지 먹은 과일 수
     * @return {최대 과일 수, 해당 노드}
     */
    private static int[] dfs(int node, int totalEat) {
        visited[node] = true;
        int maxFruits = totalEat;
        int farthestNode = node;

        for (int next : adjList[node]) {
            if (!visited[next]) {
                int[] result = dfs(next, totalEat + fruits[next]);
                if (result[0] > maxFruits || (result[0] == maxFruits && result[1] < farthestNode)) {
                    maxFruits = result[0];
                    farthestNode = result[1];
                }
            }
        }
        return new int[]{maxFruits, farthestNode};
    }
}