import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		List<Integer> arr = new ArrayList<>();
		int sum = 0;
		for (int i = 0; i < 9; i++) {
			int temp = Integer.parseInt(br.readLine());
			arr.add(temp);
			sum += temp;
		}
		List<Integer> result = dfs(arr, 9, sum);
		Collections.sort(result);
		for (int i = 0; i < result.size(); i++) {
			System.out.println(result.get(i));
		}
	}

	public static List<Integer> dfs(List<Integer> arr, int len, int sum) {
		List<Integer> result = new ArrayList<>();
		A: for (int i = 0; i < len - 1; i++) {
			for (int j = i + 1; j < len ; j++) {
				if (sum - arr.get(i) - arr.get(j) == 100) {
					for (int l = 0; l < len; l++) {
						if (l == i || l == j)
							continue;
						result.add(arr.get(l));
					}
					break A;
				}
			}
		}
		return result;
	}
}
