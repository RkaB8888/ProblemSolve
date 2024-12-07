import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * ?  메모리 ? KB 시간 ? ms
 * 
 * @author python98
 */
public class Main {
	static int T, result;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		for(int i = 0 ; i < T ; i++) {
			char[] word = br.readLine().toCharArray();
			int left = 0, right = word.length-1;
			result = 0;
			dfs(word,left,right, 0);
			sb.append(result).append('\n');
		}
		System.out.print(sb);
	}
	private static boolean dfs(char[] word, int left, int right, int cnt) {
		if(cnt>1) return false;
		while(left<=right) {
			if(word[left]!=word[right]) {
				if(dfs(word,left+1,right,cnt+1)||dfs(word,left,right-1,cnt+1)) {
					result = 1;
					return true;
				} else {
					result = 2;
					return false;
				}
			} else {
				left++;
				right--;
			}
		}
		return true;
	}
}