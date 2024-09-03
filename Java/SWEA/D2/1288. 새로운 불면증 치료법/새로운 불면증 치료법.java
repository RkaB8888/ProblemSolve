import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 메모리 18,372KB 시간 109ms
 */
public class Solution {
	static int N, checkCnt;
	static boolean[] check;
	static int num, temp, result = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			check = new boolean[10];
			checkCnt = 0;
			num = 0;
			temp = 0;
			result = 0;
			while (checkCnt < 10) {
				result+=N;
				num = result;
				while (num > 0) {
					temp = num%10;
					if (!check[temp]) {
						check[temp] = true;
						checkCnt++;
					}
					num /= 10;
				}
			}
			sb.append('#').append(tc).append(' ').append(result).append('\n');
		}
		System.out.println(sb);
	}

}