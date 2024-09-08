import java.io.*;
import java.util.*;

public class Main {
	
	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	
	static ArrayList<Integer>[] need;
	static int[] d;
	static int[] memo;
	
	public static void main(String[] args) throws IOException {
		
		int T = Integer.parseInt(in.readLine());
		while(T-- > 0) {
			st = new StringTokenizer(in.readLine());
			int n = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			d = new int[n];
			need = new ArrayList[n];
			memo = new int[n];
			Arrays.fill(memo, -1);
			for(int i=0; i<n; ++i)
				need[i] = new ArrayList<>();
			st = new StringTokenizer(in.readLine());
			for(int i=0; i<n; ++i)
				d[i] = Integer.parseInt(st.nextToken());
			for(int i=0; i<k; ++i) {
				st = new StringTokenizer(in.readLine());
				int u = Integer.parseInt(st.nextToken()) - 1;
				int v = Integer.parseInt(st.nextToken()) - 1;
				need[v].add(u);
			}
			int w = Integer.parseInt(in.readLine()) - 1;
			sb.append(time(w)).append('\n');
		}
		System.out.println(sb);
	}
	
	static int time(int x) {
		if(memo[x] != -1)
			return memo[x];
		int ret = 0;
		for(int u : need[x]) {
			ret = Math.max(ret, time(u));
		}
		ret += d[x];
		return memo[x] = ret;
	}
}