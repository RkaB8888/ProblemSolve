import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 메모리 ? KB 시간 ? ms
 * 한번 정렬 후 탐색
 */
public class Main {

	static int N, S;
	static long result;
	static int arr[];
	static List<Integer> leftList = new ArrayList<>();
	static List<Integer> rightList = new ArrayList<>();

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		halfsubset(0, N / 2, 0, leftList);
		halfsubset(N / 2, N, 0, rightList);

		leftList.sort(null);
		rightList.sort(null);

		for (int i = 0; i < leftList.size(); i++) {
			int goal = S - leftList.get(i);
			
			int left = 0, right = rightList.size();
			while (left < right) {//upper
				int mid = (left + right) >> 1;
				if (rightList.get(mid) <= goal) {
					left = mid+1;
				} else {
					right = mid;
				} 
			}
			result+=left;
			left = 0;
			right = rightList.size();
			while (left < right) {//lower
				int mid = (left + right) >> 1;
				if (rightList.get(mid) < goal) {
					left = mid + 1;
				} else {
					right = mid;
				}
			}
			result-=right;
		}
		if(S==0) result--;
		System.out.print(result);
	}

	private static void halfsubset(int str, int end, int sum, List<Integer> list) {
		if (str == end) {
			list.add(sum);
			return;
		}
		halfsubset(str + 1, end, sum + arr[str], list);
		halfsubset(str + 1, end, sum, list);
	}

}