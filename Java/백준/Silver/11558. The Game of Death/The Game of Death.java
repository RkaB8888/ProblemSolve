import java.io.*;
import java.util.*;

/**
 * @description
 *    - 그래프 :<br>
 * @performance 메모리: ? KB, 동작시간: ? ms
 * @author python98
 */
public class Main {
	static int T, N;
	static int[] target;
	static boolean[] check;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		target = new int[10001];
		check = new boolean[10001];
		test:for(int t = 1 ; t <= T ; t++) {
			N = Integer.parseInt(br.readLine());
			for(int i = 1 ; i <= N ; i++) {
				target[i] = Integer.parseInt(br.readLine());
			}
			int curNum = 1, K = 0;
			Arrays.fill(check, 1, N+1, false);
			while(!check[curNum]) {
				check[curNum] = true;
				curNum = target[curNum];
				K++;
				if(curNum==N) {
					sb.append(K).append('\n');
					continue test;
				}
			}
			sb.append("0\n");
		}
		System.out.print(sb);
	}
}