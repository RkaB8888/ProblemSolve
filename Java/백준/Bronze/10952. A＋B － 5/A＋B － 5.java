import java.io.*;
import java.util.*;

// 11608 68

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;
        while(true) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
            if(a==0&&b==0) break;
			sb.append(a + b).append('\n');
		}
		System.out.println(sb);
	}

}