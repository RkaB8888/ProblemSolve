import java.io.*;
import java.util.*;

/**
 * @description 정렬
 * @performance 메모리: ? KB, 동작시간: ? ms
 * @author python98
 */
public class Main {
	static int N, result;
	static String[] words;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		result = N;
		words = new String[N];
		for(int i = 0 ; i < N ; i++) {
			words[i] = br.readLine();
		}

		Arrays.sort(words);
		int result = N;
		for(int i = 1 ; i < N ; i++){
			if(words[i].startsWith(words[i-1])){
				result--;
			}
		}

		System.out.print(result);
	}
}