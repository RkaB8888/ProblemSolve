import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 메모리 ? KB 시간 ? ms
 * @author python98
 */
public class Main {
	
	static final int inf = Integer.MAX_VALUE;
	static int N, A[], M, B[][], result;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		A = new int[N+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 1 ; i <= N ; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		M = Integer.parseInt(br.readLine());
		B = new int[M+1][];
		for(int i = 1 ; i <= M ; i++) {
			st = new StringTokenizer(br.readLine());
			B[i] = new int[] {Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())};
		}
		bfs();
//		System.out.println(Arrays.toString(A));
		System.out.print(result);
	}

	private static void bfs() {
		Map<Long, Integer> map = new HashMap<>();
		long hashCode = getHash(A);
		map.put(hashCode, A[0]);
		Queue<int[]> q = new ArrayDeque<int[]>();
		q.add(A);
		
		result = inf;
		while(!q.isEmpty()) {
			int[] curArr = q.poll();
			if(curArr[0]>=result) continue;
			if(check(curArr)) {
				A = curArr;
				result = curArr[0];
				continue;
			}
			for(int i = 1 ; i <= M ; i++) {
				int[] newA = new int[N+1];
				System.arraycopy(curArr, 0, newA, 0, N+1);
				swap(B[i][0],B[i][1],newA);
				newA[0]+=B[i][2];
				hashCode = getHash(newA);
				if(!map.containsKey(hashCode)||map.get(hashCode)>newA[0]) {
					map.put(hashCode, newA[0]);
					q.add(newA);
				}
			}
		}
		if(result==inf) result = -1;
	}
	private static void swap(int a, int b, int[] arr) {
		int temp = arr[a];
		arr[a]=arr[b];
		arr[b]=temp;
	}
	private static long getHash(int[] arr) {
		long result = 0;
		for(int i = 1 ; i <= N ; i++) {
			result*=10;
			result+=arr[i]-1;
		}
		return result;
	}
	private static boolean check(int[] arr) {
		for(int i = 1 ; i < N ; i++) {
			if(arr[i]>arr[i+1]) return false;
		}
		return true;
	}
}