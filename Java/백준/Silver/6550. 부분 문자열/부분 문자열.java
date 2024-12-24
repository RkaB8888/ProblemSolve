import java.io.*;
import java.util.*;

/**
 * 문자열 메모리 ? KB 시간 ? ms 하나씩 비교
 * 
 * @author python98
 */
public class Main {
	static int N, K;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		String line;
		while ((line = br.readLine())!=null) {
			st = new StringTokenizer(line);
			char[] s = st.nextToken().toCharArray();
			char[] t = st.nextToken().toCharArray();
			int i = 0, j = 0, iEnd = s.length, jEnd = t.length - iEnd + 1;
			while (i < iEnd && j < jEnd) {
				if (s[i] == t[j]) {
					i++;
					jEnd++;
				}
					j++;
			}
			if(i==iEnd) {
				sb.append("Yes\n");
			} else {
				sb.append("No\n");
			}
		}
		System.out.print(sb);
	}
}