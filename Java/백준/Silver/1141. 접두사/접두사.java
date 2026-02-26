import java.io.*;
import java.util.*;

/**
 * @description 정렬 + Trie
 * @performance 메모리: ? KB, 동작시간: ? ms
 * @author python98
 */
public class Main {
	static int N, result;
	static String[] words;

	static class Trie{
		Trie[] next;
		void init(){
			next = new Trie[26];
		}
		boolean add(String s){ // 새로운 단어라면 true 반환
			Trie[] cur = this.next;
			boolean flag = false;
			for(char c : s.toCharArray()){
				if(cur[c-'a'] == null) {
					cur[c-'a'] = new Trie();
					cur[c-'a'].init();
					flag = true;
					cur = cur[c-'a'].next;
				}
				else{
					cur = cur[c-'a'].next;
				}
			}
			return flag;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		result = N;
		words = new String[N];
		for(int i = 0 ; i < N ; i++) {
			words[i] = br.readLine();
		}

		Trie trie = new Trie();
		trie.init();

		Arrays.sort(words,(a,b)-> b.length()-a.length());

		for(String s : words){
			if(!trie.add(s)){
				result--;
			}
		}
		System.out.print(result);
	}
}