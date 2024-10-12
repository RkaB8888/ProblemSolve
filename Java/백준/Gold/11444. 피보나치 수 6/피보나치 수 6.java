import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 메모리 ? KB 시간 ? ms
 * 행렬 제곱 분할 정복
 * @author python98
 */
public class Main {
	
	static final int mod = 1000000007;
	
	static long N;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Long.parseLong(br.readLine());
			
		System.out.print(fibo(N));
	}
	private static long fibo(long n) {
		if(n==0) return 0;
		if(n==1) return 1;
		long[][] baseMatrix = {{1,1},{1,0}};
		long[][] result = getMatrix(baseMatrix,n-1);
		return result[0][0];
	}
	private static long[][] getMatrix(long Matrix[][], long n) {
		if(n==1) return Matrix;
		if((n&1)==0) {
			long[][] half = getMatrix(Matrix, n>>1);
			return multiply(half,half);
		} else {
			return multiply(Matrix,getMatrix(Matrix,n-1));
		}
	}
	private static long[][] multiply(long[][] matrix1, long[][] matrix2) {
		long[][] nextMatrix = new long[2][2];
		nextMatrix[0][0] = (matrix1[0][0]*matrix2[0][0]%mod+matrix1[0][1]*matrix2[1][0]%mod)%mod;
		nextMatrix[0][1] = (matrix1[0][0]*matrix2[0][1]%mod+matrix1[0][1]*matrix2[1][1]%mod)%mod;
		nextMatrix[1][0] = (matrix1[1][0]*matrix2[0][0]%mod+matrix1[1][1]*matrix2[1][0]%mod)%mod;
		nextMatrix[1][1] = (matrix1[1][0]*matrix2[0][1]%mod+matrix1[1][1]*matrix2[1][1]%mod)%mod;
		return nextMatrix;
	}
}