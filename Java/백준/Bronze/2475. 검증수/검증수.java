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
	static int result;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		for(int i = 0 ; i < 5 ; i++) {
			result+=Math.pow(Integer.parseInt(st.nextToken()),2);
		}
		result%=10;
		System.out.print(result);
	}

}