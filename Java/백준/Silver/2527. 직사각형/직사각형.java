
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int T = 0; T < 4; T++) {
			String[] s = br.readLine().split(" ");
			int rect[][] = new int[2][4];
			for (int i = 0, idx = 0; i < 2; i++) {
				for (int j = 0; j < 4; j++, idx++) {
					rect[i][j] = Integer.parseInt(s[idx]);
				}
			}

			if (rect[1][0] > rect[0][2] || rect[1][1] > rect[0][3] || rect[1][2] < rect[0][0]
					|| rect[1][3] < rect[0][1]) {
				System.out.println("d");
			}

			else if (rect[1][0] < rect[0][2] && rect[1][1] < rect[0][3] && rect[1][2] > rect[0][0]
					&& rect[1][3] > rect[0][1]) {
				System.out.println("a");
			}

			else if (rect[0][0] == rect[1][2] && rect[0][1] == rect[1][3]
					|| rect[0][2] == rect[1][0] && rect[0][3] == rect[1][1]
					|| rect[0][0] == rect[1][2] && rect[0][3] == rect[1][1]
					|| rect[0][2] == rect[1][0] && rect[0][1] == rect[1][3]) {
				System.out.println("c");
			}

			else {
				System.out.println("b");
			}
		}
	}

}
