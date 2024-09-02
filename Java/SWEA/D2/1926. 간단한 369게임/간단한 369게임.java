import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 메모리 11,516KB 시간 68ms
 */
public class Solution {
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		for(int i = 1 ; i <= N ; i++) {
			boolean flag = false;
			int num = i;
			while(num!=0) {
				int temp = num%10;
				if(temp!=0&&temp%3==0) {
					sb.append('-');
					flag = true;
				}
				num/=10;
			}
			if(!flag) sb.append(i);
			sb.append(' ');
		}
		System.out.println(sb);
	}

}