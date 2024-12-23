import java.io.*;
import java.util.*;
/**
 * 구현 메모리 ? KB 시간 ? ms
 * 껍데기는 (N+1)/2개 까지 존재함.
 * 주어준 (r,c)에서 r, N-r+1, c, N-c+1 중에서 제일 작은 값을 취한다.
 * 그 중 제일 작은 값을 3으로 나눴을 때 나머지에 +1 값을 출력
 * @author python98
 */
public class Main {
	static int N, K;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		for(int i = 0, r = 0, c = 0, min = Integer.MAX_VALUE ; i < K ; i++) {
			st = new StringTokenizer(br.readLine());
			c = Integer.parseInt(st.nextToken());
			r = Integer.parseInt(st.nextToken());
			min = Math.min(Math.min(r, N-r+1),Math.min(c, N-c+1))-1;
			sb.append(min%3+1).append("\n");
		}
		System.out.print(sb);
	}
}