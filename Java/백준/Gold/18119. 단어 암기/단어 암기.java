import java.io.*;
import java.util.*;

/**
 * @description 역인덱스 + 상태 카운팅
 * @performance 메모리: ? KB, 동작시간: ? ms
 * @author python98
 */
public class Main {

	private static class Node{
		boolean rem;
		int len, lastWord;
		int[] words;

		Node(int n){
			this.rem = true;
			this.len = 0;
			this.words = new int[n];
			this.lastWord = -1;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		Node[] alpha = new Node[26];
		for(int i = 0 ; i < 26 ; i++) {
			alpha[i] = new Node(N);
		}

		for(int i = 0 ; i < N ; i++) {
			char[] input = br.readLine().toCharArray();
			for(char c : input){
				if(alpha[c-'a'].lastWord==i) continue;
				alpha[c-'a'].lastWord = i;
				alpha[c-'a'].words[alpha[c-'a'].len] = i;
				alpha[c-'a'].len++;
			}
		}

		int result = N;
		int[] words_cnt = new int[N];
		Arrays.fill(words_cnt,0);
		for(int i = 0 ; i < M ; i++) {
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken());
			char c = st.nextToken().charAt(0);
			if(d==1) { // 잊어야 한다
				if(!alpha[c-'a'].rem) continue;
				Node node = alpha[c-'a'];
				node.rem = false;
				for(int j = 0; j < node.len; j++) {
					int idx = node.words[j];
					if(words_cnt[idx]==0) result--;
					words_cnt[idx]--;
				}
			}else { // 기억해야 한다
				if(alpha[c-'a'].rem) continue;
				Node node = alpha[c-'a'];
				node.rem = true;
				for(int j = 0; j < node.len; j++) {
					int idx = node.words[j];
					words_cnt[idx]++;
					if(words_cnt[idx]==0) result++;
				}
			}
			sb.append(result).append('\n');
		}
		System.out.print(sb);
	}
}