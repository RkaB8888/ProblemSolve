import java.io.*;
import java.util.*;

/**
 * @description
 *    - 수학 :<br>
 *    - 최대공약수(A) 이상의 공통 약수는 존재하지 않음<br>
 *    - 즉, resultA와 resultB는 A이상의 수이며, resultA와 resultB를 소인수 분해 했을 때 A를 제외하고 공통되는 것이 없음<br>
 *    - 서로 공통되지 않는 소인수들을 곱하면 최소공배수(B)가 나옴<br>
 *    - ex) A = 6, B = 180
 *    <ul>
 *    <li>resultA와 resultB는 6이상의 수를 가짐. 
 *    <li>180에서 공통된 수 6을 제외(180/6)하고 소인수 분해 시 30 = 2*3*5 -> 2와 3과 5로 구성.
 *    <li>resultA, resultB에 2,3,5로 구성된 수를 넣어주고 합이 최소가 되는 경우를 찾음.
 *    </ul>
 * @performance 메모리: ? KB, 동작시간: ? ms
 * @author python98
 */
public class Main {
	static int A, B, val, resultA, resultB, min = Integer.MAX_VALUE;
	static List<Integer> consistVal;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		if (A == B) {
			sb.append(A).append(" ").append(B);
			System.out.print(sb);
			return;
		}
		B /= A;
		resultA = resultB = A;

		consistVal = new ArrayList<>();
		for (int i = 2; i <= B; i++) {
			val = 1;
			while (B % i == 0) {
				val *= i;
				B /= i;
			}
			if (val != 1) {
				consistVal.add(val);
			}
		}

		findMin(resultA, resultB, 0);
		sb.append(resultA).append(" ").append(resultB);
		System.out.print(sb);
	}

	private static void findMin(int a, int b, int depth) {
		if (a + b >= min)
			return;
		if (depth == consistVal.size()) {
			if (a > b) {
				resultA = b;
				resultB = a;
			} else {
				resultA = a;
				resultB = b;
			}
			min = a + b;
			return;
		}
		int culVal = consistVal.get(depth);
		findMin(a * culVal, b, depth + 1);
		findMin(a, b * culVal, depth + 1);
	}
}