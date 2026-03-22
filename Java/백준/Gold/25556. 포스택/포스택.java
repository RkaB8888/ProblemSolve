import java.io.*;
import java.util.*;

/**
 * @description 그리디 + 스택
 * @performance 메모리: ? KB, 동작시간: ? ms
 * @author python98
 */
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] s = new int[4];
		Arrays.fill(s,0);
		StringTokenizer st = new StringTokenizer(br.readLine());
		boolean flag = true;
		for(int i = 0 ; i < N ; i++) {
			int cur = Integer.parseInt(st.nextToken());
			flag = true;
			for(int j = 0 ; j < 4 ; j++){
				if(s[j]<cur){
					s[j] = cur;
					flag = false;
					break;
				}
			}
			if(flag) break;
		}
		if(flag) {
			System.out.print("NO");
		} else {
			System.out.print("YES");
		}
	}
}