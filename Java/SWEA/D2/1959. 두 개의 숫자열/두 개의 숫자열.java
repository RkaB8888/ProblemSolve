import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 메모리 18,392KB 시간 108ms
 */
public class Solution {
	static int N, M, A[], B[], result;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			A = new int[N];
			M = Integer.parseInt(st.nextToken());
			B = new int[M];
			result = 0;
			st = new StringTokenizer(br.readLine());
			for(int i = 0 ; i < N ; i++) {
				A[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for(int i = 0 ; i < M ; i++) {
				B[i] = Integer.parseInt(st.nextToken());
			}
			if(N>M) {
				calc(B,M,A,N);
			}
			else {
				calc(A,N,B,M);
			}
			sb.append('#').append(tc).append(' ').append(result).append('\n');
		}
		System.out.println(sb);
	}
	public static void calc(int[] a, int a_len, int[] b, int b_len) { // b가 더 큼
		int sum = 0;
		for(int i = 0, iEnd = b_len-a_len ; i <= iEnd ; i++) {
			sum = 0;
			for(int j = 0 ; j < a_len ; j++) {
				sum+=a[j]*b[i+j];
			}
			if(sum>result) result = sum;
		}
	}
}