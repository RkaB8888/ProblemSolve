import java.io.*;
import java.util.*;

/**
 * @description 트리의 지름 DFS 재귀
 * @performance 메모리: 21,008 KB, 동작시간: 180 ms
 * @author python98
 */
public class Main {
	static int n, maxEat, fartest, start;
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
        start = 1;
        fartest = 1;
		dfs(start,start,fruits[start]);
		
		Arrays.fill(visited, false);
		
		// 가장 먼 리프노드에서 출발
		start = fartest;
		dfs(start,start,fruits[start]);
		
		sb.append(maxEat).append(" ").append(start>fartest?fartest:start);
		System.out.print(sb);
	}

	/**
     * DFS 함수
     * @param node 현재 노드
     * @param totalEat 현재까지 먹은 과일 수
     * @return {최대 과일 수, 해당 노드}
     */
    private static void dfs(int startNode, int curNode, int totalEat) {
        visited[curNode] = true;
        if(totalEat>maxEat) {
        	maxEat = totalEat;
        	fartest = curNode;
        } else if(totalEat==maxEat) {
        	fartest = fartest>curNode?curNode:fartest;
        }

        for (int next : adjList[curNode]) {
            if (!visited[next]) {
                dfs(startNode, next, totalEat + fruits[next]);
            }
        }
    }
}