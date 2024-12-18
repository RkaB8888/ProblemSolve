import java.io.*;
import java.util.Arrays;
/**
 * 구현 메모리 ? KB 시간 ? ms
 * 
 * @author python98
 */
public class Main {
	static final int SUM = 2015;
	static int result = SUM;
	static boolean[] check = new boolean['Z'+1];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1 ; tc <= T ; tc++) {
			result = SUM;
			Arrays.fill(check, false);
			for(char c : br.readLine().toCharArray()) {
				if(check[c]==false) {
					result -= c;
					check[c] = true;
				}
			}
			sb.append(result).append('\n');
		}
		System.out.print(sb);
	}
}