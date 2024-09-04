import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 메모리:27,796KB, 시간:183ms
 * 집을 기준으로 k범위만큼 색칠 -> 가장 진하게 색칠된 곳이 가장 많은 집을 포함할 지점
 * K값을 1부터 N+1까지 올리면서 최대 집의 수를 구함
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
			process();// K를 1부터 늘려가며 K = N+1까지 늘려가며 최대 이익 찾기
			sb.append('#').append(tc).append(' ').append(result).append('\n');
		}
		System.out.println(sb);
	}

	public static void process() {
		int homeMax = 0;
		for (int K = 1; K <=N+1; K++) {//K범위를 늘려감
			int operate = K*K+(K-1)*(K-1);//색칠한 곳에서 가장 많이 겹치는 곳 = 집의 갯수
			for (int HN = 0; HN < homeCnt; HN++) {
				Home home = homes[HN];
				int x = home.i, y = home.j;
				for(int i = x-K+1 ; i < x+K ; i++) {
					if(i<0||i>=N) continue;
					for(int j = y-K+1 ; j < y+K ; j++) {
						if(j<0||j>=N) continue;
						if(Math.abs(x-i)+Math.abs(y-j)==K-1) {
							map[i][j]++;
							if(map[i][j]>homeMax) homeMax = map[i][j];
						}
					}
				}
			}
			int get = M*homeMax;
			if(get-operate>=0&&result<homeMax) result = homeMax;
			
		}
	}

}