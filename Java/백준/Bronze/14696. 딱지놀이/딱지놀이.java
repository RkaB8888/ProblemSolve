import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int roundNum = Integer.parseInt(br.readLine());
		for (int T = 0; T < roundNum; T++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int cntA = Integer.parseInt(st.nextToken());
			int arrA[] = new int[5];
			for (int i = 0; i < cntA; i++) {
				arrA[Integer.parseInt(st.nextToken())]++;
			}
			st = new StringTokenizer(br.readLine());
			int cntB = Integer.parseInt(st.nextToken());
			int arrB[] = new int[5];
			for (int i = 0; i < cntB; i++) {
				arrB[Integer.parseInt(st.nextToken())]++;
			}
			boolean flag = true;
			for (int i = 4; i > 0; i--) {
				if (arrA[i] > arrB[i]) {
					sb.append('A').append('\n');
					flag = false;
					break;
				} else if ((arrA[i] < arrB[i])) {
					sb.append('B').append('\n');
					flag = false;
					break;
				}
			}
			if (flag)
				sb.append('D').append('\n');
		}
		System.out.println(sb);
	}
}