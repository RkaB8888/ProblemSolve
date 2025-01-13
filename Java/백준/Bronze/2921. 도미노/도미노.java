import java.io.*;

/**
 * @description 수학
 * @performance 메모리: ? KB, 동작시간: ? ms
 * @author python98
 */
public class Main {
	static int N, result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		
		for(int i = 1 ; i <= N ; i++) {
			result+=i*(i+1) + (i+1)*i/2;
		}
		System.out.print(result);
	}
}