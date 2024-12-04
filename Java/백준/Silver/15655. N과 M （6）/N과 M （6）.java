import java.io.*;
import java.util.*;
/**
 * 
 * 조합 메모리 11,584 KB 시간 68 ms
 * 
 * @author python98
 *
 */
public class Main {
	static int N, M, arr[], selected[];
	static StringBuilder sb;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N];
		selected = new int[M];
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		combination(0,0);
		System.out.print(sb);
	}

	private static void combination(int depth, int str) {
		if(depth == M) {
			for(int num : selected) {
				sb.append(num).append(' ');
			}
			sb.append('\n');
			return;
		}
		for(int i = str ; i < N ; i++) {
			selected[depth] = arr[i];
			combination(depth+1,i+1);
		}
	}
}