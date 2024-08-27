import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(st.nextToken());// 학생 수
		int K = Integer.parseInt(st.nextToken());// 졸고 있는 학생의 수
		int Q = Integer.parseInt(st.nextToken());// 지환이가 출석 코드를 보낼 학생의 수
		int M = Integer.parseInt(st.nextToken());// 주어질 구간의 수

		int arrLen = N+3;//출석 번호가 N+2까지 되기 때문에
		int[] isSleep = new int[arrLen];// 0 미출석 1 출석 2 잠
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < K; i++) {
			isSleep[Integer.parseInt(st.nextToken())] = 2;
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < Q; i++) {
			int temp = Integer.parseInt(st.nextToken());
			if(isSleep[temp]!=0) continue;
			for(int multi = temp ; multi<arrLen ; multi+=temp) {
				if (isSleep[multi] == 0) {
                    isSleep[multi] = 1;
                }
			}
		}
		int[][] range = new int[M][2];//구간 저장
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			range[i][0] = Integer.parseInt(st.nextToken());
			range[i][1] = Integer.parseInt(st.nextToken());
		}
		
		int[] prefixSum = new int[arrLen];
		int sum = 0;
		for (int i = 3; i < arrLen; i++) {
			if (isSleep[i] != 1)
				sum++;
			prefixSum[i] = sum;
		}
		for (int i = 0; i < M; i++) {
			int s = range[i][0];
			int e = range[i][1];
			sb.append(prefixSum[e] - prefixSum[s-1]).append('\n');
		}
		System.out.println(sb);
	}

}