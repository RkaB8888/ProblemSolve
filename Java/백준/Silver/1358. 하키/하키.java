import java.io.*;
import java.util.*;

/**
 * @description 수학
 * @performance 메모리: ? KB, 동작시간: ? ms
 * @author python98
 */
public class Main {
	static double W, H, X1, Y1, X2, Y2, Y3, H2;
	static int P, cnt;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		W = Double.parseDouble(st.nextToken()); 
		H = Double.parseDouble(st.nextToken());
		X1 = Double.parseDouble(st.nextToken());
		Y1 = Double.parseDouble(st.nextToken());
		X2 = X1 + W;
		Y2 = Y1 + H;
		Y3 = Y1 + H/2;
		H2 = Math.pow(H/2, 2);
		P = Integer.parseInt(st.nextToken());
		
		for(int i = 0 ; i < P ; i++) {
			st = new StringTokenizer(br.readLine());
			if(check(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()))) {
				cnt++;
			}
		}
		System.out.print(cnt);
	}
	private static boolean check(int x, int y) {
		if(x>=X1 && y>=Y1 && x <= X2 && y <= Y2) {
			return true;
		} else if(Math.pow(X1-x, 2)+Math.pow(Y3-y, 2)<=H2) {
			return true;
		} else if(Math.pow(X2-x, 2)+Math.pow(Y3-y, 2)<=H2) {
			return true;
		}
		return false;
	}
	
}