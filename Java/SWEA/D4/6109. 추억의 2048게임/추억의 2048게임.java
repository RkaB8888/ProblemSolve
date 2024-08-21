import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 메모리:29,068KB, 시간:148ms
 * 
 * @author SSAFY
 *
 */
public class Solution {
	static int N;
	static int[][] map;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			String path = st.nextToken();
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			/////////////// 입력 끝///////////////////////////////////////////
			switch (path.charAt(0)) {
			case 'l':
				combine();
				gather();
				break;
			case 'r':
				rotate(3);
				combine();
				gather();
				rotate(3);
				break;
			case 'u':
				rotate(1);
				combine();
				gather();
				rotate(2);
				break;
			case 'd':
				rotate(2);
				combine();
				gather();
				rotate(1);
				break;

			}
			sb.append('#').append(tc).append('\n');
			printMap();
		}
		System.out.println(sb);
	}

	// 왼쪽을 기준으로 하는 메소드를 사용하기 위해 맵을 회전 시킴
	public static void rotate(int way) {// 1:반시계90 2:시계90 3: 시계180
		int newMap[][] = new int[N][N];
		switch (way) {
		case 1://반시계 90
			for(int i = 0 ; i < N ; i++) {
				for(int j = 0 ; j < N ; j++) {
					newMap[i][j] = map[j][N-i-1];
				}
			}
			break;
		case 2://시계 90
			for(int i = 0 ; i < N ; i++) {
				for(int j = 0 ; j < N ; j++) {
					newMap[i][j] = map[N-j-1][i];
				}
			}
			break;
		case 3://180
			for(int i = 0 ; i < N ; i++) {
				for(int j = 0 ; j < N ; j++) {
					newMap[i][j] = map[N-i-1][N-j-1];
				}
			}
			break;
		}
		map = newMap;
	}

	// 순회하며 합칠 부분 합치는 메소드 (왼쪽 기준)
	public static void combine() {
		int[] arr, preNum = new int[2];// 이전의 숫자, j
		for (int i = 0; i < N; i++) {
			arr = map[i]; // 연산을 줄이기 위해 한 줄만 꺼내봄
			preNum[0] = 0;
			for (int j = 0; j < N; j++) {
				if (arr[j] == 0)
					continue;
				if (preNum[0] == arr[j]) {
					arr[j] = 0;
					arr[preNum[1]] *= 2; // 여기가 합치는 부분
					preNum[0] = 0;
				} else {
					preNum[0] = arr[j];
					preNum[1] = j;
				}
			}
		}
	}

	// 왼쪽으로 모으는 메소드
	public static void gather() {
		int arr[], idx;// idx는 왼쪽부터 차곡차곡 넣기 위함
		for (int i = 0; i < N; i++) {
			arr = map[i]; // 연산을 줄이기 위해 한 줄만 꺼내봄
			idx = 0;
			for (int j = 0; j < N; j++) {
				if (arr[j] == 0)
					continue;
				int temp = arr[j];
				arr[j] = 0;
				arr[idx++] = temp;
				
			}
		}
	}
	// 맵을 출력해 줌
	public static void printMap() {
//		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sb.append(map[i][j]).append(' ');
			}
			sb.append('\n');
		}
//		System.out.println(sb);
	}
}