import java.io.*;
import java.util.StringTokenizer;
/**
 * 구현 메모리 ? KB 시간 ? ms
 * 
 * @author python98
 */
public class Main {
	static int cnt;
	static int X,Y;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		X = Y = Integer.parseInt(st.nextToken());
		cnt = Integer.parseInt(st.nextToken());
		
		for(int i = 0, x = 0, y = 0 ; i < cnt ; i++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			if(x>=X||y>=Y) continue;
			if(X*y > x*Y) {
				Y = y;
			} else {
				X = x;
			}
		}
		System.out.print(X*Y);
	}
}