
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int[] cnt = {0,0};
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int temp = Integer.parseInt(st.nextToken());
			cnt[0]+=temp%2;
			cnt[1]+=temp/2;
		}
		if(cnt[1]<cnt[0]) System.out.println("NO");
		else if(cnt[1]==cnt[0]) System.out.println("YES");
		else {
			int diff = cnt[1]-cnt[0];
			if(diff%3!=0) System.out.println("NO");
			else System.out.println("YES");
		}
	}

}
