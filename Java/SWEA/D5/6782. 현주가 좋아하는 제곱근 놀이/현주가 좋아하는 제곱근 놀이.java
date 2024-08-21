
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * 메모리:29,772KB, 시간:290ms
 * @author SSAFY
 *
 */
public class Solution {
	static double N;
	static long result;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			N = Double.parseDouble(br.readLine());
			result = 0;
			process();
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}
		System.out.println(sb);
	}
	public static void process() {
		while(N>2.0) {
			double temp = Math.sqrt(N);
			double tempCeil = Math.ceil(temp);
			double tempPow = Math.pow(tempCeil, 2);
			result+=tempPow-N;
			N=tempCeil;
			result++;
		}
	}

}
