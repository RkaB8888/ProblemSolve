
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Solution {
	static int N;// 단어 수
	static int result;
	static List<Set<Character>> charSetList = new LinkedList<>();
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
///////////////////////입력 시작//////////////////////////////////////////
		int testCase = Integer.parseInt(br.readLine());
		for (int t = 1; t <= testCase; t++) {
			charSetList.clear();
			N = Integer.parseInt(br.readLine());
			result = 0;
			for (int i = 0; i < N; i++) {
				Set<Character> charSet = new HashSet<>();
				String temp = br.readLine();
				for (int j = 0; j < temp.length(); j++) {
					char c = temp.charAt(j);
					if ('a' <= c && c <= 'z') {
						charSet.add(c);
					}
				}
				charSetList.add(charSet);
			}
///////////////////////입력 끝//////////////////////////////////////////
			loop(0, new HashSet<>());

			sb.append('#').append(t).append(' ').append(result).append('\n');
		}
		System.out.println(sb);
	}

	public static void loop(int cnt, Set<Character> total) {
		if (cnt == N) {
			if(total.size()==26) result++;
			return;
		}
		loop(cnt+1,total);
		Set<Character> tempTotal = new HashSet<>(total);
		tempTotal.addAll(charSetList.get(cnt));
		loop(cnt+1,tempTotal);
	}

}