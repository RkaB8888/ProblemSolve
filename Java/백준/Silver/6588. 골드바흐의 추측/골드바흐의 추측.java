import java.io.*;
import java.util.*;

/**
 * @description odd-only sieve + bitset + long
 * @performance 메모리: ? KB, 동작시간: ? ms
 * @author python98
 */
public class Main {

	static final int MAX = 1000000;
	static long[] prime = new long[(MAX>>7)+1];

	private static boolean get(int n){
		return ((prime[n>>7] & (1L<<((n>>1)&63))) != 0);
	}

	private static void set(int n) {
		prime[n>>7] |= (1L<<(((n>>1)&63)));
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		for(int i = 3 ; i * i <= MAX ; i+=2) {
			if(!get(i)){
				int step = i<<1;
				for(int j = i * i ; j <= MAX ; j += step) {
					set(j);
				}
			}
		}

		while(true){
			int n = Integer.parseInt(br.readLine());
			boolean find = false;
			if(n==0) break;
			for(int i = 3 ; i <= n/2 ; i+=2) {
				if(!get(i)&&!get(n-i)) {
					find = true;
					sb.append(n).append(" = ").append(i).append(" + ").append(n-i).append('\n');
					break;
				}
			}
			if(!find) sb.append("Goldbach's conjecture is wrong.\n");
		}
		System.out.print(sb);
	}
}