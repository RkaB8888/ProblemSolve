import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 메모리 ? KB 시간 ? ms 1.보석을 무게 오름차순으로 정렬 2.가방을 무게 오름차순으로 정렬 3.PQ 생성 (가치를 내림차순으로
 * 정렬) 4.가장 작은 가방의 무게보다 보석의 무게가 더 작으면 PQ에 넣음 5.가방의 무게보다 보석의 무게가 더 크면 이전까지 PQ에
 * 담았던 보석 중에서 가장 큰 가치를 저장 6. 다음 가방으로 4번부터 반복
 * 
 * @author python98
 */
public class Main {
	static int N, K;
	static long result;
	static int[][] jewel;
	static int[] bag;
	static PriorityQueue<Integer> pq;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		jewel = new int[N][2];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			jewel[i][0] = Integer.parseInt(st.nextToken());
			jewel[i][1] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(jewel, (a, b) -> a[0] - b[0]);

		bag = new int[K];
		for (int i = 0; i < K; i++) {
			bag[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(bag);

		pq = new PriorityQueue<>((a, b) -> b-a);

		int j = 0;
        for (int i = 0; i < K; i++) {
            while (j < N && jewel[j][0] <= bag[i]) {
                pq.offer(jewel[j][1]);
                j++;
            }

            if (!pq.isEmpty()) {
                result += pq.poll();
            }
        }

		System.out.print(result);
	}
}