import java.io.*;

/**
 * @description
 *    - 부분 문자열 찾기<br>
 * @performance 메모리: ? KB, 동작시간: ? ms
 * @author python98
 */
public class Main {
	static int k, s, result;
	static int[] preIdx;
	static char[] base, check;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		base = br.readLine().toCharArray();
		check = br.readLine().toCharArray();

		if (base.length < check.length) {
			System.out.print(0);
			return;
		}
		
		preIdx = new int[check.length];
		int pre = 0;
		for (int i = 1; i < check.length; i++) {
			while (pre > 0 && check[i] != check[pre]) {
				pre = preIdx[pre - 1];
			}
			if (check[i] == check[pre]) {
				pre++;
			}
			preIdx[i] = pre;
		}
		for (int i = 0, j = 0; i < base.length; i++) {
			while (j > 0 && base[i] != check[j]) {
				j = preIdx[j - 1];
			}
			if (base[i] == check[j]) {
				j++;
			}
			if (j == check.length) {
				result = 1;
				break;
			}
		}
		System.out.print(result);
	}

}