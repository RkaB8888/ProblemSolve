import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 메모리 ? KB 시간 ? ms
 * 
 */
public class Main {

	static int N, M, K, treeCnt;
	static int[][] map, a, drdc = {{-1,0},{1,0},{0,-1},{0,1},{1,-1},{1,1},{-1,-1},{-1,1}};
	static PriorityQueue<Tree> pq1 = new PriorityQueue<>((a, b) -> a.age - b.age);
	static PriorityQueue<Tree> pq2 = new PriorityQueue<>((a, b) -> a.age - b.age);
	static Queue<Tree> DieQ = new ArrayDeque<>();

	static class Tree {
		int x, y, age;

		public Tree(int x, int y, int age) {
			this.x = x;
			this.y = y;
			this.age = age;
		}

	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		for(int i = 0 ; i < N ; i++) {
			Arrays.fill(map[i], 5);
		}
		a = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int j = 0; j < N; j++) {
				a[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine().trim());
			pq1.add(new Tree(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())));
			treeCnt++;
		}
		int curYear = 0;
		while(curYear<K) {
			//봄, 여름, 가을, 겨울 사이클
			if(treeCnt==0) break;
			Spring();
			Summer();
			Fall();
			Winter();
			curYear++;
		}
		System.out.println(treeCnt);
	}
	public static void Spring() {
		while(!pq1.isEmpty()) {
			Tree tree = pq1.poll();
			if(map[tree.x][tree.y]<tree.age) {
				DieQ.add(tree);
			}
			else {
				map[tree.x][tree.y]-=tree.age;
				tree.age++;
				pq2.add(tree);
			}
		}
	}
	public static void Summer() {
		while(!DieQ.isEmpty()) {
			Tree tree = DieQ.poll();
			int x = tree.x;
			int y = tree.y;
			int extraEnergy = tree.age/2;
			map[x][y]+=extraEnergy;
			treeCnt--;
		}
	}
	public static void Fall() {
		while(!pq2.isEmpty()) {
			Tree tree = pq2.poll();
			int x = tree.x;
			int y = tree.y;
			boolean pollen = (tree.age%5==0);
			if(pollen) {
				for(int i = 0 ; i < 8 ; i++) {
					 int r = x + drdc[i][0];
					 int c = y + drdc[i][1];
					 if(r<0||c<0||r>=N||c>=N) continue;
					 pq1.add(new Tree(r,c,1));
					 treeCnt++;
				}
			}
			pq1.add(tree);
		}
	}
	public static void Winter() {
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < N ; j++) {
				map[i][j] += a[i][j];
			}
		}
	}
}