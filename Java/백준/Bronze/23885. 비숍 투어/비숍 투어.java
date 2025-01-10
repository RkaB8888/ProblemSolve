import java.io.*;
import java.util.*;

/**
 * @description 구현
 * @performance 메모리: ? KB, 동작시간: ? ms
 * @author python98
 */
public class Main {
	static int N, M;
	static int[] s, e;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		s = new int[] {Integer.parseInt(st.nextToken())-1,Integer.parseInt(st.nextToken())-1};
		
		st = new StringTokenizer(br.readLine());
		e = new int[] {Integer.parseInt(st.nextToken())-1,Integer.parseInt(st.nextToken())-1};
		
		if((s[0]+s[1])%2==(e[0]+e[1])%2&&(s[0]-s[1])%2==(e[0]-e[1])%2) {
			System.out.print("YES");
		} else {
			System.out.println("NO");
		}
	}
}