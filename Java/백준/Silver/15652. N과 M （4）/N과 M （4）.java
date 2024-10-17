import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * 메모리 111,748 KB 시간 284 ms
 * 조합
 * @author python98
 */
public class Main {
	static int N, M, arr[];
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[M];
		process(1, 0);
		System.out.print(sb);
	}
	public static void process(int str, int cnt) {
		if(cnt==M) {
			for(int i = 0 ; i < M ; i++) {
				sb.append(arr[i]).append(' ');
			}
			sb.append('\n');
			return;
		}
		for(int i = str ; i <=N ; i++) {
			arr[cnt] = i;
			process(i,cnt+1);
		}
	}
}