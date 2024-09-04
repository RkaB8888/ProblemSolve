import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
/**
 * 메모리:34848KB, 시간:156ms
 * 1부터 시작해서 bfs 탐색 dp에 그 숫자까지의 최소 연산 횟수 저장
 */
public class Main {
	static int N;
	static int[] dp;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dp = new int[N+1];
		Queue<Integer> q = new ArrayDeque<>();
		q.add(1);
		
		while(!q.isEmpty()) {
			int num = q.poll();
			if(num==N) break;
			int num1 = num+1, num2 = num*2, num3 = num*3, nCnt = dp[num]+1;
			if(num3<=N&&dp[num3]==0) {
				dp[num3] = nCnt;
				q.add(num3);
			}
			if(num2<=N&&dp[num2]==0) {
				dp[num2] = nCnt;
				q.add(num2);
			}
			if(num1<=N&&dp[num1]==0) {
				dp[num1] = nCnt;
				q.add(num1);
			}
		}
		System.out.println(dp[N]);
	}

}