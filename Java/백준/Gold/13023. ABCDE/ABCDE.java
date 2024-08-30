import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 메모리:19,400KB, 시간:232ms
 */
public class Main {
	static int N;
	static int M;
	static List<Integer>[] list;
	static boolean[] isSelect;
	static int result;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		list = new List[N];
		for(int i = 0 ; i < N ; i++) {
			list[i] = new ArrayList<Integer>();
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			list[from].add(to);
			list[to].add(from);
		}
		for(int i = 0 ; i < N ; i++) {
			isSelect = new boolean[N];
			dfs(i,0);
			if(result==1) break;
		}
		System.out.println(result);
	}
	
	public static void dfs(int from, int depth) {
		if(depth == 5) {
			result = 1;
			return;
		}
		for(int i = 0, iEnd =list[from].size()  ; i < iEnd ;i++) {
			int to = list[from].get(i);
			if(isSelect[to]) continue;
			isSelect[to] = true;
			dfs(to,depth+1);
			if(result==1) return;
			isSelect[to] = false;
		}
	}
}