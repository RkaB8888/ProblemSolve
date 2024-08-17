import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int len = Integer.parseInt(br.readLine());
		String[] s = br.readLine().split(" ");
		int[] numbers = new int[len];
		for (int i = 0; i < len; i++) {
			numbers[i] = Integer.parseInt(s[i]);
		}
		int result = 1, cnt = 1;
		for (int i = 1; i < len; i++) {
			if (numbers[i - 1] <= numbers[i]) {
				cnt++;
				if (cnt > result)
					result = cnt;
			} else
				cnt = 1;
		}
		cnt = 1;
		for (int i = 1; i < len; i++) {
			if (numbers[i - 1] >= numbers[i]) {
				cnt++;
				if (cnt > result)
					result = cnt;
			} else
				cnt = 1;
		}
		System.out.println(result);
	}
}