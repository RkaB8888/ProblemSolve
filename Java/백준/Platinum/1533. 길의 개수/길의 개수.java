import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 행렬 제곱 분할 정복 메모리 ? KB 시간 ? ms
 */
public class Main {
	static final int MOD = 1000003;
	static int N, S, E, T, result;
	static long[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken())-1;
		E = Integer.parseInt(st.nextToken())-1;
		T = Integer.parseInt(st.nextToken());

		map = new long[5*N][5*N];
		for(int i = 0 ; i < 5*N ; i+=5) {
			char[] c = br.readLine().toCharArray();
			for(int j = 0 ; j < 5*N ; j+=5) {
				int w = c[j/5]-'0';
				int from = i, to = j+w-1 ;
				while(to>=j) {
					map[from][to] = 1;
					from = to;
					to--;
				}
			}
		}
//		for(int[] row : map) {
//			System.out.println(Arrays.toString(row));
//		}
		
		map = matrixProduct(map,T);
//		System.out.println("//////////");
//		for(int[] row : map) {
//			System.out.println(Arrays.toString(row));
//		}
		System.out.print(map[S*5][E*5]);
	}

	private static long[][] matrixProduct(long[][] matrix, long t) {
		if(t==1) return matrix; 
		long[][] half = matrixProduct(matrix,t>>1);
		if(t%2==1) {
			return product(matrix,product(half,half));
		} else {
			return product(half,half);
		}	
	}

	private static long[][] product(long[][] matrix1, long[][] matrix2) {
		long[][] result = new long[N*5][N*5];
		for(int i = 0 ; i < N*5 ; i++) {
			for(int j = 0 ; j < N*5 ; j++) {
				for(int k = 0 ; k < N*5 ; k++) {
					result[i][j] += matrix1[i][k]*matrix2[k][j]%MOD;
					result[i][j]%=MOD;
				}
			}
		}
		return result;
	}
}