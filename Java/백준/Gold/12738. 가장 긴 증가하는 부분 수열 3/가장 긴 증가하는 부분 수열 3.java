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

	static int N;
	static int arr[];
	static List<Integer> list = new ArrayList<>();

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		list.add(Integer.MIN_VALUE);
		for (int i = 0; i < N; i++) {
			if (arr[i] > list.get(list.size() - 1)) {
				list.add(arr[i]);
			} else {
				int left = 1, right = list.size() - 1;
				while (left < right) {
					int mid = (left + right) >> 1;
					if (list.get(mid) >= arr[i]) {
						right = mid;
					}
					else {
						left = mid+1;
					}
				}
				list.set(right, arr[i]);
			}
		}
		System.out.print(list.size()-1);
	}

}