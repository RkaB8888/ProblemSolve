import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 메모리:18,592KB , 시간:120ms
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
		for (int i = 0; i < N; i++) {
            for (int j = 0, jEnd = N-M; j <= jEnd; j++) {
                for (int i2 = i; i2 < N; i2++) {
                    for (int j2 = (i == i2 ? j + M : 0) ; j2 <= jEnd; j2++) {
                        max = Math.max(max, vals[i][j] + vals[i2][j2]);
                    }
                }
            }
        }
	}
	public static void valSet() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j <= N - M; j++) {
				vals[i][j] = combinate(i,j,0,0,0,0);
			}
		}
	}

	public static int combinate(int r, int c, int cnt, int sum, int val, int maxVal) {
		if(sum>C) return maxVal;
		if(cnt == M) return Math.max(val, maxVal);
		if(c>=N) return maxVal;
		
		int newVal = combinate(r,c+1,cnt+1,sum+map[r][c],val+map[r][c]*map[r][c],Math.max(val, maxVal));
		int skipVal = combinate(r,c+1,cnt+1,sum,val,maxVal);
		return Math.max(newVal, skipVal);
	}
}