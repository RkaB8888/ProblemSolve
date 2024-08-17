import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		int col = Integer.parseInt(s[0]) + 1;
		int row = Integer.parseInt(s[1]) + 1;
		int[][] arr = new int[row][col];
		for (int i = 0; i < row; i++) {
			arr[i][0] = 1;
			arr[i][col - 1] = 1;
		}
		for (int i = 0; i < col; i++) {
			arr[0][i] = 1;
			arr[row - 1][i] = 1;
		}
		int T = Integer.parseInt(br.readLine());
		for (int i = 0; i < T; i++) {
			s = br.readLine().split(" ");
			int way = Integer.parseInt(s[0]);
			int r = Integer.parseInt(s[1]);

			switch (way) {
			case 1:
				arr[0][r] = 10;
				break;
			case 2:
				arr[row - 1][r] = 10;
				break;
			case 3:
				arr[r][0] = 10;
				break;
			default:
				arr[r][col - 1] = 10;
				break;

			}

		}
		s = br.readLine().split(" ");
		int way = Integer.parseInt(s[0]);
		int r = Integer.parseInt(s[1]);
		int strR = 0, strC = 0;
		switch (way) {
		case 1:
			strR = 0;
			strC = r;
			break;
		case 2:
			strR = row - 1;
			strC = r;
			break;
		case 3:
			strR = r;
			strC = 0;
			break;
		default:
			strR = r;
			strC = col - 1;
			break;

		}
		int result = bfs(arr, row, col, strR, strC);
		System.out.println(result);
	}

	public static int bfs(int[][] map, int rowLen, int colLen, int r, int c) {
		int result = 0;
		int[][] queue = new int[200][3];
		int str = 0, end = 0;
		queue[end][0] = r;
		queue[end][1] = c;
		queue[end++][2] = 0;
		map[r][c] = 2;
		int[][] dr_dc = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
		while (str != end) {
			int curR = queue[str][0];
			int curC = queue[str][1];
			int curCnt = queue[str++][2];
			str %= 200;
			for (int i = 0; i < 4; i++) {
				int nextR = curR + dr_dc[i][0];
				int nextC = curC + dr_dc[i][1];
				int nextCnt = curCnt + 1;
				if (nextR < 0 || nextC < 0 || nextR >= rowLen || nextC >= colLen)
					continue;
				if (map[nextR][nextC] == 0 || map[nextR][nextC] == 2)
					continue;
				if (map[nextR][nextC] == 10)
					result += nextCnt;
				map[nextR][nextC] = 2;
				queue[end][0] = nextR;
				queue[end][1] = nextC;
				queue[end++][2] = nextCnt;
				end %= 200;
			}

		}
		return result;
	}

}