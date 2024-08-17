import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.LinkedList;
import java.util.List;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		List<Integer> list = new LinkedList<>();
		int len = Integer.parseInt(br.readLine());
		String[] s = br.readLine().split(" ");
		for (int i = 0; i < len; i++) {
			int num = Integer.parseInt(s[i]);
			list.add(i - num, i + 1);
		}
		for (int i : list) {
			System.out.print(i + " ");
		}
	}
}
