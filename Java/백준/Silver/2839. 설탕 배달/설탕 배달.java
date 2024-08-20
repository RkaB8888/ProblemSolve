import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int weight = Integer.parseInt(br.readLine());
		int quotient = weight / 3;
		int remain = weight % 3;
		int _3kg = 0;
		int _5kg = 0;
		int result = -1;
		if (remain == 0) {
			_5kg += (quotient / 5) * 3;
			_3kg += (quotient % 5);
			result = _5kg + _3kg;
		} else if (remain == 1) {
			if (quotient < 3)
				result = -1;
			else {
				quotient -= 3;
				_5kg += 2;
				_5kg += (quotient / 5) * 3;
				_3kg += (quotient % 5);
				result = _5kg + _3kg;
			}
		} else if (remain == 2) {
			if (quotient < 1)
				result = -1;
			else {
				quotient -= 1;
				_5kg++;
				_5kg += (quotient / 5) * 3;
				_3kg += (quotient % 5);
				result = _5kg + _3kg;
			}
		}
		System.out.println(result);
	}

}