import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 메모리:?KB, 시간:?ms
 *
 */
public class Main {
	static char[] T, P;
	static int tLen, pLen, preProcess[], cnt;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		T = br.readLine().toCharArray();
		P = br.readLine().toCharArray();
		tLen = T.length;
		pLen = P.length;

//		System.out.println(Arrays.toString(T));
//		System.out.println(Arrays.toString(P));

		preProcess = new int[pLen];
		preProcess[0] = 0;

		// 전처리 과정
		int pi = 0;
		for (int i = 1; i < pLen; i++) {
			if (P[i] == P[pi]) {
				preProcess[i] = ++pi;
			} else {
				if (pi > 0) {
					pi = preProcess[pi - 1];
					i--;
				}
			}
		}

//		System.out.println(Arrays.toString(preProcess));

		// 비교 과정
		for (int i = 0, p = 0; i <= tLen; i++) {
			if (p == pLen) {
				cnt++;
				sb.append(i - pLen + 1).append(' ');
				p = preProcess[pLen - 1];
			}
			if(i==tLen) break;
			if (T[i] != P[p]) {
				if (p > 0) {
					p = preProcess[p - 1];
					i--;
				}
			} else {
				p++;
			}
		}
		System.out.println(cnt);
		System.out.print(sb);
	}

}