import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 메모리 302,532 KB 시간 1,384 ms
 * PQ 2개를 이용한 구현
 */
public class Main {

	static int N, M, K, treeCnt;
	static int[][] map, a, drdc = {{-1,0},{1,0},{0,-1},{0,1},{1,-1},{1,1},{-1,-1},{-1,1}};
	static List<Tree> trees1 = new ArrayList<>();
	static List<Tree> trees2 = new ArrayList<>();
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
			trees1.add(new Tree(Integer.parseInt(st.nextToken())-1, 
					Integer.parseInt(st.nextToken())-1, 
					Integer.parseInt(st.nextToken())));
			treeCnt++;
		}
		int curYear = 0;
		while(curYear<K) {
			//봄, 여름, 가을, 겨울 사이클
			if(treeCnt==0) break;
			trees1.sort(Comparator.comparingInt(tree -> tree.age));
			SpringAndSummer();
			Fall();
			//겨울
			for(int i = 0 ; i < N ; i++) {
				for(int j = 0 ; j < N ; j++) {
					map[i][j] += a[i][j];
				}
			}
			curYear++;
		}
		System.out.println(treeCnt);
	}
	public static void SpringAndSummer() {
		for(Tree tree : trees1) {
			if(map[tree.x][tree.y]<tree.age) {
				DieQ.add(tree);
			}
			else {
				map[tree.x][tree.y]-=tree.age;
				tree.age++;
				trees2.add(tree);
			}
		}
		trees1.clear();
		while(!DieQ.isEmpty()) {
			Tree deadTree = DieQ.poll();
			map[deadTree.x][deadTree.y]+=deadTree.age/2;
			treeCnt--;
		}
	}

	public static void Fall() {
		for(Tree tree : trees2) {
			if(tree.age%5==0) {
				for(int i = 0 ; i < 8 ; i++) {
					 int r = tree.x + drdc[i][0];
					 int c = tree.y + drdc[i][1];
					 if(r<0||c<0||r>=N||c>=N) continue;
					 trees1.add(new Tree(r,c,1));
					 treeCnt++;
				}
			}
			trees1.add(tree);
		}
		trees2.clear();
	}

}