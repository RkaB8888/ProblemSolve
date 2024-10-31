import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * 메모리 131,952 KB 시간 1580 ms 
 * 1.trie에 색상 이름 저장
 * 2.set에 닉네임 저장
 * 3.팀명 앞글자에서 trie에 있는지 확인
 * 4.trie에서 해당 색상이 나타나면 나머지 부분이 set에 있는지 확인
 * @author python98
 */
public class Main {
	static int C, N, Q;
	static Node rootNode = new Node();
	static Set<String> nickNames = new HashSet<>();
	static class Node {
		int cnt;
		Node[] nextNode = new Node[26];
		boolean exist;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		for(int i = 0 ; i < C ; i++) {
			setTrie(br.readLine().toCharArray());
		}
		
		for(int i = 0 ; i < N ; i++) {
			nickNames.add(br.readLine());
		}
		
		Q = Integer.parseInt(br.readLine());
		
		for(int i = 0 ; i < Q ; i++) {
			sb.append(check(br.readLine())).append('\n');
		}
		System.out.print(sb);
	}
	private static String check(String teamName) {
		int i = 0, len = teamName.length();
		Node cur = rootNode;
		while(i<len) {
			int index = teamName.charAt(i)-'a';
			if(cur.nextNode[index]==null||cur.nextNode[index].cnt==0) {
				return "No";
			}
			cur = cur.nextNode[index];
			if(cur.cnt==0) {
				return "No";
			}
			if(cur.exist) {
				String temp = teamName.substring(i+1);
				if(nickNames.contains(temp)) {
					return "Yes";
				}
			}
			i++;
		}
		return "No";
	}
	private static void setTrie(char[] color) {
		int len = color.length;
		Node cur = rootNode;
		cur.cnt++;
		
		for (int i = 0; i < len; i++) {
			int index = color[i] - 'a';
			if (cur.nextNode[index] == null) {
				cur.nextNode[index] = new Node();
			}
			cur = cur.nextNode[index];
			cur.cnt++;
		}
		cur.exist = true;
	}
}