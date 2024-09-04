import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Main {
	static int N;
	static int[] dp;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dp = new int[N+1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0; dp[1] = 0;
		Queue<Integer> q = new ArrayDeque<>();
		q.add(1);
		
		while(!q.isEmpty()) {
			int num = q.poll();
			if(num==N) break;
			if(num*3<=N&&dp[num*3]>dp[num]+1) {
				dp[num*3] = dp[num]+1;
				q.add(num*3);
			}
			if(num*2<=N&&dp[num*2]>dp[num]+1) {
				dp[num*2] = dp[num]+1;
				q.add(num*2);
			}
			if(num+1<=N&&dp[num+1]>dp[num]+1) {
				dp[num+1] = dp[num]+1;
				q.add(num+1);
			}
		}
		System.out.println(dp[N]);
	}

}