import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 메모리:27,288KB, 시간:181ms
 *
 */
public class Solution {
	static int N, K, sideLen, result[], arr[];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			sideLen = N/4;//한 변의 길이
			char c[] = br.readLine().toCharArray();
			arr = new int[N];
			result = new int[N];
			for(int i = 0 ; i < N ; i++) {//각 단위 10진수 변환
				if(c[i]<='9') {
					arr[i] = c[i]-'0';
				}
				else {
					arr[i] = c[i]-'A'+10;
				}
			}
			for(int i = 0; i < sideLen ; i++) {
				result[0] = (result[0]<<4)|arr[i];
			}
			int leftShift = (sideLen-1)*4;
			for(int i = 1, j = N-1 ; i<N&&j > 0 ; i++,j--) {
				result[i]=result[i-1]>>4;
				result[i]+=arr[j]<<leftShift;
			}
			Arrays.sort(result);
			int preNum = 0, cnt = 0;
			for(int i = N-1 ; i >= 0 ; i--) {
				if(preNum!=result[i]) {
					cnt++;
					preNum = result[i];
				}
				if(cnt==K) break;
			}
			sb.append('#').append(tc).append(' ').append(preNum).append('\n');
		}
		System.out.println(sb);
	}

}