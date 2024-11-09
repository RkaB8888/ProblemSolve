import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * PQ 윈도우슬라이딩 53,376 KB 시간 684 ms
 */
public class Main {
	static class Pair implements Comparable<Pair> {
		int h, o, dist;

		public Pair(int h, int o) {
			if (h < o) {
				this.h = h;
				this.o = o;
			} else {
				this.h = o;
				this.o = h;
			}
			this.dist = o - h;
		}

		@Override
		public int compareTo(Pair other) {
			return this.o - other.o;
		}
	}

	static int N, D, result;
	static List<Pair> list;
	static PriorityQueue<Integer> pq;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		list = new ArrayList<Pair>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			list.add(new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		D = Integer.parseInt(br.readLine());
		list.removeIf((a) -> a.dist > D);
		list.sort(null);
		result = 0;
		pq = new PriorityQueue<Integer>();
		for (int i = 0 ; i < list.size() ; i++) {
			Pair startP = list.get(i);
			int dE = startP.o, dS=dE-D;
			pq.add(startP.h);
			
			while(!pq.isEmpty()) {
				if(pq.peek()<dS) {
					pq.poll();
				} else {
					break;
				}
			}
			result = Math.max(result, pq.size());
		}

		System.out.print(result);
	}

}