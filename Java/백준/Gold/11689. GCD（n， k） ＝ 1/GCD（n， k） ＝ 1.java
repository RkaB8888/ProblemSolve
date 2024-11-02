import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static long N, result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Long.parseLong(br.readLine());

		if (N == 1) {
			System.out.print(1);
			return;
		}
		result = N;
		for (long i = 2 ; i*i <= N; i++) {
			if (N % i == 0) { //소인수라면
				do { // 해당 소인수를 제거
					N /= i;
				} while (N % i == 0);
				result -= result/i; 
			}
		}
		if(N!=1) { // 해당 소인수가 하나만 존재하는 경우
			result-=result/N;
		}
		System.out.print(result);
	}
}