import java.io.*;

/**
 * @description 구현
 * @performance 메모리: ? KB, 동작시간: ? ms
 * @author python98
 */
public class Main {
	static int cnt;
	static char[] input;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		input = br.readLine().toCharArray();
		int c = 0;
		cnt = input.length;
		while (c < input.length-1) {
			if (c < input.length - 1 && input[c] == 'c' && input[c + 1] == '=') {
				c++;
				cnt--;
			} if (c < input.length - 1 && input[c] == 'c' && input[c + 1] == '-') {
				c++;
				cnt--;
			} if (c < input.length - 1 && input[c] == 'd' && input[c + 1] == '-') {
				c++;
				cnt--;
			} if (c < input.length - 1 && input[c] == 'l' && input[c + 1] == 'j') {
				c++;
				cnt--;
			} if (c < input.length - 1 && input[c] == 'n' && input[c + 1] == 'j') {
				c++;
				cnt--;
			} if (c < input.length - 1 && input[c] == 's' && input[c + 1] == '=') {
				c++;
				cnt--;
			} if (c < input.length - 1 && input[c] == 'z' && input[c + 1] == '=') {
				c++;
				cnt--;
			} if (c < input.length - 2 && input[c] == 'd' && input[c + 1] == 'z' && input[c + 2] == '=') {
				c += 2;
				cnt -= 2;
			}
			c++;
		}
		System.out.print(cnt);
	}
}