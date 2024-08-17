import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int M;
	static int[] nums;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		nums = new int[M];
		loop(0, 0); //몇번째 요소인지, 어디부서 탐색을 시작할지
		
		System.out.println(sb);
		
	}
	//모든 경우를 탐색. 단, 중복은 없이 오름차순으로
	public static void loop(int cnt, int str) {
		if(cnt==M) {
			for(int i=0 ; i < M ; i++) {
				sb.append(nums[i]).append(' ');
			}
			sb.append('\n');
			return;
		}
		for(int i =str ; i < N ; i++ ) {
			nums[cnt] = i+1;
			loop(cnt+1,i+1);
		}
	}
}