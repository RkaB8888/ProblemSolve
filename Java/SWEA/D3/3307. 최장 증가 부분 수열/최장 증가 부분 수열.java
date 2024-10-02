import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 메모리:?kb, 시간:?ms
 */
public class Solution {
	static int N, arr[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {

			N = Integer.parseInt(br.readLine());
			arr = new int[N];
			st = new StringTokenizer(br.readLine());
			arr[0] = Integer.parseInt(st.nextToken());
			int cnt = 0;
			for (int i = 1; i < N; i++) {
				int nextNum = Integer.parseInt(st.nextToken());
				if (arr[cnt] < nextNum) {
					arr[++cnt] = nextNum;
				} else {
					int leftIdx = 0, rightIdx = cnt;
					while (leftIdx < rightIdx) {
						int mid = (leftIdx + rightIdx) >> 1;
						if(arr[mid]<=nextNum) {
							leftIdx = mid+1;
						} else {
							rightIdx = mid;
						}
					}
					arr[leftIdx] = nextNum;
				}
			}
			sb.append("#").append(tc).append(" ").append(cnt+1).append("\n");
		}
		System.out.println(sb);
	}
}