import java.io.*;

/**
 * @description 구현
 * @performance 메모리: 11,520 KB, 동작시간: 68 ms
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
		while (c < input.length - 1) {
			if (input[c] == 'c' && (input[c + 1] == '=' || input[c + 1] == '-')) {
				c++;
				cnt--;
			} else if (input[c] == 'd') {
				if (input[c + 1] == '-') {
					c++;
					cnt--;
				} else if (c < input.length - 2 && input[c + 1] == 'z' && input[c + 2] == '=') {
					c += 2;
					cnt -= 2;
				}
			} else if (input[c] == 'l' && input[c + 1] == 'j') {
				c++;
				cnt--;
			}
			else if ( input[c] == 'n' && input[c + 1] == 'j') {
				c++;
				cnt--;
			}
			else if (input[c] == 's' && input[c + 1] == '=') {
				c++;
				cnt--;
			}
			else if (input[c] == 'z' && input[c + 1] == '=') {
				c++;
				cnt--;
			}
			c++;
		}
		System.out.print(cnt);
	}
}