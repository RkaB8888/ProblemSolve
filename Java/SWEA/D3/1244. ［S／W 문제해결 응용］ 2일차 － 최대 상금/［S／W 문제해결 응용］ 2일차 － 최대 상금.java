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
	static boolean dupl, checked[];

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
			checked = new boolean[num.length];
			Arrays.sort(idealNum);
			for (int i = 1; i < idealNum.length; i++) {
				if (idealNum[i - 1] == idealNum[i]) {
					dupl = true;
					break;
				}
			}
			for (int i = 0, iEnd = num.length; N > 0 && i < iEnd; i++) {
				if (!checked[i] && num[i] != idealNum[iEnd - 1 - i]) {
					checked[i] = true;
					char temp = num[i];
					num[i] = idealNum[iEnd - 1 - i];// 큰 값을 앞쪽에 넣는다.
					int tempIdx = 0;
					boolean flag = true;
					for (int j = iEnd - 1; j > i; j--) {
						if (num[j] == num[i]) {
							if (idealNum[iEnd - 1 - j] == temp) {
								checked[j] = true;
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
						checked[tempIdx] = true;
						num[tempIdx] = temp;
						N--;
					}
					
				}
				
			}
			if (!dupl && N % 2 == 1) {
				char temp = num[num.length - 2];
				num[num.length - 2] = num[num.length - 1];
				num[num.length - 1] = temp;
			}
			sb.append('#').append(tc).append(' ').append(new String(num)).append('\n');
		}
		System.out.println(sb);
	}

}