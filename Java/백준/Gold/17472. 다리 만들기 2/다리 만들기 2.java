import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 메모리 18,368KB 시간 184ms
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
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 1) {
					findLand(i, j);
					landNum++;
					
				}
			}
		}
		minLength = new int[landNum][landNum];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] > 1) {
					findLength(i, j);
				}
			}
		}
		Prim();
		System.out.println(len);
	}

	public static void findLength(int row, int col) {
		int r = row, c = col, num = map[row][col];
		int len, numTemp;
		for (int i = 0; i < 4; i++) {
			len = 0;
			while(true) {
				r += dr_dc[i][0]; c+= dr_dc[i][1];
				if (r < 0 || c < 0 || r >= N || c >= M ) {
					break;
				}
				numTemp = map[r][c];
				if(numTemp != 0) {//땅이라면
					if(len<2||numTemp==num) break;//같은 땅이라면,길이가 2보다 작다면
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

	public static void findLand(int row, int col) {
		Queue<int[]> q = new ArrayDeque<>();
		map[row][col] = landNum;
		q.add(new int[] { row, col });
		int temp[],r,c;
		while (!q.isEmpty()) {
			temp = q.poll();
			for (int i = 0; i < 4; i++) {
				r = temp[0] + dr_dc[i][0]; c = temp[1] + dr_dc[i][1];
				if (r < 0 || c < 0 || r >= N || c >= M || map[r][c] != 1)
					continue;
				map[r][c] = landNum;
				q.add(new int[] { r, c });
			}
		}
	}

	public static void Prim() {
		boolean[] isSelect = new boolean[landNum];
		int[] minEdge = new int[landNum];
		Arrays.fill(minEdge, Integer.MAX_VALUE);
		PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a,b)->a[1]-b[1]);
		int[] min = minLength[2];
		int val;
		for(int i = 2 ; i < landNum ; i++) {
			val = min[i];
			if(val>1&&val<minEdge[i]) {
				pq.add(new int[] {i,val});
				minEdge[i] = val;
			}
		}
		isSelect[2] = true;
		int cnt = 3;
		while(!pq.isEmpty()&&cnt<landNum) {
			int[] temp = pq.poll();
			if(isSelect[temp[0]]) continue;
			isSelect[temp[0]] = true;
			len+=temp[1];
			min = minLength[temp[0]];
			for(int i = 2 ; i < landNum ; i++) {
				val = min[i];
				if(val>1&&val<minEdge[i]) {
					pq.add(new int[] {i,val});
					minEdge[i] = val;
				}
			}
			cnt++;
		}
		if(cnt<landNum) len = -1;
	}
}