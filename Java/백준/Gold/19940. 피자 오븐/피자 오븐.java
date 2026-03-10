import java.io.*;
import java.util.*;

/**
 * @description 그리디
 * @performance 메모리: ? KB, 동작시간: ? ms
 * @author python98
 */
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int t = 0 ; t < T ; t++){
			int N = Integer.parseInt(br.readLine());
			int ADDH = N/60; // 90부터는 한번 더 누르는게 나음
			N%=60;
			if(N>35) {
				ADDH++;
				N-=60;
			}
			int ADDT=0, MINT=0, ADDO=0, MINO=0;
			if(N>0) {
				ADDT = N/10;
				N%=10;
				if(N>5) {
					ADDT++;
					MINO=10-N;
				} else {
					ADDO = N;
				}
			} else {
				int M = N*(-1);
				MINT = M/10;
				M%=10;
				if(M>5) {
					MINT++;
					ADDO=10-M;
				} else {
					MINO = M;
				}
			}
			sb.append(ADDH).append(' ').append(ADDT).append(' ').append(MINT).append(' ').append(ADDO).append(' ').append(MINO).append('\n');

		}
		System.out.print(sb);
	}
}