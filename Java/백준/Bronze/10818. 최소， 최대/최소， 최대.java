import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 입출력 메모리 ? KB 시간 ? ms
 *
 */
public class Main {
	static int N, MIN, MAX;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		MIN = MAX = Integer.parseInt(st.nextToken());
		for(int i = 1 ; i < N ; i++) {
			int num = Integer.parseInt(st.nextToken());
			if(MIN > num) MIN = num;
			if(num > MAX) MAX = num;
		}
		sb.append(MIN).append(' ').append(MAX);
		System.out.print(sb);
	}

}