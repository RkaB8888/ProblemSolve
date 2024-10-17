import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/**
 * 메모리 ? KB 시간 ? ms
 * 정렬 후 조합
 * @author python98
 */
public class Main {
	static int N, M, arr[], result[];
	static boolean isSelect[];
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N];
		result = new int[M];
		isSelect = new boolean[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		
		process(0);
		System.out.print(sb);
	}
	public static void process(int cnt) {
		if(cnt==M) {
			for(int i = 0 ; i < M ; i++) {
				sb.append(result[i]).append(' ');
			}
			sb.append('\n');
			return;
		}
		for(int i = 0 ; i < N ; i++) {
			if(isSelect[i]) continue;
			isSelect[i] = true;
			result[cnt] = arr[i];
			process(cnt+1);
			isSelect[i] = false;
		}
	}
}