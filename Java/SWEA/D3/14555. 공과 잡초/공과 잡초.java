import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 *  23,692kb 131ms
 */
public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			String s = br.readLine();
			int cnt = 0;
			for(int i = 0, iEnd = s.length() ; i < iEnd ; i++) {
				char c = s.charAt(i);
				if(c=='(') cnt++;
				else if(c==')') {
					if(i-1<0||s.charAt(i-1)!='(') cnt++;
				}
			}
			sb.append('#').append(tc).append(' ').append(cnt).append('\n');
		}
		System.out.println(sb);
	}
}