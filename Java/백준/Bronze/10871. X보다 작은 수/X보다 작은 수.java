import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 입출력 메모리 ? KB 시간 ? ms
 *
 */
public class Main {
	static int N, X;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; i++) {
			int nextNum = Integer.parseInt(st.nextToken());
			if(nextNum<X) {
				sb.append(nextNum).append(' ');
			}
		}
		System.out.print(sb);
	}

}