import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * 메모리:56,080KB, 시간:568ms
 * @author SSAFY
 *
 */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int[] arrSum = new int[N+1];
		int sum = 0;
		arrSum[0] = sum;
		for(int i = 1 ; i <= N ; i++) {
			sum+=Integer.parseInt(st.nextToken());
			arrSum[i] = sum;
		}
		for(int i = 0 ; i < M ; i++) {
			st = new StringTokenizer(br.readLine());
			int idx1 = Integer.parseInt(st.nextToken());
			int idx2 = Integer.parseInt(st.nextToken());
			sb.append(arrSum[idx2]-arrSum[idx1-1]).append('\n');
		}
		System.out.println(sb);
	}
}