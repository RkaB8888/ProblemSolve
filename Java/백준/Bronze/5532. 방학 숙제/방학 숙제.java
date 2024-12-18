import java.io.*;
/**
 * 수학 메모리 ? KB 시간 ? ms
 * 
 * @author python98
 */
public class Main {
	static int L,A,B,C,D,result;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		L = Integer.parseInt(br.readLine());
		A = Integer.parseInt(br.readLine());
		B = Integer.parseInt(br.readLine());
		C = Integer.parseInt(br.readLine());
		D = Integer.parseInt(br.readLine());
		result = L - Math.max((A+C-1)/C, (B+D-1)/D);
		System.out.print(result);
	}
}