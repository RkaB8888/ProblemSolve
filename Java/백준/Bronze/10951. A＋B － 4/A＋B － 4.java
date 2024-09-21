import java.io.*;
import java.util.*;

public class Main {
// 11756 64
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String s;
		while ((s = br.readLine()) != null) {
			StringTokenizer st = new StringTokenizer(s);
			sb.append(Integer.parseInt(st.nextToken()) + Integer.parseInt(st.nextToken())).append('\n');
		}
		System.out.println(sb);
	}

}