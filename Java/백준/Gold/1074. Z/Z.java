import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author python98 분할정복 ? KB ? ms
 */
public class Main {
	static int N, r, c, result;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		process();

		System.out.print(result);
	}

	private static void process() {
		if(N==0) return;
		int unit = 1 << (2 * N - 2);
		if (r >> (N - 1) >= 1) {
			if (c >> (N - 1) >= 1) {// 4번째 칸
				result+=unit*3;
				r-=(1<<N-1);
				c-=(1<<N-1);
			} else {// 3번째 칸
				result+=unit*2;
				r-=(1<<N-1);
			}
		} else {
			if (c >> (N - 1) >= 1) {// 2번째 칸
				result+=unit;
				c-=(1<<N-1);
			} else {// 1번째 칸
				
			}
		}
		N--;
		process();
	}

}