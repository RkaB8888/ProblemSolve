import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 메모리:185,576, 시간:2,486ms
 */
public class Solution {
	static int V;
	static int E;
	static boolean[] Visited;
	static List<int[]>[] list;
	static int startNode;
	static long result;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			list = new ArrayList[V]; 
			Visited = new boolean[V];
			result = 0;
			
			for (int i = 0; i < V; i++) {
                list[i] = new ArrayList<>();
            }
			
			for(int i = 0 ; i < E ; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken())-1;
				int to = Integer.parseInt(st.nextToken())-1;
				int weight = Integer.parseInt(st.nextToken());
				list[from].add(new int[] {to,weight});
				list[to].add(new int[] {from,weight});
			}
			Prim(1);
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}
		System.out.println(sb);
	}
	public static void Prim(int start) {
		PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a,b) -> a[1]-b[1]);
		pq.addAll(list[start]);
		Visited[start] = true;
		int nodeCnt = 1;
		while(!pq.isEmpty()&&nodeCnt<V) {
			int[] next = pq.poll();
			int to = next[0];
			int weight = next[1];
			
			if(Visited[to]) continue;
			
			Visited[to] = true;
			nodeCnt++;
			result+=weight;
			pq.addAll(list[to]);
		}
	}

}