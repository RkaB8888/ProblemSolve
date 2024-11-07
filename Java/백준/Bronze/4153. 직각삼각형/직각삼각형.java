import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int a, b, c;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		while(a!=0&&b!=0&&c!=0) {
			int A, B, C;
			if(a>b) {
				if(a>c) {
					A = a;
					B = b;
					C = c;
				} else {
					A = c;
					B = b;
					C = a;
				}
			} else {
				if(b>c) {
					A = b;
					B = a;
					C = c;
				} else {
					A = c;
					B = b;
					C = a;
				}
			}
			if(A*A==B*B+C*C) {
				sb.append("right").append('\n');
			} else {
				sb.append("wrong").append('\n');
			}
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
		}
		System.out.print(sb);
	}

}