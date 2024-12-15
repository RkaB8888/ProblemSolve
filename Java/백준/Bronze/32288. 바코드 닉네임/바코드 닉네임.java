import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * 출력 메모리 ? KB 시간 ? ms
 * 
 * @author python98
 */
public class Main {
	static int N;
	static char[] name;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		name = br.readLine().toCharArray();
		for(int i = 0 ; i < N ; i++) {
			if(name[i]=='I') sb.append('i');
			else sb.append('L');
		}
		System.out.print(sb);
	}
}