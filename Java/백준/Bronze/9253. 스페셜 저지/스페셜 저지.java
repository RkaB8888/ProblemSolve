import java.io.*;
/**
 * 구현 메모리 ? KB 시간 ? ms
 * 
 * @author python98
 */
public class Main {
	static String A,B,C;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		A = br.readLine();
		B = br.readLine();
		C = br.readLine();
		if(A.contains(C)&&B.contains(C)) {
			System.out.print("YES");
		} else {
			System.out.print("NO");
		}
	}
}