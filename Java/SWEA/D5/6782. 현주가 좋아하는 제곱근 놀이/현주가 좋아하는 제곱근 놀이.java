import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * 메모리:29,772KB, 시간:290ms
 * @author SSAFY
 *
 */
public class Solution {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			double N = Double.parseDouble(br.readLine());
			int result = process(N);
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}
		System.out.println(sb);
	}
	public static int process(double N) {
		int result = 0;
		while(N>2) {
			double tempCeil = Math.ceil(Math.sqrt(N));
			double tempPow = Math.pow(tempCeil, 2);
			result+=tempPow-N+1;
			N=tempCeil;
		}
		return result;
	}

}