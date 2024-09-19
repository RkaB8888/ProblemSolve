import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
 * 메모리 ? KB 시간 ? ms
 * DFS
 */
public class Main {
	
	static int N, S, result, arr[], dp[][];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		arr = new int[N];
		for(int i = 0 ; i < N ; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		dfs(0,0);
		if(S==0) result--;
		System.out.print(result);
	}
	public static void dfs(int cnt, int sum) {
		if(cnt==N) {
			if(sum==S) {
				result++;
			}
			return;
		}
		dfs(cnt+1,sum+arr[cnt]);
		dfs(cnt+1, sum);
	}
}