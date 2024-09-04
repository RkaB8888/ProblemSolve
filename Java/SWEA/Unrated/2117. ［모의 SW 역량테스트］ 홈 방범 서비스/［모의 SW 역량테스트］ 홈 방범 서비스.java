import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 메모리:87,668KB, 시간:382ms
 * 
 */
public class Solution {
	static int N, M, map[][];
	static Home[] homes;
	static int homeCnt;
	static int result;
	static class Home {
		int i, j;

		public Home(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			homes = new Home[N * N];
			homeCnt = 0;
			result = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					int num = Integer.parseInt(st.nextToken());
					if (num == 1)
						homes[homeCnt++] = new Home(i, j);
				}
			}
			homes = Arrays.copyOf(homes, homeCnt);
			process();// K를 1부터 늘려가며 K = N까지 늘려가며 최대 이익 찾기
			sb.append('#').append(tc).append(' ').append(result).append('\n');
		}
		System.out.println(sb);
	}

	public static void process() {
		for (int K = 1; K <=N+1; K++) {//K범위를 늘려감
			map = new int[N][N];
			int homeMax = 0;//색칠한 곳에서 가장 많이 겹치는 곳 = 집의 갯수
			for (int HN = 0; HN < homeCnt; HN++) {
				Home home = homes[HN];
				map[home.i][home.j]++;
				if(map[home.i][home.j]>homeMax) homeMax = map[home.i][home.j];
				for (int i = 1; i < K; i++) { // 해당 범위 색칠
					if (home.i + i < N) {
						map[home.i + i][home.j]++;
						if(map[home.i + i][home.j]>homeMax) homeMax = map[home.i + i][home.j];
					}
					if (home.i - i >= 0) {
						map[home.i - i][home.j]++;
						if(map[home.i - i][home.j]>homeMax) homeMax = map[home.i - i][home.j];
					}
					if (home.j + i < N){
						map[home.i][home.j + i]++;
						if(map[home.i][home.j + i]>homeMax) homeMax = map[home.i][home.j + i];
					}
					if (home.j - i >= 0){
						map[home.i][home.j - i]++;
						if(map[home.i][home.j - i]>homeMax) homeMax = map[home.i][home.j - i];
					}
				}
				for (int i = 1; i < K; i++) {
					for (int j = 1; i+j < K; j++) {
						if (home.i + i < N) {
							if (home.j + j < N){
								map[home.i + i][home.j + j]++;
								if(map[home.i + i][home.j + j]>homeMax) homeMax = map[home.i + i][home.j + j];
							}
							if (home.j - j >= 0){
								map[home.i + i][home.j - j]++;
								if(map[home.i + i][home.j - j]>homeMax) homeMax = map[home.i + i][home.j - j];
							}
						}
						if (home.i - i >= 0) {
							if (home.j + j < N){
								map[home.i - i][home.j + j]++;
								if(map[home.i - i][home.j + j]>homeMax) homeMax = map[home.i - i][home.j + j];
							}
							if (home.j - j >= 0){
								map[home.i - i][home.j - j]++;
								if(map[home.i - i][home.j - j]>homeMax) homeMax = map[home.i - i][home.j - j];
							}
						}
					}
				}
			}
			int operate = K*K+(K-1)*(K-1);
			int get = M*homeMax;
			if(get-operate>=0&&result<homeMax) result = homeMax;
			
		}
	}

}