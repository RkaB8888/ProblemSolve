import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * 
 * @author python98
 * ? KB ? ms
 * 오른쪽에 붙이는 경우와 왼쪽에 붙이는 경우를 나눠서 탐색 (그리디)
 *
 */
public class Main {
	static int N, arr[], dp[][];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		arr = new int[N];
		for(int i = 0 ; i < N ; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		dp = new int[N][N]; // dp[i][j]: i부터 j까지의 부분 배열을 팰린드롬으로 만들기 위한 최소 추가 횟수
		System.out.print(minAdd(0, N-1));
	}
	static int minAdd(int left, int right) {
        if (dp[left][right] != 0) {
            return dp[left][right];
        }

        // 팰린드롬이면 0 반환
        if (left >= right) {
            return 0;
        }

        // 왼쪽과 오른쪽이 다르면 1 추가하고 재귀 호출
        if (arr[left] != arr[right]) {
            dp[left][right] = 1 + Math.min(minAdd(left + 1, right), minAdd(left, right - 1));
        } else {
            dp[left][right] = minAdd(left + 1, right - 1);
        }

        return dp[left][right];
    }
}