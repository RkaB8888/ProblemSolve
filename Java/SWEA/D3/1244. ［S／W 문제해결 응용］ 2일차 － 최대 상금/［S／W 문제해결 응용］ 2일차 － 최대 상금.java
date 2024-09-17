import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * ?KB ?ms
 * 그리디
 */
public class Solution {
	static int N, result;
	static char num[], idealNum[];
	static boolean flag;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int TC = Integer.parseInt(br.readLine().trim());

		for (int tc = 1; tc <= TC; tc++) {
			st = new StringTokenizer(br.readLine().trim());
			num = st.nextToken().toCharArray();
			idealNum = Arrays.copyOf(num, num.length);
			N = Integer.parseInt(st.nextToken());
			flag = false;
			Arrays.sort(idealNum);
			for (int i = 1; i < idealNum.length; i++) {
				if (idealNum[i - 1] == idealNum[i]) {
					flag = true;
					break;
				}
			}
			for (int i = 0, iEnd = num.length; N > 0 && i < iEnd; i++) {
				if (num[i] != idealNum[iEnd - 1 - i]) {
					char temp = num[i];
					num[i] = idealNum[iEnd - 1 - i];// 큰 값을 앞쪽에 넣는다.
					int tempIdx = 0;
					boolean flag = true;
					for (int j = iEnd - 1; j > i; j--) {
						if (num[j] == num[i]) {
							if (idealNum[iEnd - 1 - j] == temp) {
								num[j] = temp;
								N--;
								flag = false;
								break;
							}
							else {
								tempIdx = Math.max(j,tempIdx);
							}
						}
					}
					if(flag) {
						num[tempIdx] = temp;
						N--;
					}
				}
			}
			if (!flag && N % 2 == 1) {
				char temp = num[num.length - 2];
				num[num.length - 2] = num[num.length - 1];
				num[num.length - 1] = temp;
			}
			sb.append('#').append(tc).append(' ').append(new String(num)).append('\n');
		}
		System.out.println(sb);
	}

}