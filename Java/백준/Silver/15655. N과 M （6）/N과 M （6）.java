import java.io.*;
import java.util.*;
/**
 * 
 * ? 메모리 ? KB 시간 ? ms
 * 
 * @author python98
 *
 */
public class Main {
	static int N, M, arr[];
	static boolean[] selected;
	static StringBuilder sb;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N];
		selected = new boolean[N];
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
			for(int i = 0 ; i < str ; i++) {
				if(selected[i]) {
					sb.append(arr[i]).append(' ');
				}
			}
			sb.append('\n');
			return;
		}
		for(int i = str ; i < N ; i++) {
			selected[i] = true;
			combination(depth+1,i+1);
			selected[i] = false;
		}
	}
}