import java.io.*;
import java.util.*;

/**
 * @description 역인덱스 + 상태 카운팅
 * @performance 메모리: 73,088 KB, 동작시간: 612 ms
 * @author python98
 */
public class Main {
	static char nextChar() throws IOException {
		int c;
		while ((c = System.in.read()) <= 32);
		return (char)c;
	}

	static int nextInt() throws IOException {
		int c, n = 0;
		while ((n = System.in.read()) <= 32);
		n &= 15;
		while ((c = System.in.read()) > 32) {
			n = (n << 3) + (n << 1) + (c & 15);
		}
		return n;
	}
	static int readWord(char[] buf) throws IOException {
		int c, len = 0;

		while ((c = System.in.read()) <= 32);

		do {
			buf[len++] = (char)c;
		} while ((c = System.in.read()) > 32);

		return len;
	}

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
		StringBuilder sb = new StringBuilder();

		int N = nextInt();
		int M = nextInt();
		Node[] alpha = new Node[26];
		for(int i = 0 ; i < 26 ; i++) {
			alpha[i] = new Node(N);
		}

		char[] input = new char[1000];
		for(int i = 0 ; i < N ; i++) {
			int len = readWord(input);
			for(int j = 0 ; j < len ; j++){
				int idx = input[j] - 'a';
				if(alpha[idx].lastWord==i) continue;
				alpha[idx].lastWord = i;
				alpha[idx].words[alpha[idx].len] = i;
				alpha[idx].len++;
			}
		}

		int result = N;
		int[] words_cnt = new int[N];
		for(int i = 0 ; i < M ; i++) {
			int d = nextInt();
			char c = nextChar();
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