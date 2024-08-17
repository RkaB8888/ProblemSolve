import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	public static final double denum = 0.61803;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int num1 = Integer.parseInt(br.readLine());
		int num2 = (int) (num1 * denum+1);
		List<Integer> list1 = process(num1, num2);
		System.out.println(list1.size());
		for (int num : list1) {
		    System.out.print(num + " ");
		}
	}

	public static List<Integer> process(int num1, int num2) {
		List<Integer> list = new ArrayList<>();
		list.add(num1);
		list.add(num2);
		int temp = num1 - num2;
		while (temp >= 0) {
			list.add(temp);
			num1 = num2;
			num2 = temp;
			temp = num1 - num2;
		}
		return list;
	}
}