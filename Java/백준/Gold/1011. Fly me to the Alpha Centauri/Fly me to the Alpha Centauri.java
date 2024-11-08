import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 세그먼트 트리 메모리 108,680 KB 시간 460 ms
 */
public class Main {
	static double X, Y, len, halfLen;
	static double N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1 ; tc <= T ; tc++) {
			st = new StringTokenizer(br.readLine());
			X = Integer.parseInt(st.nextToken());
			Y = Integer.parseInt(st.nextToken());
			len = Y-X;
			halfLen = len/2;
			N = findN();
//			System.out.println("N : "+halfLen);
//			System.out.println("n : "+N);
			sb.append((int)N).append('\n');
		}

		System.out.print(sb);

	}

	private static double findN() {
		double NP = Math.round((-1+Math.sqrt(-1+8*halfLen))/2);//거리의 절반까지의 합은 1부터 몇까지 SUM 해야 하는지 구하는 것
		double tempN = NP*(1+NP);//1부터 NP까지의 합
//		System.out.println("NP : "+NP+", tempN : "+tempN);
		if(tempN+NP+1 < len ) return NP*2+2;
		else if(tempN < len && len <= tempN+NP+1) return NP*2+1;
		else if(tempN-NP < len && len <= tempN) return NP*2;
		else return NP*2-1;
	}

}