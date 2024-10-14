import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {
	static int N, matrixInfo[][], dp[][][];//i~j까지의 곱 중 최소 횟수, m, n
	static final int inf = Integer.MAX_VALUE;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		matrixInfo = new int[N][2];
		for(int i = 0 ; i < N ; i++) {
			st = new StringTokenizer(br.readLine());
			matrixInfo[i][0] = Integer.parseInt(st.nextToken());
			matrixInfo[i][1] = Integer.parseInt(st.nextToken());
		}
		dp = new int[N][N][3];
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < N ; j++) {
				if(i==j) {
					dp[i][j][0] = 0;
					dp[i][j][1] = matrixInfo[i][0];
					dp[i][j][2] = matrixInfo[i][1];
				} else {
					dp[i][j][0] = inf;
				}
			}
		}
		
		for(int k = 1 ; k < N ; k++) {//간격
			for(int i = 0 ; i+k < N ; i++) {//시작
				calc(i,i+k);
			}
		}
		System.out.println(dp[0][N-1][0]);
	}
	private static void calc(int str, int end) {
		for(int i = str ; i < end ; i++) {
			int m1 = dp[str][i][1], m2 = dp[i+1][end][1];
			int n2 = dp[i+1][end][2];
			int temp = dp[str][i][0]+dp[i+1][end][0]+m1*m2*n2;
			if(dp[str][end][0]>temp) {
				dp[str][end][0] = temp;
				dp[str][end][1] = m1;
				dp[str][end][2] = n2;
			}
		}
	}
}