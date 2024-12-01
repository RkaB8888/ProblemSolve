import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 위상정렬 메모리 ? KB 시간 ? ms
 * 
 * @author python98
 *
 */
public class Main {
	static int N, result;
	static int[] cnt;
	static List<List<Integer>> adjList;
	static boolean[] adopter;
	static Queue<Integer> q;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		cnt = new int[N + 1];
		adopter = new boolean[N + 1];
		adjList = new ArrayList<>();
		q = new ArrayDeque<Integer>();
		for (int i = 0; i <= N; i++) {
			adjList.add(new ArrayList<Integer>());
		}
		for (int i = 1; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int to = Integer.parseInt(st.nextToken());
			int from = Integer.parseInt(st.nextToken());
			adjList.get(from).add(to);
			adjList.get(to).add(from);
			cnt[from]++;
			cnt[to]++;
		}
		for (int i = 1; i <= N; i++) {
			if (cnt[i] == 1) {
				q.add(i);
				cnt[i]--;
			}
		}
		while (!q.isEmpty()) {
			int curNode = q.poll();
			for (int nextNode : adjList.get(curNode)) {
				cnt[nextNode]--;
				if(cnt[nextNode]==0) continue;
				if(!adopter[curNode]) {
//					System.out.println("nextNode : "+nextNode);
					adopter[nextNode] = true;
				}
				if(cnt[nextNode]==1) {
//					System.out.println("add : "+nextNode);
					q.add(nextNode);
					cnt[nextNode]--;
				}
			}
		}
		for (int i = 1; i <= N; i++) {
			if (adopter[i]) {
				result++;
			}
		}
//		System.out.println(Arrays.toString(adopter));
		System.out.print(result);
	}
}