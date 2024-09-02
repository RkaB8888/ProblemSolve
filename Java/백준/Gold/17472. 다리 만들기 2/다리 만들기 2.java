import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 메모리 11,516KB 시간 68ms
 */
public class Main {
	static int N, M, map[][];
	static int landNum = 2;
	static int dr_dc[][] = { {1,0}, {-1,0}, {0,1}, {0,-1} };
	static int minLength[][];
	static int len;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		findLand();
		minLength = new int[landNum][landNum];
		findLength();
		Prim();
		System.out.println(len);
	}

	public static void findLength() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] > 1) {
					lengthConfirm(i, j);
				}
			}
		}
	}

	public static void lengthConfirm(int row, int col) {
		int r = row, c = col, num = map[row][col];
		for (int i = 0; i < 4; i++) {
			int len = 0;
			while(true) {
				r += dr_dc[i][0]; c+= dr_dc[i][1];
				if (r < 0 || c < 0 || r >= N || c >= M ) {
					break;
				}
				int numTemp = map[r][c];
				if(numTemp != 0) {//땅이라면
					if(numTemp==num||len<2) break;//같은 땅이라면,길이가 2보다 작다면
					if(minLength[num][numTemp]==0||minLength[num][numTemp]>len) {
						//측정이 된적 없거나 이전의 측정 길이보다 작다면 갱신
						minLength[num][numTemp]=len;
						minLength[numTemp][num]=len;
					}
					break;
				}
				len++;
			}
		}
	}

	public static void findLand() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 1) {
					landConfirm(i, j);
					landNum++;
				}
			}
		}
	}

	public static void landConfirm(int row, int col) {
		Queue<int[]> q = new ArrayDeque<>();
		map[row][col] = landNum;
		q.add(new int[] { row, col });
		while (!q.isEmpty()) {
			int[] temp = q.poll();
			int x = temp[0], y = temp[1];
			for (int i = 0; i < 4; i++) {
				int r = x + dr_dc[i][0], c = y + dr_dc[i][1];
				if (r < 0 || c < 0 || r >= N || c >= M || map[r][c] != 1)
					continue;
				map[r][c] = landNum;
				q.add(new int[] { r, c });
			}
		}
	}

	public static void Prim() {
		boolean[] isSelect = new boolean[landNum];
		PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a,b)->a[1]-b[1]);
		for(int i = 2 ; i < landNum ; i++) {
			if(minLength[2][i]>1) {
				pq.add(new int[] {i,minLength[2][i]});
			}
		}
		isSelect[2] = true;
		int cnt = 3;
		while(!pq.isEmpty()&&cnt<landNum) {
			int[] temp = pq.poll();
			if(isSelect[temp[0]]) continue;
			isSelect[temp[0]] = true;
			len+=temp[1];
			for(int i = 2 ; i < landNum ; i++) {
				if(minLength[temp[0]][i]>1) {
					pq.add(new int[] {i,minLength[temp[0]][i]});
				}
			}
			cnt++;
		}
		if(cnt<landNum) len = -1;
	}
}