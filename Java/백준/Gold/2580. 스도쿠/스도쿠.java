import java.io.*;
import java.util.*;

/**
 * @description 조합, 완전탐색
 * @performance 메모리: ? KB, 동작시간: ? ms
 * @author python98
 */
public class Main {
	static int blankCnt;
	static int[][] map, blanks;
	static boolean flag;
	static boolean[][] rowCheck, colCheck;
	static boolean[][][] nineCheck;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		map = new int[9][9];
		rowCheck = new boolean[9][10];
		colCheck = new boolean[9][10];
		nineCheck = new boolean[3][3][10];
		blanks = new int[81][2];
		StringTokenizer st;
		for (int i = 0; i < 9; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (map[i][j] == 0) {
					blanks[blankCnt][0] = i;
					blanks[blankCnt++][1] = j;
				}
			}
		}
		checkPossible();
		combination(0);
		for(int i = 0 ; i < 9 ; i++) {
			for(int j = 0 ; j < 9 ; j++) {
				sb.append(map[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.print(sb);
	}

	private static void combination(int depth) {
		if (depth == blankCnt) {
			flag = true;
			return;
		}
		for (int i = 1; i <= 9; i++) {
			if (!rowCheck[blanks[depth][0]][i] && !colCheck[blanks[depth][1]][i] && !nineCheck[blanks[depth][0] / 3][blanks[depth][1] / 3][i]) {
				rowCheck[blanks[depth][0]][i] = true;
				colCheck[blanks[depth][1]][i] = true;
				nineCheck[blanks[depth][0] / 3][blanks[depth][1] / 3][i] = true;
				map[blanks[depth][0]][blanks[depth][1]] = i;
				combination(depth + 1);
				if(flag) return;
				rowCheck[blanks[depth][0]][i] = false;
				colCheck[blanks[depth][1]][i] = false;
				nineCheck[blanks[depth][0] / 3][blanks[depth][1] / 3][i] = false;
			}
		}
	}

	private static void checkPossible() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				rowCheck[i][map[i][j]] = true;
				colCheck[j][map[i][j]] = true;
				nineCheck[i / 3][j / 3][map[i][j]] = true;
			}
		}
	}
}