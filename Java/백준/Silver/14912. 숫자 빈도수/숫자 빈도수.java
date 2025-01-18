import java.io.*;
import java.util.*;

/**
 * @description 구현
 * @performance 메모리: ? KB, 동작시간: ? ms
 * @author python98
 */
public class Main {
	static int n, d, result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken())+'0';
		
		while(n>0) {
			char[] digits = String.valueOf(n).toCharArray();
			for(char c : digits) {
				if(c==d) {
					result++;
				}
			}
			n--;
		}
		System.out.print(result);
	}

}