import java.io.*;

/**
 * @description
 *    - 그래프 :<br>
 * @performance 메모리: ? KB, 동작시간: ? ms
 * @author python98
 */
public class Main {
	static int T, N;
	static int[] target;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		target = new int[10001];
		
		test:for(int t = 1 ; t <= T ; t++) {
			N = Integer.parseInt(br.readLine());
			for(int i = 1 ; i <= N ; i++) {
				target[i] = Integer.parseInt(br.readLine());
			}
			int curNum = 1, nextNum = 0, K = 0;
			while((nextNum = target[curNum])!=0) {
				if(curNum==N) {
					sb.append(K).append('\n');
					continue test;
				}
				target[curNum] = 0;
				curNum = nextNum;
				K++;
				
			}
			sb.append("0\n");
		}
		System.out.print(sb);
	}
}