import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
/**
 * 
 * @author python98
 * ? KB ? ms
 * 위상정렬?
 *
 */
public class Main {
	static int N, M, listCnt[];
	static List<Integer>[] adjList;
	static boolean listCheck[];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		listCnt = new int[N+1];
		listCheck = new boolean[N+1];
		
		adjList = new ArrayList[N+1];
		for(int i = 1 ; i <= N ; i++) {
			adjList[i] = new ArrayList<Integer>();
		}
		
		for(int i = 0 ; i < M ; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			adjList[from].add(to);
			listCnt[to]++;
		}
		
		Queue<Integer> q = new ArrayDeque<>();
		for(int i = 1 ; i <= N ; i++) {
			if(listCnt[i]==0) {
				listCheck[i] = true;
				q.add(i);
				
			}
		}
		
		while(!q.isEmpty()) {
			int num = q.poll();
			sb.append(num).append(' ');
			for(int i : adjList[num]) { // 인입 간선이 없으면 큐에 넣는다.
				listCnt[i]--;
				if(listCnt[i]==0&&!listCheck[i]) {
					listCheck[i] = true;
					q.add(i);
				}
			}
		}
		System.out.print(sb);
	}

}