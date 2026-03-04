import java.io.*;
import java.util.*;

/**
 * @description 그리디
 * @performance 메모리: ? KB, 동작시간: ? ms
 * @author python98
 */
public class Main {

	static int cmp(char[] a, int lenA, char[] b, int lenB){
		int len = Math.min(lenA,lenB);
		for(int i = 0 ; i < len ; i++){
			if(a[i]!=b[i]){
				return a[i]-b[i];
			}
		}
		return lenA-lenB;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());

		char[][] map = new char[R][];
		for(int i = 0 ; i < R ; i++){
			map[i] = br.readLine().toCharArray();
		}

		char[] result = new char[Math.max(R,C)];
		int resultLen = 0;
		for(int i = 0 ; i < R ; i++){
			for(int j = 0 ; j < C ; j++){

				if(map[i][j]=='#') continue;

				if(i==0||map[i-1][j]=='#'){
					char[] word = new char[R];
					int idx = 0;
					while(i+idx<R&&map[i+idx][j]!='#'){
						word[idx] = map[i+idx][j];
						idx++;
					}
					if(idx>=2&&(resultLen==0||cmp(result,resultLen, word, idx)>0)) {
						System.arraycopy(word, 0, result,0,idx);
						resultLen = idx;
					}
				}
				if(j==0||map[i][j-1]=='#'){
					char[] word = new char[C];
					int idx = 0;
					while(j+idx<C&&map[i][j+idx]!='#'){
						word[idx] = map[i][j+idx];
						idx++;
					}
					if(idx>=2&&(resultLen==0||cmp(result,resultLen, word, idx)>0)) {
						System.arraycopy(word, 0, result,0,idx);
						resultLen = idx;
					}
				}
			}
		}
		System.out.print(new String(result,0,resultLen));
	}
}