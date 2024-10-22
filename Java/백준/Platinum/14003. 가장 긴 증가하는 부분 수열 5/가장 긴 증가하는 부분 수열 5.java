import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 메모리 ? KB 시간 ? ms 그리디 이분탐색
 * 
 * @author python98
 */
public class Main {
	static final int inf = Integer.MAX_VALUE;
	static int N;
	static int[] A, index, result;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		A = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		result = new int[N]; // 이분탐색을 통한 수열을 만들기 위한 임시 배열
		index = new int[N]; // temp에 저장되는 인덱스 저장
		Arrays.fill(index, -1); // 인덱스 값이 들어가지 않았음을 표시하기 위해 -1로 채움
		
		result[0] = A[0];
		index[0] = 0; // A[0]은 temp[0]에 저장됨
		int resultCnt = 1;
		for (int i = 0; i < N; i++) {
			if (result[resultCnt-1] < A[i]) {
				result[resultCnt] = A[i];
				index[i] = resultCnt++;
			} else {
				int idx = lowerBound(result, 0, resultCnt, A[i]);
				result[idx] = A[i];
				index[i] = idx;
			}
		}
		sb.append(resultCnt).append('\n'); // 갯수가 나옴
		
		result = new int[resultCnt--];
		for(int i = N-1 ; i >= 0 ; i--) {
			if(index[i]==resultCnt) {
				result[resultCnt--] = A[i];
			}
		}
		for(int num : result) {
			sb.append(num).append(' ');
		}
		System.out.print(sb);
	}

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