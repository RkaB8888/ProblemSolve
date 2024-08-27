import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/**
 * 메모리:11,568kb, 시간:84ms
 */
public class Main {
	static int L;
	static int C;
	static final boolean[] aeiou = new boolean[26];
	static char[] c;
	static char[] item;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		aeiou['a'-'a'] = true;
		aeiou['e'-'a'] = true;
		aeiou['i'-'a'] = true;
		aeiou['o'-'a'] = true;
		aeiou['u'-'a'] = true;
		st = new StringTokenizer(br.readLine());
		c = new char[C];
		for(int i = 0 ; i < C ; i++) {
			c[i] = st.nextToken().charAt(0);
		}
		
		Arrays.sort(c);
		item = new char[L];
		loop(0,0,0,0);
		System.out.println(sb);
	}
	
	public static void loop(int cnt, int str, int ioCnt, int bcnt) {
		
		if(cnt == L) {
			if(ioCnt>=1&&bcnt>=2) {
				for(int i = 0 ; i < L ; i++) {
					sb.append(item[i]);
				}
				sb.append('\n');
			}
			return;
		}
		
		for(int i = str ; i < C ; i++) {
			item[cnt] = c[i];
			if(aeiou[c[i]-'a']) {
				loop(cnt+1,i+1, ioCnt+1,bcnt);
			}
			else {
				loop(cnt+1,i+1,ioCnt,bcnt+1);
			}
		}
		
	}
}