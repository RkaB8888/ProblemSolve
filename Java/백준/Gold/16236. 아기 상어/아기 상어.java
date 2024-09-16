import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 메모리 221,812 KB 시간 1,288 ms
 * ArrayList 2개를 이용한 구현
 */
public class Main {

	static int N, sharkX, sharkY, sharkSize = 2,eatCnt,time;
	static int[][] map, drdc = {{-1,0},{1,0},{0,-1},{0,1}};
	static class Fish {
		int x, y, dist, size, SN;

		public Fish(int x, int y, int size,int SN) {
			this.x = x;
			this.y = y;
			this.size = size;
			this.dist = Integer.MAX_VALUE;
			this.SN = SN;
		}

	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int num = Integer.parseInt(st.nextToken());
				map[i][j] = num;
				if(num==0) continue;
				if(num==9) {
					sharkX = i;
					sharkY = j;
				}
			}
		}
		while(nextFood()) {
			if(eatCnt==sharkSize) {
				eatCnt=0;
				sharkSize++;
			}
		}
		
		System.out.println(time);
	}
	public static boolean nextFood() {
		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] {sharkX,sharkY,0});
		boolean visited[][] = new boolean[N][N];
		visited[sharkX][sharkY] = true;
		
		List<int[]> fishList = new ArrayList<>();
		while(!q.isEmpty()) {
			int size = q.size();
			boolean flag = false;
			while(--size>=0) {//거리별 탐색
				int[] cur = q.poll();
				int dist = cur[2]+1;
				for(int i = 0 ; i < 4 ; i++) {
					int r = cur[0]+drdc[i][0];
					int c = cur[1]+drdc[i][1];
					if(r<0||c<0||r>=N||c>=N||visited[r][c]||map[r][c]>sharkSize) continue;
					if(map[r][c]==0||map[r][c]==sharkSize) {//물고기가 아니면 지나감
						q.add(new int[] {r,c,dist});
						visited[r][c]=true;
					}
					else{//가장 가까운 물고기 발견
						flag = true;
						fishList.add(new int[] {r,c,dist});
						visited[r][c]=true;
					}
				}
			}
			if(flag) break;//해당 거리에서 물고기 발견 시
		}
		if(fishList.size()==0) return false;//도달할 수 있는 물고기가 없다.
		fishList.sort((a,b)->{
			if(a[0]==b[0]) return a[1]-b[1];
			return a[0]-b[0];
		});
		int[] food = fishList.get(0);
		map[food[0]][food[1]] = 9;
		map[sharkX][sharkY] = 0;
		sharkX = food[0];
		sharkY = food[1];
		eatCnt++;
		time+=food[2];
		return true;
	}
}