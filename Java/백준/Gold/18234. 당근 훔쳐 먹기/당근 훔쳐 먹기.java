import java.io.*;
import java.util.*;

/**
 * @description
 *    - 그리디 :<br>
 *    <ul>
 *    <li>초기치 == 영양분 : 시간 투자해도 이득 없음 == 꾸준히 먹거나 말거나</li>
 *    <li>초기치 < 영양분 : 시간 투자 해야 이득</li>
 *    <li>초기치 > 영양분 : 꾸준히 안먹으면 손해</li>
 *    </ul>
 *    - 문제의 조건에서 초기치는 영양분보다 작거나 같으니 남는 시간에 뭘 먹을지 고민X<br>
 *    - 모든 시간(T)에서 마지막 N만큼의 시간 중에서 영양분이 가장 낮은 것 부터 먹어야 최대 이득<br>
 * @performance 메모리: ? KB, 동작시간: ? ms
 * @author python98
 */
public class Main {
	static int N, T;
	static int[] p;
	static long result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		p = new int[N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			result += Integer.parseInt(st.nextToken());
			p[i] = Integer.parseInt(st.nextToken());
		}
		long time = T - N;
		Arrays.sort(p);
		for(int val : p) {
			result += val * time;
			time++;
		}
		System.out.print(result);
	}
}