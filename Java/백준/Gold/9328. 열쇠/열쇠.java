import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * FloodFill DFS 메모리 ? KB 시간 ? ms
 * 
 * @author python98
 */
public class Main {
	static final int DIFF = 'a' - 'A';
	static BufferedReader br;
	static StringTokenizer st;
	static int R, C, result, drdc[][] = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	static char map[][];
	static int searchMap[][];
	static List<Door> doors[];
	static boolean usedKey[], getKey[];

	static class Door {
		int r, c;

		public Door(int r, int c) {
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return "(r=" + r + ", c=" + c + ")";
		}

	}

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			input();
			int cnt = 1;
			while (floodFill(0, 0, cnt)) {
				//탐색하면서 키를 먹었다면 해당 문을 열고 재탐색
				for(int i = 0 ; i < 26 ; i++) {
					if(getKey[i]) {
						getKey[i] = false;
						openDoors(i);
						usedKey[i] = true;
					}
				}
				cnt++;
			}
			sb.append(result).append('\n');
		}
		System.out.print(sb);
	}

	// 탐색 도중 새로운 키를 먹으면 true
	private static boolean floodFill(int r, int c, int cnt) {
		boolean flag = false;

		if (map[r][c] == '*'||searchMap[r][c] == cnt)
			return false;
		else if (map[r][c] >= 'A' && map[r][c] <= 'Z') { // 열 수 없는 문
			return false;
		} else if (map[r][c] >= 'a' && map[r][c] <= 'z') {
			if (usedKey[map[r][c] - 'a'] == false) { // 사용해본 적 없는 키
				getKey[map[r][c] - 'a'] = true;
				flag = true;
			} else { // 사용한 적 있는 키
				map[r][c] = '.';
			}
		} else if (map[r][c]=='$') { // 문서 획득
			result++;
			map[r][c] = '.';
		}

		searchMap[r][c] = cnt;
		for (int i = 0; i < 4; i++) {
			int nr = r + drdc[i][0], nc = c + drdc[i][1];
			if (nr < 0 || nr >= R || nc < 0 || nc >= C)
				continue;
			flag = floodFill(nr, nc, cnt) ? true : flag;
		}

		return flag;
	}

	public static void openDoors(int doorCode) {
		for (Door door : doors[doorCode]) {
			map[door.r][door.c] = '.';
		}
	}

	public static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken()) + 2;
		C = Integer.parseInt(st.nextToken()) + 2;
		result = 0;
		map = new char[R][C];
		searchMap = new int[R][C];
		doors = new List[26];
		for (int i = 0; i < 26; i++) {
			doors[i] = new ArrayList<Door>();
		}
		usedKey = new boolean[26];
		getKey = new boolean[26];
		Arrays.fill(map[0], '.');
		Arrays.fill(map[R - 1], '.');
		for (int i = 1, iEnd = R - 2; i <= iEnd; i++) {
			char[] temp = br.readLine().toCharArray();
			map[i][0] = '.';
			map[i][C - 1] = '.';
			System.arraycopy(temp, 0, map[i], 1, temp.length);
			for (int j = 1, jEnd = C - 2; j <= jEnd; j++) {
				if (map[i][j] >= 'A' && map[i][j] <= 'Z') {
					doors[map[i][j] - 'A'].add(new Door(i, j));
				}
			}
		}
		char[] initKey = br.readLine().toCharArray();
		if (initKey[0] == '0')
			return;
		for (char c : initKey) {
			usedKey[c - 'a'] = true;
			openDoors(c - DIFF-'A');
		}
	}

//	static void output() {
//		System.out.println("지도 정보");
//		for (char[] row : map) {
//			for (char c : row) {
//				System.out.print(c);
//			}
//			System.out.println();
//		}
//		System.out.println("문 정보");
//		for (int i = 0; i < 26; i++) {
//			System.out.print((char) (i + 'A') + ":");
//			System.out.println(doors[i].toString());
//		}
//		System.out.println("키 정보");
//		System.out.print("사용된 키 : ");
//		for (int i = 0; i < 26; i++) {
//			if(usedKey[i]) System.out.print((char) (i + 'a') + ", ");
//		}
//		System.out.println();
//		System.out.print("획득한 키 : ");
//		for (int i = 0; i < 26; i++) {
//			if(getKey[i]) System.out.print((char) (i + 'a') + ",");
//		}
//		System.out.println();
//		System.out.println("///////////////");
//	}
}
