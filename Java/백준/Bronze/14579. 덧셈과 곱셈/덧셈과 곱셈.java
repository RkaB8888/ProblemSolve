import java.io.*;
import java.util.*;
/**
 * 수학 메모리 11,680 KB 시간 64 ms
 * 
 * @author python98
 */
public class Main {
	static int a,b,c,result=1;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		a=Integer.parseInt(st.nextToken());
		b=Integer.parseInt(st.nextToken());
		int i = 1;
		for(; i < a ; i++) {
			c+=i;
			c%=14579;
		}
		for(; i <= b ; i++) {
			c+=i;
			c%=14579;
			result*=c;
			result%=14579;
		}
		System.out.print(result);
	}
}