import java.io.*;
import java.util.StringTokenizer;

/**
 * @description
 *    - 구현 :<br>
 * @performance 메모리: ? KB, 동작시간: ? ms
 * @author python98
 */
public class Main {
	static final String T = "Nice", F = "Nae ga wae";
	static int A, B;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int skadmsgkrrl = 8 - Integer.parseInt(st.nextToken()), TMfprlgkrrl = 10 - skadmsgkrrl;
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		
		for(int i = 0 ; i < skadmsgkrrl ; i++) {
			st = new StringTokenizer(br.readLine());
			int X = 3*Integer.parseInt(st.nextToken()), Y = 3*Integer.parseInt(st.nextToken());
			if(X>=18) {
				A += 18;
				B += 18;
			} else if(X+Y>=18) {
				A += X;
				B += 18;
			} else {
				A += X;
				B += X + Y;
			}
		}
		
		if(A >= 66 && B >= 130) {
			sb.append(T);
		} else {
			sb.append(F);
		}
		
		for(int i = 0 ; i < TMfprlgkrrl ; i++) {
			st = new StringTokenizer(br.readLine());
		}
		
		System.out.print(sb);
	}
}