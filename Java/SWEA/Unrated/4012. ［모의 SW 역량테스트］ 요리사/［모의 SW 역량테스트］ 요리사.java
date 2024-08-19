/**
 * 메모리:25,356KB, 시간:159ms
 * 재귀를 통한 조합 생성을 1번 함
 * -> 모든 재료에 대해 A와 B가 나눠가지는 조합
 * 나눠가진 재료에 대해서 궁합을 계산하기 위해 2중 for문 사용
 * DP를 통해 A와 B가 서로 반대로 고른 경우를 제외함
 */
public class Solution {
	static int N;// 전체 식재료 개수
	static int H;// 한 사람이 가져갈 식재료 개수 (N/2)
	static int[][] table;// 궁합 테이블
	static boolean[] isSelect;// 어떤게 A가 사용하는지 판단
	static int result;// 차이의 최소를 저장

	static int[] dp;

	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		int T = readInt();
		for (int test_case = 1; test_case <= T; test_case++) {
			N = readInt();
			H = N / 2;
			table = new int[N][N];
			isSelect = new boolean[N];
			result = Integer.MAX_VALUE;
			dp = new int[1 << N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					table[i][j] = readInt();
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
		if (cnt == H) {// N/2개 선택했다면 나눠진 재료를 토대로 점수 차이 계산
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
		}
	}

	// 사용하기로 한 재료들 중에서 2개를 선택해서 궁합 점수를 산출한다.
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
	public static int readInt() throws Exception {
	    int val = 0;
	    int c = System.in.read();
	    while (c <= ' ') {  // 공백 문자 건너뛰기
	        c = System.in.read();
	    }
	    do {
	        val = 10 * val + c - 48;  // 아스키 값으로 숫자 변환
	    } while ((c = System.in.read()) >= 48 && c <= 57);

	    return val;  // 양수 반환
	}

}