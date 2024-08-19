import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * 메모리:29,712KB, 시간:1,733ms
 * 재귀를 통한 조합 생성을 2번 함
 * 모든 재료에 대해 A와 B가 나눠가지는 조합 1번
 * 나눠가진 재료에 대해서 궁합을 계산하기 위해 2개씩 고르는 조합 2번
 * DP가 없을 때 시간 초과가 걸렸고, DP를 넣고 돌리니 통과함
 */
public class Solution {
	static int N;// 전체 식재료 개수
	static int H;// 한 사람이 가져갈 식재료 개수 (N/2)
	static int[][] table;// 궁합 테이블
	static boolean[] isSelect;// 어떤게 A가 사용하는지 판단
	static int result;// 차이의 최소를 저장

	static int[] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine());
			H = N / 2;
			table = new int[N][N];
			isSelect = new boolean[N];
			result = Integer.MAX_VALUE;
			dp = new int[1 << N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					table[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			///////////////////// 입력 처리 끝//////////////////////////////////////
			combi1(0, 0, 0);
			sb.append('#').append(test_case).append(' ').append(result).append('\n');
		} // 테스트 끝
		System.out.println(sb);
	}

	// N개의 재료들 중에서 N/2개의 재료에 체크 표시(A가 사용하는 재료)
	public static void combi1(int cnt, int str, int bitmask) {
		if (cnt == H) {// N/2개 선택했다면 나눠진 재료를 토대로 점수 계산
			if (dp[bitmask] == 0) {
				int diff = calcDiff();
				if (result > diff)
					result = diff;
				dp[bitmask] = diff;
				bitmask^=((1<<N)-1);//비트 마스크의 N개 비트를 모두 반전 시킴 (같은 재료들을 B가 선택해도 동일한 결과라서)
				dp[bitmask] = diff;
			}
			return;
		}
		for (int i = str; i < N; i++) {
			isSelect[i] = true;
			combi1(cnt + 1, i + 1, bitmask | (1 << i));
			isSelect[i] = false;
			combi1(cnt, i + 1, bitmask);
		}
	}

	// 사용하기로 한 재료들 중에서 2개를 선택해서 궁합 점수를 산출한다.
	// list는 선택된 2가지 재료를 담는 배열, aCheck가 true라면 A재료를 계산하는 중
	public static int calcDiff() {
		int a = 0; 
		int b = 0;
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < i ; j++) {
				if(isSelect[i]==isSelect[j]) {
					if(isSelect[i]) {
						a+=table[i][j]+table[j][i];
					}
					else {
						b+=table[i][j]+table[j][i];
					}
				}
			}
		}
		return Math.abs(a-b);
	}

}