import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 메모리 302,532 KB 시간 1,384 ms
 * PQ 2개를 이용한 구현
 */
public class Main {

	static int N, M, K, treeCnt;
	static int[][] map, a, drdc = {{-1,0},{1,0},{0,-1},{0,1},{1,-1},{1,1},{-1,-1},{-1,1}};
	static PriorityQueue<Tree> trees = new PriorityQueue<>(Comparator.comparingInt(tree -> tree.age));
	static Queue<Tree> DieQ = new ArrayDeque<>();
	static Queue<Tree> temp = new ArrayDeque<>();

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
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		for(int i = 0 ; i < N ; i++) {
			Arrays.fill(map[i], 5);
		}
		a = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				a[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine().trim());
			trees.add(new Tree(Integer.parseInt(st.nextToken())-1, 
					Integer.parseInt(st.nextToken())-1, 
					Integer.parseInt(st.nextToken())));
			treeCnt++;
		}
		int curYear = 0;
		while(curYear<K) {
			//봄, 여름, 가을, 겨울 사이클
			if(treeCnt==0) break;
			SpringAndSummer();
			Fall();
			Winter();
			curYear++;
		}
		System.out.println(treeCnt);
	}
	public static void SpringAndSummer() {
		while(!trees.isEmpty()) {
			Tree tree = trees.poll();
			if(map[tree.x][tree.y]<tree.age) {
				DieQ.add(tree);
			}
			else {
				map[tree.x][tree.y]-=tree.age;
				tree.age++;
				temp.add(tree);
			}
		}
		while(!DieQ.isEmpty()) {
			Tree deadTree = DieQ.poll();
			map[deadTree.x][deadTree.y]+=deadTree.age/2;
			treeCnt--;
		}
		trees.addAll(temp);
		temp.clear();
	}

	public static void Fall() {
		while(!trees.isEmpty()) {
			Tree tree = trees.poll();
			if(tree.age%5==0) {
				for(int i = 0 ; i < 8 ; i++) {
					 int r = tree.x + drdc[i][0];
					 int c = tree.y + drdc[i][1];
					 if(r<0||c<0||r>=N||c>=N) continue;
					 temp.add(new Tree(r,c,1));
					 treeCnt++;
				}
			}
			temp.add(tree);
		}
		trees.addAll(temp);
		temp.clear();
	}
	public static void Winter() {
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < N ; j++) {
				map[i][j] += a[i][j];
			}
		}
	}
}