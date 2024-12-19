import java.io.*;
import java.util.StringTokenizer;
/**
 * 구현 메모리 ? KB 시간 ? ms
 * 
 * @author python98
 */
public class Main {
	static int[] cnts = new int[] {1,1,2,2,2,8};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int cnt : cnts) {
			sb.append(cnt-Integer.parseInt(st.nextToken())).append(' ');
		}
		System.out.print(sb);
	}
}