import java.io.*;
import java.util.*;

/**
 * @description 이 클래스에 대한 동작 설명
 * @performance 메모리: ? KB, 동작시간: ? ms
 * @author python98
 */
public class Main {
	static long result;
	static int N, K;
	static MV[] mv;
	static int[] C;
	
	static class MV {
		int M, V;
		
		MV(int M, int V) {
			this.M = M;
			this.V = V;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		mv = new MV[N];
		for(int i = 0 ; i < N ; i++) {
			st = new StringTokenizer(br.readLine());
			mv[i] = new MV(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
		}
		Arrays.sort(mv,(mv1, mv2) -> {
			if(mv1.M == mv2.M) {
				return mv1.V-mv2.V;
			} else {
				return mv1.M-mv2.M;
			}
		});
		
		C = new int[K];
		for(int i = 0 ; i < K ; i++) {
			C[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(C); // 작은 것을 먼저 담는다.
		
		PriorityQueue<MV> pq = new PriorityQueue<>((mv1,mv2)-> mv2.V-mv1.V);
		int mvLen = 0;
		for(int i = 0 ; i < K ; i++) {
			int curM = C[i];
			while(mvLen < N && mv[mvLen].M <= curM) {
				pq.add(mv[mvLen]);
				mvLen++;
			}
			if(!pq.isEmpty()) {
				result += pq.poll().V;
			}
		}
		
		System.out.print(result);
	}
}