import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 메모리 167,820KB 시간 424ms
 */
public class Main {
	static int N, K, buildTimes[], reachTimes[], goal;
	static Edge edges[];
	static class Edge{
		int to;
		Edge next;
		public Edge(int to, Main.Edge next) {
			this.to = to;
			this.next = next;
		}
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		for(int tc = 1 ; tc <= TC ; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			buildTimes = new int[N+1];
			reachTimes = new int[N+1];
			
			st = new StringTokenizer(br.readLine());
			for(int i = 1 ; i <= N ; i++) {
				buildTimes[i] = Integer.parseInt(st.nextToken());
			}
			
			edges = new Edge[N+1];
			
			for(int i = 0 ; i < K ; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				edges[from] = new Edge(to,edges[from]);
			}
			
			goal = Integer.parseInt(br.readLine());
			for(int i = 1 ; i <= N ; i++) {
				reachTimes[i] = buildTimes[i];
			}
			bfs();
			sb.append(reachTimes[goal]).append('\n');
		}
		System.out.println(sb);
	}
	public static void bfs() {
		Queue<Integer> q = new ArrayDeque<>();
		for(int i = 1 ; i <= N ; i++) {
			for(Edge edge = edges[i] ; edge != null ; edge = edge.next) {
				if(reachTimes[edge.to]<reachTimes[i]+buildTimes[edge.to]) {
					reachTimes[edge.to]=reachTimes[i]+buildTimes[edge.to];
					q.add(edge.to);
				}
			}
		}
		
		while(!q.isEmpty()) {
			int curVertex = q.poll();
			for(Edge edge = edges[curVertex] ; edge != null ; edge = edge.next) {
				if(reachTimes[edge.to]<reachTimes[curVertex]+buildTimes[edge.to]) {
					reachTimes[edge.to]=reachTimes[curVertex]+buildTimes[edge.to];
					q.add(edge.to);
				}
			}
		}
	}

}