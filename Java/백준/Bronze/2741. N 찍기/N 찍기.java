import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 
 * 입출력 메모리 ? KB 시간 ? ms
 *
 */
public class Main {
	static int N;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		for(int i = 1 ; i <= N ; i++) {
			sb.append(i).append('\n');
		}
		System.out.print(sb);
	}

}