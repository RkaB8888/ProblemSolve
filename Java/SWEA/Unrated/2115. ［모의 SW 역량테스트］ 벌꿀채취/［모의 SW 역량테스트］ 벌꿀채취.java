import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 메모리:27,276KB , 시간:122ms
 * 벌통 M개를 선택한 방법 중 가장 큰 값을 vals에 저장
 * 각 인부의 합을 vals를 통해 출력
 */
public class Solution {
	// 벌통들의 크기 N, 선택할 수 있는 벌통의 개수 M, 꿀을 채취할 수 있는 최대 양 C
	static int N, M, C, map[][], vals[][],max;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			vals = new int[N][N];
			max = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			valSet();
			findMax();
			sb.append('#').append(tc).append(' ').append(max).append('\n');
		}
		System.out.println(sb);
	}
	public static void findMax() {
		for(int i = 0 ; i < N*N ; i++) {
			int iR = i/N, iC = i%N;
			if(iC+M>N) continue;
			for(int j = i+M ; j < N*N ; j++) {
				int jR = j/N, jC = j%N;
				if(jC+M>N) continue;
				max = Math.max(vals[iR][iC]+vals[jR][jC], max);
			}
		}
	}
	public static void valSet() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j <= N - M; j++) {
				combinate(i,j,i,j,0,0,0);
			}
		}
	}

	public static void combinate(int sr, int sc,int r, int c, int cnt, int sum, int val) {
		if(sum>C) return;
		if (cnt == M) {
			if (val > vals[sr][sc])
				vals[sr][sc] = val;
			return;
		}
		int num = map[r][c];
		combinate(sr,sc,r,c+1,cnt+1,sum+num,val+num*num);
		combinate(sr,sc,r,c+1,cnt+1,sum,val);
	}
}