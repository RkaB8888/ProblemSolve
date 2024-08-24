import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 메모리:18,908KB, 시간:109ms
 * 
 * @author SSAFY
 *
 */
public class Solution {
	static int H; // 높이
	static int W; // 너비
	static char[][] map;// 맵 정보
	static int N; // 명령어 길이
	static String input; // 명령어

	static class player {
		int i;
		int j;
		int way;
		int[][] dr_dc = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

		public player() {
			super();
		}

		public void Command(char c) {
			if (c == 'S') {
				this.Shoot();
			} else {
				this.Move(c);
			}
		}

		public void Shoot() {
			int r = i + dr_dc[way][0];
			int c = j + dr_dc[way][1];
			while (r >= 0 && r < H && c >= 0 && c < W && map[r][c] != '#') {
				if (map[r][c] == '*') { // 그냥 벽을 만나면
					map[r][c] = '.';
					break;
				}
				r += dr_dc[way][0];
				c += dr_dc[way][1];
			}
		}

		public void Move(char head) {
			if (head == 'U') {
				map[i][j] = '^';
				this.way = 0;
				moveCheck(this.way);
			} else if (head == 'R') {
				map[i][j] = '>';
				this.way = 1;
				moveCheck(this.way);
			} else if (head == 'D') {
				map[i][j] = 'v';
				this.way = 2;
				moveCheck(this.way);
			} else if (head == 'L') {
				map[i][j] = '<';
				this.way = 3;
				moveCheck(this.way);
			}
		}
		public void moveCheck(int way) {
			int r = this.i+dr_dc[way][0];
			int c = this.j+dr_dc[way][1];
			if (r >= 0 && r < H && c >= 0 && c < W && map[r][c] == '.') {
				map[r][c] ^= map[i][j];
				map[i][j] ^= map[r][c];
				map[r][c] ^= map[i][j];
				this.i = r;
				this.j = c;
			}
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			map = new char[H][];
			for (int i = 0; i < H; i++) {
				map[i] = br.readLine().toCharArray();
			}
			N = Integer.parseInt(br.readLine());
			input = br.readLine();
			// 입력 끝
			// 플레이어 위치 찾기
			player p = new player();
			a: for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					if (map[i][j] == '^') {
						p.i = i;
						p.j = j;
						p.way = 0;
						break a;
					} else if (map[i][j] == 'v') {
						p.i = i;
						p.j = j;
						p.way = 2;
						break a;
					} else if (map[i][j] == '<') {
						p.i = i;
						p.j = j;
						p.way = 3;
						break a;
					} else if (map[i][j] == '>') {
						p.i = i;
						p.j = j;
						p.way = 1;
						break a;
					}
				}
			}
			// 명령어 시작
			for (int i = 0; i < N; i++) {
				p.Command(input.charAt(i));
			}
			sb.append('#').append(tc).append(' ');
			for(int i = 0 ; i < H ; i++) {
				for(int j = 0 ; j < W ; j++) {
					sb.append(map[i][j]);
				}
				sb.append('\n');
			}
		}
		System.out.println(sb);
	}

}