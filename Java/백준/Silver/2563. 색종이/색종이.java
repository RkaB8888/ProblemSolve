import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		int[][] arr = new int[100][100];

		if (T == 0) {
			System.out.println(0);
			return;
		}
		for (int loop = 0; loop < T; loop++) {
			String[] s = br.readLine().split(" ");
			for (int j = 0; j < 10; j++) { // 해당 포지션 row 순환
				for (int k = 0; k < 10; k++) { // 해당 포지션 col 순환
					arr[Integer.parseInt(s[0]) + j][Integer.parseInt(s[1]) + k]++;
				}
			}
		}
		int result = 0;
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				if (arr[i][j] > 0)
					result++;
			}
		}
		System.out.println(result);
	}

}