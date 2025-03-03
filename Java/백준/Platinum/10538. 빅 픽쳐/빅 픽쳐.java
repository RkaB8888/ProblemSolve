import java.io.*;
import java.util.*;

/**
 * @description 아호코라식
 * @performance 메모리: ? KB, 동작시간: ? ms
 * @author python98
 */
public class Main {
	
	static int hp, wp, hm, wm, result;
	static String[] p, m;
	static Trie root;
	
	static int[] hpNum;
	static int[][] convert;
	
	static class Trie{
		int end;
		Trie fail;
		Trie[] next;
		
		Trie() {
			next = new Trie[2];
		}
		
		void add (String s, int id) {
			Trie cur = this;
			for(char c : s.toCharArray()) {
				int idx = charToIdx(c);
				if(cur.next[idx]==null) {
					cur.next[idx] = new Trie();
				}
				cur = cur.next[idx];
			}
			if(cur.end==0) {
				cur.end = id+1;
			}
		}
		
		void build() {
			Queue<Trie> q = new ArrayDeque<>();
			this.fail = this;
			
			for (int i = 0; i < 2; i++) {
				if(next[i]!=null) {
					next[i].fail = this;
					q.add(next[i]);
				} else {
					next[i] = this;
				}
			}
			
			while(!q.isEmpty()) {
				Trie cur = q.poll();
				for(int i = 0 ; i < 2 ; i++) {
					if(cur.next[i] != null) {
						Trie child = cur.next[i];
						child.fail = cur.fail.next[i];
						q.add(child);
					} else {
						cur.next[i] = cur.fail.next[i];
					}
				}
			}
		}
		
		int[] convertRow(String text) {
			int len = text.length();
			int[] arr = new int[len];
			Trie cur = this;
			for(int i = 0 ; i < len ; i++) {
				int idx = charToIdx(text.charAt(i));
				cur = cur.next[idx];
				arr[i] = cur.end;
			}
			return arr;
		}
	}
	
	static int[] getLps(int[] pat) {
		int n = pat.length;
		int[] lps = new int[n];
		int idx = 0;
		int i = 1;
		while(i<n) {
			if(pat[i] == pat[idx]) {
				lps[i++] = ++idx; // 같다면 i의 lps는 idx의 다음 인덱스를 가리킴
			} else if(idx > 0) {
				idx = lps[idx-1];
			} else {
				lps[i++] = 0;
			}
		}
		return lps;
	}
	
	static int kmp(int[] pat, int[] txt) {
		int[] lps = getLps(pat);
		int i = 0, j = 0, cnt = 0;
		while(i < txt.length) {
			if(txt[i]==pat[j]) {
				i++;j++;
				if(j==pat.length) {
					cnt++;
					j = lps[j-1];
				}
			} else if(j > 0) {
				j = lps[j-1];
			} else {
				i++;
			}
		}
		return cnt;
	}
	
	static int charToIdx(char c) {
		return c=='o'? 1 : 0;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		hp = Integer.parseInt(st.nextToken());
		wp = Integer.parseInt(st.nextToken());
		hm = Integer.parseInt(st.nextToken());
		wm = Integer.parseInt(st.nextToken());
		
		p = new String[hp];
		m = new String[hm];
		for(int i = 0 ; i < hp ; i++) {
			p[i] = br.readLine();
		}
		for(int i = 0 ; i < hm ; i++) {
			m[i] = br.readLine();
		}
		
		Map<String, Integer> map = new HashMap<>();
		hpNum = new int[hp];
		root = new Trie();
		int uniqCnt = 0;
		for(int i = 0 ; i < hp ; i++) {
			if(!map.containsKey(p[i])) {
				map.put(p[i], uniqCnt);
				uniqCnt++;
				root.add(p[i], map.get(p[i]));
			}
			hpNum[i] = map.get(p[i]) + 1;
		}
		
		root.build();
		
		convert = new int[hm][];
		for(int i = 0 ; i < hm ; i++) {
			convert[i] = root.convertRow(m[i]);
		}
		
		result = 0;
		for(int j = wp-1 ; j < wm ; j++) {
			int[] colData = new int[hm];
			for(int i = 0 ; i < hm ; i++) {
				colData[i] = convert[i][j];
			}
			result += kmp(hpNum,colData);
		}
		System.out.print(result);
	}
}