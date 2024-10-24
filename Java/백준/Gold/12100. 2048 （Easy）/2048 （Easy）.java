import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 메모리: ?KB, 시간: ?ms
 */
public class Main {
	static int N, map[][], result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		int maxNum = 0;
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int num = Integer.parseInt(st.nextToken());
				map[i][j] = num;
				maxNum = Math.max(maxNum, num);
			}
		}

		dfs(32, maxNum, map);

		// 결과 출력
//		if(result>2048) result = 2048;
		System.out.print(result);
	}

	private static void dfs(int prod, int maxNum, int[][] curMap) {
		if (result >= maxNum * prod)
			return;
		if (prod == 1) {
			result = Math.max(result, maxNum);
//			for(int i = 0 ; i < N ; i++) {
//				System.out.println(Arrays.toString(curMap[i]));
//			}
			return;
		}
		int[][] rotatedMap;
		for (int i = 0; i < 4; i++) {
			rotatedMap = rotate(i, curMap);
			left(rotatedMap);
			dfs(prod / 2, checkMaxNum(rotatedMap), rotatedMap);
		}
	}

	private static int checkMaxNum(int[][] map) {
		int maxNum = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				maxNum = Math.max(maxNum, map[i][j]);
			}
		}
		return maxNum;
	}

	private static void left(int[][] map) {
		for (int i = 0; i < N; i++) {
			int row[] = map[i];
			int recentNum = 0, lastIdx = -1;
			for (int j = 0; j < N; j++) {
				if (row[j] != 0) { // 떨어질 값이 있다면
					if (recentNum == row[j]) { // 합쳐질 값이 있다면
						row[lastIdx] *= 2;
						row[j] = 0;
						recentNum = 0;
					} else { // 합쳐질 값이 없다면
						recentNum = row[j]; // 마지막 숫자 갱신
						lastIdx++;
						if (lastIdx != j) { // 다르다면 해당 인덱스로 숫자 옮기고 0으로 바꿔줌
							row[lastIdx] = row[j];
							row[j] = 0;
						}
					}
				}
			}
		}
	}

	private static int[][] rotate(int curl, int[][] map) {
		int[][] rotatedMap = new int[N][N];
		if (curl == 1) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					rotatedMap[i][j] = map[N - j - 1][i]; // 90도 회전
				}
			}
		} else if (curl == 2) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					rotatedMap[i][j] = map[N - i - 1][N - j - 1]; // 180도 회전
				}
			}

		} else if (curl == 3) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					rotatedMap[i][j] = map[j][N - i - 1]; // 270도 회전
				}
			}

		} else {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					rotatedMap[i][j] = map[i][j]; // 원본 그대로
				}
			}

		}
		return rotatedMap;
	}

}