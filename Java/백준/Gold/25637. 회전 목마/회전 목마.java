import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 그리디 메모리 ? KB 시간 ? ms
 */
public class Main {
	static int N, result = Integer.MAX_VALUE;
	static int[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		arr = new int[2*N];
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
			arr[i] = num;
			arr[N+i] = num;
		}
		for(int i = 0 ; i < N ; i++) {
			int fillIdx = 0, moveCnt = 0;
			for(int j = 0 ; j < N ; j++) {
				if(arr[i+j]!=0) {
					int pNum = arr[i+j];
					while(pNum>0) {
						moveCnt+=Math.abs(fillIdx-j); 
						// fillIdx를 0~N까지 하나씩 채움
						//채우고자 하는 칸(fillIdx)과 j사이의 거리가 이동 거리
						fillIdx++;
						pNum--;
					}
				}
			}
			result = Math.min(result, moveCnt);
		}
		System.out.print(result);
	}
}