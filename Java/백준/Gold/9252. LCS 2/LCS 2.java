import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * 메모리 ? KB 시간 ? ms
 * DP
 * @author python98
 */
public class Main {
	static int aLen, bLen, dp[][];
	static char[] A, B;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		A = br.readLine().toCharArray();
		aLen = A.length;
		B = br.readLine().toCharArray();
		bLen = B.length;
		dp = new int[aLen+1][bLen+1];
		for(int i = 1 ; i <= aLen ; i++) {
			for(int j = 1 ; j <= bLen ; j++) {
				if(A[i-1]==B[j-1]) {
					dp[i][j] = dp[i-1][j-1]+1;
				} else {
					dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
				}
			}
		}
		int i = aLen, j = bLen;
		while(i>0&&j>0) {
			if(A[i-1]==B[j-1]) {
				sb.append(A[i-1]);
				i--;j--;
			} else if(dp[i-1][j]>dp[i][j-1]){
				i--;
			} else {
				j--;
			}
		}
		System.out.println(dp[aLen][bLen]);
		System.out.println(sb.reverse());
	}
}