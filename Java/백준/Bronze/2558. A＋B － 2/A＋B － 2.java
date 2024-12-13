import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * 
 * 출력 메모리 ? KB 시간 ? ms
 * 
 * @author python98
 *
 */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print(Integer.parseInt(br.readLine())+Integer.parseInt(br.readLine()));
	}
}