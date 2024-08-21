/**
 * 메모리:45632kb, 시간:380ms
 */
public class Main {

	static int N, M, R; // 배열의 크기와 명령횟수

	static int[][] map; // 배열 정보

	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		N = readInt();
		M = readInt();
		R = readInt();

		map = new int[N][M];

		// 배열 정보 입력
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = readInt();
			}
		}
		for (int i = 0; i < R; i++) {
			int process = readInt();
			switch (process) {
			case 1:
				process1();
				break;
			case 2:
				process2();
				break;
			case 3:
				process3();
				break;
			case 4:
				process4();
				break;
			case 5:
				process5();
				break;
			case 6:
				process6();
				break;
			}

		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				sb.append(map[i][j]).append(' ');
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}

	public static void process1() {
		int[][] newMap = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				newMap[i][j] = map[N - i - 1][j];
			}
		}
		map = newMap;
	}

	public static void process2() {
		int[][] newMap = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				newMap[i][j] = map[i][M - j - 1];
			}
		}
		map = newMap;
	}

	public static void process3() {
		// 오른쪽 90도 회전
		int[][] newMap = new int[M][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				newMap[j][N - i - 1] = map[i][j];
			}
		}
		map = newMap;
		int temp = N;
		N = M;
		M = temp;
	}

	public static void process4() {

		// 왼쪽으로 90도 회전
		int[][] newMap = new int[M][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				newMap[M - j - 1][i] = map[i][j];
			}
		}
		map = newMap;
		int temp = N;
		N = M;
		M = temp;
	}

	public static void process5() {
		int nhalf = N / 2, mhalf = M / 2;
		// 1번만 옮긴걸로
		int[][] newMap = new int[N][M];
		for (int i = 0; i < nhalf; i++) {// 1번을 2번으로
			for (int j = 0; j < mhalf; j++) {
				newMap[i][j + mhalf] = map[i][j];
			}
		}
		for (int i = 0; i < nhalf; i++) {// 2번을 3번으로
			for (int j = 0; j < mhalf; j++) {
				newMap[i + nhalf][j + mhalf] = map[i][j + mhalf];
			}
		}
		for (int i = 0; i < nhalf; i++) {// 3번을 4번으로
			for (int j = 0; j < mhalf; j++) {
				newMap[i + nhalf][j] = map[i + nhalf][j + mhalf];
			}
		}
		for (int i = 0; i < nhalf; i++) {// 4번을 1번으로
			for (int j = 0; j < mhalf; j++) {
				newMap[i][j] = map[i + nhalf][j];
			}
		}
		map = newMap;
	}

	public static void process6() {
		int nhalf = N / 2, mhalf = M / 2;
		// 6번 연산을 1번 해줌
		int[][] newMap = new int[N][M];
		for (int i = 0; i < nhalf; i++) {// 1번을 4번으로
			for (int j = 0; j < mhalf; j++) {
				newMap[i + nhalf][j] = map[i][j];
			}
		}
		for (int i = 0; i < nhalf; i++) {// 4번을 3번으로
			for (int j = 0; j < mhalf; j++) {
				newMap[i + nhalf][j + mhalf] = map[i + nhalf][j];
			}
		}
		for (int i = 0; i < nhalf; i++) {// 3번을 2번으로
			for (int j = 0; j < mhalf; j++) {
				newMap[i][j + mhalf] = map[i + nhalf][j + mhalf];
			}
		}
		for (int i = 0; i < nhalf; i++) {// 2번을 1번으로
			for (int j = 0; j < mhalf; j++) {
				newMap[i][j] = map[i][j + mhalf];
			}
		}
		map = newMap;
	}

	public static int readInt() throws Exception {
		int val = 0;
		int c = System.in.read();
		while (c <= ' ') {
			c = System.in.read();
		}
		do {
			val = val * 10 + c - 48;
		} while ((c = System.in.read()) >= 48 && c <= 57);
		return val;
	}
	

}