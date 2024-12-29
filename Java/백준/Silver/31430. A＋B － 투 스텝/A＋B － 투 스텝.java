import java.io.*;
import java.util.*;

/**
 * @description
 *    - 최대 2*10^18의 수를 26진수 13자리로 서로 변환하는 문제<br>
 * @performance 메모리: ? KB, 동작시간: ? ms
 * @author python98
 */
public class Main {
	static final int MAX_NUM = 26;
	static int c, digit;
	static long sum, output2;
	static char[] output1;
	static char[] input2;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		c = Integer.parseInt(br.readLine());
		output1 = new char[13];
		Arrays.fill(output1, 'a');
		if(c==1) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			sum = Long.parseLong(st.nextToken()) + Long.parseLong(st.nextToken());
			while(sum>0) {
				output1[digit++] += sum%MAX_NUM;
				sum/=MAX_NUM;
			}
			for(int i = 12 ; i >= 0 ; i--) {
				sb.append(output1[i]);
			}
			System.out.print(sb);
		} else {
			input2 = br.readLine().toCharArray();
			for(int i = 0 ; i < 13 ; i++) {
				output2 *= MAX_NUM;
				output2 += input2[i]-'a';
			}
			System.out.print(output2);
		}
	}
}