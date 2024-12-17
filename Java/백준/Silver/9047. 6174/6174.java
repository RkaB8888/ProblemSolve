import java.io.*;
import java.util.Arrays;
/**
 * 구현 메모리 ? KB 시간 ? ms
 * 
 * @author python98
 */
public class Main {
	static char[] numC;
	static int[] num;
	static int result;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1 ; tc <= T ; tc++) {
			numC = br.readLine().toCharArray();
			num = new int[4];
			for(int i = 0 ; i < 4 ; i++) {
				num[i] = numC[i]-'0';
			}
			result = 0;
			while(num[0]!=6||num[1]!=1||num[2]!=7||num[3]!=4) {
				Arrays.sort(num);
				int large = 0, small = 0, unit = 1;
				for(int i = 0 ; i < 4 ; i++) {
					small*=10;
					small+=num[i];
					large += unit*num[i];
					unit*=10;
				}
				int temp = large - small;
				for(int i = 3 ; i >= 0 ; i--) {
					num[i] = temp%10;
					temp/=10;
				}
				result++;
			}
			sb.append(result).append('\n');
		}
		System.out.print(sb);
	}
}