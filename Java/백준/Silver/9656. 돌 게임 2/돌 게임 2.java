import java.io.*;
/**
 * 수학 메모리 ? KB 시간 ? ms
 * 
 * @author python98
 */
public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		if((Integer.parseInt(br.readLine())&1)==1) {
			System.out.print("CY");
		} else {
			System.out.print("SK");
		}
	}
}