import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 메모리 ? KB 시간 ? ms 다시 풀어봄
 * 
 * @author python98
 */
public class Main {
	static int N, adjMatrix[][], result = Integer.MAX_VALUE;
	static boolean isSelect[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		adjMatrix = new int[N][N];
		isSelect = new boolean[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				adjMatrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		isSelect[0] = true;
		cycle(0, 0, 0, 0);
		System.out.print(result);
	}

	private static void cycle(int cnt, int sum, int str, int cur) {
		if (sum >= result)
			return;
		if (cnt == N) {
			if (adjMatrix[cur][str] != 0) {
				result = Math.min(sum+adjMatrix[cur][str], result);
			}
			return;
		}
		if (cnt == 0) {
			for (int i = 0; i < N; i++) {
				isSelect[i] = true;
				cycle(cnt + 1, sum, i, i);
				isSelect[i] = false;
			}
		} else {
			for (int i = 0; i < N; i++) {
				if (adjMatrix[cur][i] == 0 || isSelect[i])
					continue;
				isSelect[i] = true;
				cycle(cnt + 1, sum + adjMatrix[cur][i], str, i);
				isSelect[i] = false;
			}
		}
	}
}