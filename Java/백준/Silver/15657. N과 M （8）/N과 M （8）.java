import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/**
 * 
 * 중복순열 메모리 219,984 KB 시간 512 ms
 * 
 * @author python98
 *
 */
public class Main {
	static int N, M;
	static int[] arr1, arr2;
	static StringBuilder sb;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr1 = new int[N];
		arr2 = new int[M];
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; i++) {
			arr1[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr1);
		permutation(0,0);
		System.out.print(sb);
	}
	private static void permutation(int depth, int str) {
		if(depth==M) {
			for(int num : arr2) {
				sb.append(num).append(' ');
			}
			sb.append('\n');
			return;
		}
		for(int i = str ; i < N ; i++) {
			arr2[depth] = arr1[i];
			permutation(depth+1,i);
		}
	}
}