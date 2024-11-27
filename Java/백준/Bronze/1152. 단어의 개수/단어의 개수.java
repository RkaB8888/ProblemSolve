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
	
	public static void main(String[] args) throws IOException {
		System.out.print(new StringTokenizer(new BufferedReader(new InputStreamReader(System.in)).readLine()).countTokens()); 
	}
}