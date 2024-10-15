import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * 메모리 ? KB 시간 ? ms
 * @author python98
 */
public class Main {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		for(int i = 1 ; i < 10 ; i++) {
			sb.append(N).append(" * ").append(i).append(" = ").append(N*i).append('\n');
		}
		System.out.print(sb);
	}
}