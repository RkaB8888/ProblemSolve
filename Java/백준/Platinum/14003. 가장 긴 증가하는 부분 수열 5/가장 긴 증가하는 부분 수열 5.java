import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 메모리 182,284 KB 시간 648 ms 그리디 이분탐색
 * 
 * @author python98
 */
public class Main {
	static final int inf = Integer.MAX_VALUE;
	static int N;
	static int[] A, result, index;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		A = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 입력 배열 초기화
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		// LIS를 저장하기 위한 배열과 인덱스 추적 배열 초기화
		result = new int[N];
		index = new int[N];
		
		// 첫 번째 값 설정
		result[0] = A[0];
		index[0] = 0; // A[0]은 temp[0]에 저장됨
		int resultCnt = 1;
		
		// LIS 계산 및 인덱스 추적
		for (int i = 1; i < N; i++) {
			if (result[resultCnt-1] < A[i]) {
				result[resultCnt] = A[i];
				index[i] = resultCnt++;
			} else {
				int idx = lowerBound(result, 0, resultCnt, A[i]);
				result[idx] = A[i];
				index[i] = idx;
			}
		}
		
		// 결과 수열 길이 추가
		sb.append(resultCnt).append('\n');
		
		// 최장 증가 부분 수열을 추적하여 역순으로 저장
		result = new int[resultCnt--];
		for(int i = N-1 ; i >= 0 ; i--) {
			if(index[i]==resultCnt) {
				result[resultCnt--] = A[i];
			}
		}
		
		// 결과 출력
		for(int num : result) {
			sb.append(num).append(' ');
		}
		System.out.print(sb);
	}

	// 이분 탐색을 사용해 lower bound 찾기
	private static int lowerBound(int[] arr, int str, int end, int val) {
		while (str < end) {
			int mid = (str + end) >> 1;
			if (arr[mid] < val) {// lower bound
				str = mid + 1;
			} else {
				end = mid;
			}
		}
		return str;
	}
}