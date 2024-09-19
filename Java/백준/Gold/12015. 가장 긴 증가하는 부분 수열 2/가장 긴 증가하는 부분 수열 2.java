import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 메모리 84804 KB 시간 1028 ms
 * 반으로 쪼개고 이분탐색
 */
public class Main {

	static int N;
	static int arr[];
	static List<Integer> list = new ArrayList<>();

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		list.add(Integer.MIN_VALUE);
		a:for(int i = 0 ; i < N ; i++) {
			if(list.get(list.size()-1)<arr[i]) {
				list.add(arr[i]);
			} else {
				int left = 0, right = list.size();
				while(left<right) {
					int mid = (left+right)>>1;
					int temp = list.get(mid);
					if(temp==arr[i]) continue a;
					else if(temp>arr[i]) right = mid;
					else left = mid+1;
				}
				list.set(left, arr[i]);
			}
		}
		System.out.print(list.size()-1);
	}

}