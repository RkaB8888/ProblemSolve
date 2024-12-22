import java.io.*;
import java.util.*;
/**
 * 수학 메모리 ? KB 시간 ? ms
 * 
 * @author python98
 */
public class Main {
	static int M,N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		if(M>N) {
			System.out.print((N-1)*2+1);
		} else {
			System.out.print((M-1)*2);
		}
	}
}