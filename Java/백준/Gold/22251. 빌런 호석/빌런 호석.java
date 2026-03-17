import java.io.*;
import java.util.*;

/**
 * @description 이 클래스에 대한 동작 설명
 * @performance 메모리: ? KB, 동작시간: ? ms
 * @author python98
 */

// 0에서 9를 만드는 7비트 배열
// 입력받은 X의 각 자릿수와 0~9 배열과 비트 XOR비교 (최대 7*(K-1)번)
// X의 자릿수 당 0~9까지 반전시킬 때 필요한 횟수 카운팅 (최대 7*(K-1)번)
// ex 3번째 자릿수는 7번 바꿔서 만들 수 있는 숫자 갯수...
// for문 돌면서 N이하라면 +1
public class Main {

	private static final int[] BIT = {
		0b1110111,
		0b0010001, 
		0b0111110,
		0b0111011,
		0b1011001,
		0b1101011,
		0b1101111,
		0b0110001,
		0b1111111,
		0b1111011
	};

	static int N, K, P, X, result;
	static int[] arr, curArr, limit;
	static int[][] cnt;

	private static void dfs(int n, int sum, int num, int digit){
		if(n==K) {
			if(num>=1&&num<=N){
				result++;
			}
			return;
		}
		for(int i = 0 ; i<10 ; i++) { // 10
			if(sum+cnt[n][i]<=P) {
				dfs(n+1,sum+cnt[n][i],num+(i*digit), digit*10);
			}
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());

		curArr = new int[K];
		int temp = X;
		for(int i = 0 ; i < K ; i++) { // K
			curArr[i] = BIT[temp%10];
			temp/=10;
		}

		cnt = new int[K][10];
		for(int i = 0 ; i < K ; i++) { // K * 10
			for(int j = 0 ; j < 10 ; j++) {
				cnt[i][j] = Integer.bitCount(curArr[i]^BIT[j]);
			}
		}

		dfs(0, 0, 0, 1); // 10^K
		System.out.print(result-1);
	}
}