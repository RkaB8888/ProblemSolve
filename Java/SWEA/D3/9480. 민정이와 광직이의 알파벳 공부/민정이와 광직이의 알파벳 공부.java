
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
	static int N;// 단어 수
	static int result;
	static int[] bitmaskList;
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
///////////////////////입력 시작//////////////////////////////////////////
		int testCase = Integer.parseInt(br.readLine());
		for (int t = 1; t <= testCase; t++) {
			N = Integer.parseInt(br.readLine());
			result = 0;
			bitmaskList = new int[N];
			for (int i = 0; i < N; i++) {
				int bitmask = 0;
				String temp = br.readLine();
				for (int j = 0; j < temp.length(); j++) {
					char c = temp.charAt(j);
					if ('a' <= c && c <= 'z') {
						bitmask|=1<<(c-'a');
					}
				}
				bitmaskList[i] = bitmask;
			}
///////////////////////입력 끝//////////////////////////////////////////
			loop(0, 0);

			sb.append('#').append(t).append(' ').append(result).append('\n');
		}
		System.out.println(sb);
	}

	public static void loop(int cnt, int bitmaskTotal) {
		if (cnt == N) {
			if(Integer.bitCount(bitmaskTotal)==26) result++;
			return;
		}
		loop(cnt+1,bitmaskTotal);
		loop(cnt+1,bitmaskTotal|bitmaskList[cnt]);
	}

}