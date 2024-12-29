import java.io.*;
import java.util.*;

/**
 * @description
 *    - 아스키 코드 연산<br>
 * @performance 메모리: ? KB, 동작시간: ? ms
 * @author python98
 */
public class Main {
	static int k, s;
	static char[] target;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		k = Integer.parseInt(st.nextToken())%26;
		s = Integer.parseInt(st.nextToken());
		target = br.readLine().toCharArray();
		for(char c : target) {
			if(c >= 'A' && c <= 'Z') {
				c+=k;
				while(c>'Z') {
					c -= 'Z'-'A' + 1;
				}
			} else if(c >= 'a' && c <= 'z') {
				c+=k;
				while(c>'z') {
					c -= 'z'-'a' + 1;
				}
			}
			sb.append(c);
		}
		System.out.print(sb);
	}
}