import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * 수학 메모리 ? KB 시간 ? ms
 * 
 * @author python98
 */
public class Main {
	static int N, result;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		for(int i = 4 ; i < N ; i+=2) {
			result+=(N-i)/2;
		}
		System.out.print(result);
	}
}