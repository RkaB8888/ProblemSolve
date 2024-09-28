import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * 
 * @author python98
 * ? KB ? ms
 *
 */
public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int A = Integer.parseInt(st.nextToken()), B = Integer.parseInt(st.nextToken());
		sb.append(A+B).append('\n').append(A-B).append('\n').append(A*B).append('\n').append(A/B).append('\n').append(A%B);
		System.out.println(sb);
	}
}