import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 메모리:27,796KB, 시간:183ms
 * 집을 기준으로 k범위만큼 색칠 -> 가장 진하게 색칠된 곳이 가장 많은 집을 포함할 지점
 * K값을 1부터 N+1까지 올리면서 최대 집의 수를 구함
 */
public class Solution {
	static int N, M, map[][];
	static int result;
	
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
			result = 0;
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			process();// K를 1부터 늘려가며 K = N까지 늘려가며 최대 이익 찾기
			sb.append('#').append(tc).append(' ').append(result).append('\n');
		}
		System.out.println(sb);
	}

	public static void process() {
		for (int K = 1; K <=N+1; K++) {//K범위를 늘려감
			int operationCost = K*K + (K-1)*(K-1);
			for(int i = 0 ; i < N ; i++) {
				for(int j = 0 ; j < N ; j++) {
					int homeCnt = calcHomeCnt(i,j,K);
					int profit = homeCnt*M - operationCost;
					if(profit >= 0 && homeCnt > result) {
						result = homeCnt;
					}
				}
			}
		}
	}
	public static int calcHomeCnt(int x, int y, int K) {
		int cnt = 0;
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < N ; j++) {
				if (map[i][j] == 1 && Math.abs(i - x) + Math.abs(j - y) < K) {
                    cnt++;
                }
			}
		}
		return cnt;
	}
}