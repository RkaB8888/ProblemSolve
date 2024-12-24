import java.io.*;
import java.util.*;

/**
 * 문자열 메모리 15,468 KB 시간 100 ms 하나씩 비교
 * 
 * @author python98
 */
public class Main {
	static int N, K, i, j, iEnd, jEnd;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static String line;
	static char[] s, t;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while ((line = br.readLine())!=null) {
			st = new StringTokenizer(line);
			s = st.nextToken().toCharArray();
			t = st.nextToken().toCharArray();
			i = 0; j = 0; iEnd = s.length; jEnd = t.length - iEnd + 1;
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