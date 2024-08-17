
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		int r = Integer.parseInt(s[0]), c = Integer.parseInt(s[1]);
		int goal = Integer.parseInt(br.readLine());
		if (goal > r * c) {
			System.out.println(0);
			return;
		}
		int num = 0, row = 1, col = 0;
		while (num < goal) {
			num += c;
			col += c;
			r--;
			if (num >= goal) {
				col -= num - goal;
				break;
			}
			num += r;
			row += r;
			c--;
			if (num >= goal) {
				row -= num - goal;
				break;
			}
			num += c;
			col -= c;
			r--;
			if (num >= goal) {
				col += num - goal;
				break;
			}
			num += r;
			row -= r;
			c--;
			if (num >= goal) {
				row += num - goal;
				break;
			}
		}
		System.out.println(row + " " + col);
	}
}