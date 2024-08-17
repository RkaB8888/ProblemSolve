
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int map[][] = new int[1001][1001];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int paperNum = Integer.parseInt(br.readLine());
		int position[][] = new int[paperNum + 1][4];
		for (int loop = 1; loop <= paperNum; loop++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			position[loop][0] = Integer.parseInt(st.nextToken());
			position[loop][1] = Integer.parseInt(st.nextToken());
			position[loop][2] = position[loop][0] + Integer.parseInt(st.nextToken());
			position[loop][3] = position[loop][1] + Integer.parseInt(st.nextToken());
			for (int i = position[loop][0]; i < position[loop][2]; i++) {
				for (int j = position[loop][1]; j < position[loop][3]; j++) {
					map[i][j] = loop;
				}
			}
		}

		for (int loop = 1; loop <= paperNum; loop++) {
			int result = 0;
			for (int i = position[loop][0]; i < position[loop][2]; i++) {
				for (int j = position[loop][1]; j < position[loop][3]; j++) {
					if (map[i][j] == loop)
						result++;
				}
			}
			System.out.println(result);
		}

	}

}
