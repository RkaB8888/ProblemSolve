import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	static boolean check[];
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		check = new boolean[N+1];
		int[] arr = new int[M];
		find(N,0,M,arr);
	}
	
	public static void find(int N, int cnt, int M, int[] arr) {
		if(cnt==M) {
			StringBuilder sb = new StringBuilder();
			for(int i = 0 ; i < M ; i++) {
				sb.append(arr[i]).append(' ');
			}
			System.out.println(sb);
			return;
		}
		for(int i = 1 ; i <= N ; i++) {
			if(check[i]) {
				continue;
			}
			check[i] = true;
			arr[cnt] = i;
			find(N,cnt+1,M, arr);
			check[i] = false;
		}
	}
}