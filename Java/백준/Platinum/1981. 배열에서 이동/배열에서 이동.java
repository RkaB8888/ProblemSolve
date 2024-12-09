import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * 투포인터, bfs 메모리 ? KB 시간 ? ms
 * 
 * @author python98
 */
public class Main {
	static final int DIR[][] = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	static int N, minH, maxH, result = 200;
	static int[][] map;
	static List<Integer> hList;
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		map = new int[N][N];
		Set<Integer> heights = new HashSet<>();
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int val = Integer.parseInt(st.nextToken());
				map[i][j] = val;
				heights.add(val);
			}
		}

		hList = new ArrayList<>(heights);
		hList.sort(Integer::compareTo);
		
		int lowIdx = 0, highIdx = 0;
		int temp = Math.max(map[0][0],map[N-1][N-1]);
		while (hList.get(highIdx) < temp) highIdx++;
		
		int highLimit = hList.size(), lowLimit = 0;
		
		temp = Math.min(map[0][0],map[N-1][N-1]);
		while (hList.get(lowLimit) <= temp) lowLimit++;
		
		visited = new boolean[N][N];
		while (lowIdx < lowLimit && highIdx < highLimit) {
			minH = hList.get(lowIdx);
			maxH = hList.get(highIdx);
			if (isPosible()) {
				result = Math.min(result, maxH - minH);
				lowIdx++;
			} else {
				highIdx++;
			}
		}
		System.out.print(result);
	}

	private static boolean isPosible() {
		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] {0,0});
		for(int i = 0 ; i < N ; i++) Arrays.fill(visited[i], false);
		visited[0][0] = true;
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int row = cur[0], col = cur[1];
			
			for(int i = 0 ; i < 4 ; i++) {
				int nr = row + DIR[i][0], nc = col + DIR[i][1];
				if(nr<0||nc<0||nr>=N||nc>=N||visited[nr][nc]) continue;
				if(nr==N-1&&nc==N-1) return true;
				visited[nr][nc] = true;
				if(map[nr][nc]<minH||map[nr][nc]>maxH) continue;
				q.add(new int[] {nr,nc});
			}
		}
		return false;
	}
}