import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
/**
 * 메모리 ? KB 시간 ? ms 다시 풀어봄
 * 메모이제이션
 * @author python98
 */
public class Main {
	static int N, memo[];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		if(N==1) System.out.print(0);
		else {
			memo = new int[N+1];
			bfs();
			System.out.print(memo[1]);
		}
	}
	private static void bfs() {
		Queue<Integer> q = new ArrayDeque<Integer>();
		q.add(N);
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			int p = cur%3;
			int d = cur/3;
			if(p==0&&memo[d]==0) {
				memo[d] = memo[cur]+1;
				if(d==1) return;
				q.add(d);
			}
			p = cur&1;
			d = cur/2;
			if(p==0&&memo[d]==0) {
				memo[d] = memo[cur]+1;
				if(d==1) return;
				q.add(d);
			}
			d = cur-1;
			if(d>=1&&memo[d]==0) {
				memo[d] = memo[cur]+1;
				if(d==1) return;
				q.add(d);
			}	
		}
	}

}