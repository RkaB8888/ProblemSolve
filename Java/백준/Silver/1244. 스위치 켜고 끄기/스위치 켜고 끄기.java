
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int light_len = Integer.parseInt(br.readLine());
		boolean light[] = new boolean[light_len];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < light_len; i++) {
			if (Integer.parseInt(st.nextToken()) == 1)
				light[i] = true;
		}

		int pNum = Integer.parseInt(br.readLine());

		for (int i = 0; i < pNum; i++) {
			// 성별 확인하고 처리하는 부분
			st = new StringTokenizer(br.readLine());
			if (st.nextToken().equals("1")) {
				man(light, light_len, Integer.parseInt(st.nextToken()));
			} else {
				woman(light, light_len, Integer.parseInt(st.nextToken()) - 1);
			}
		}

		// 출력하는 부분 20개씩 나눠서 출력함
		System.out.print(light[0] ? 1 : 0);
		System.out.print(" ");
		for (int i = 1; i < light_len; i++) {
			if (i % 20 == 0)
				System.out.println();
			if (light[i]) {
				System.out.print(1);
				System.out.print(" ");
			} else {
				System.out.print(0);
				System.out.print(" ");
			}
		}

	}

	public static void man(boolean[] light, int len, int idx) {
		int m = len / idx;
		for (int i = 1; i <= m; i++) {
			light[(i * idx) - 1] ^= true;
		}
	}

	public static void woman(boolean[] light, int len, int idx) {
		int down = idx - 1, up = idx + 1;
		light[idx] ^= true;
		while (down >= 0 && up < len && light[down] == light[up]) {
			light[down--] ^= true;
			light[up++] ^= true;
		}
	}

}
