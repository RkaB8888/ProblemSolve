import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 
 * @author python98 구현 ? KB ? ms
 */
public class Main {
	static int[] position = new int[26];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		Arrays.fill(position, -1);
		char c[] = br.readLine().toCharArray();
		for(int i = 0 ; i < c.length ; i++) {
			int a = c[i]-'a';
			if(position[a]==-1) position[a] = i;
		}
		for(int i = 0 ; i < 26 ; i++) {
			sb.append(position[i]).append(' ');
		}
		System.out.println(sb);
	}
}