import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 19,088 KB 100 ms
 * 그리디
 */
public class Solution {
	static int N, result;
	static char num[], idealNum[];
	static boolean dupl;

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
			dupl = false;
			
			int len = num.length;
			
			Arrays.sort(idealNum);
			for (int i = 1; i < len; i++) {
				if (idealNum[i - 1] == idealNum[i]) {
					dupl = true;
					break;
				}
			}
			int lastIdx = len - 1;
			for (int i = 0 ; N > 0 && i < len; i++) {
				if (num[i] != idealNum[lastIdx - i]) {
					char changeNum1 = idealNum[lastIdx - i];
					char temp = num[i];
					num[i] = changeNum1;// 큰 값을 앞쪽에 넣는다.
					int tempIdx = 0;
					boolean flag = true;
					for (int j = len - 1; j > i; j--) {
						if (num[j] == changeNum1) {
							if (idealNum[lastIdx - j] == temp) {
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
			if (!dupl && N % 2 == 1) {
				char temp = num[len - 2];
				num[len - 2] = num[lastIdx];
				num[lastIdx] = temp;
			}
			sb.append('#').append(tc).append(' ').append(new String(num)).append('\n');
		}
		System.out.println(sb);
	}

}