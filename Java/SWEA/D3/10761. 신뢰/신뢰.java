import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 *  ?kb ?ms
 */
public class Solution {
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			int oP = 1, bP = 1, nextP = 0, nextLen = 0, 
					btimetum = 0, otimetum = 0, time = 0;
			for(int i = 0 ; i < N ; i++) {
				if(st.nextToken().charAt(0)=='B') {
					nextP = Integer.parseInt(st.nextToken());
					nextLen = Math.abs(nextP-bP);
					if(btimetum>nextLen) {
						btimetum = 1;
						otimetum+=btimetum;
						time+=btimetum;
					}
					else {
						btimetum = nextLen - btimetum + 1;
						otimetum+=btimetum;
						time+=btimetum;
					}
					bP = nextP;
					btimetum = 0;
				}
				else {
					nextP = Integer.parseInt(st.nextToken());
					nextLen = Math.abs(nextP-oP);
					if(otimetum>nextLen) {
						otimetum = 1;
						btimetum+=otimetum;
						time+=otimetum;
					}
					else {
						otimetum = nextLen - otimetum + 1;
						btimetum+=otimetum;
						time+=otimetum;
					}
					oP = nextP;
					otimetum = 0;
				}
			}
			sb.append('#').append(tc).append(' ').append(time).append('\n');
		}
		System.out.println(sb);
	}
}