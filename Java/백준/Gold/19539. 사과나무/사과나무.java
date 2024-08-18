
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int[] idealH = new int[N];
		int[] cnt = {0,0};
		boolean flag = false;
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			idealH[i] = Integer.parseInt(st.nextToken());
			cnt[0]+=idealH[i]%2;
			cnt[1]+=idealH[i]/2;
		}
		if(cnt[1]<cnt[0]) flag = false;
		else if(cnt[1]==cnt[0]) flag= true;
		else {
			int diff = cnt[1]-cnt[0];
			if(diff%3!=0) flag = false;
			else flag = true;
		}
		if (flag)
			System.out.println("YES");
		else
			System.out.println("NO");
	}

}
