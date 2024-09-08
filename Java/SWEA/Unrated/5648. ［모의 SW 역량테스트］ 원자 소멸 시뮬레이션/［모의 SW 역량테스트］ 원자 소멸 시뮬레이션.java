import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 메모리 ?KB 시간 ?ms
 */
public class Solution {
	static int N, result;
	static Atom[] atoms;
	static boolean[] is;
	static double[] CT;
	static int[][] dirs = { { 0, 1 }, { 0, -1 }, { -1, 0 }, { 1, 0 } };// x y 상하좌우
	// A->B의 방향은 B-A의 단위 벡터를 통해 알 수 있다.
	// A-B의 단위 벡터와 A의 이동 방향과 같고, B-A의 단위 벡터와 B의 이동 방향이 같다면 충돌 가능성이 있다.
	// G의 방향 - E의 방향 == E-G의 단위 벡터와 같다면 abs(E.x-G.x)초 후에 충돌

	static class Atom {
		int dir, K, SN;// x,y,이동방향,에너지,고유넘버
		double x, y;

		public Atom(int i, int j, int dir, int k, int sn) {
			super();
			this.x = i;
			this.y = j;
			this.dir = dir;
			K = k;
			SN = sn;
		}

	}

	// 충돌이 발생했을 때 정보를 큐에 저장하기 위한 클래스
	static class Collision {
		int startAtomNum;
		int endAtomNum;
		double collTime;

		public Collision(int startAtomNum, int endAtomNum) {
			this.startAtomNum = startAtomNum;
			this.endAtomNum = endAtomNum;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			result = 0;
			atoms = new Atom[N];
			is = new boolean[N];
			CT = new double[N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				atoms[i] = new Atom(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
						Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), i);
			}
			process();
			sb.append('#').append(tc).append(' ').append(result).append('\n');
		}
		System.out.println(sb);
	}

	public static void process() {
		PriorityQueue<Collision> pq = new PriorityQueue<>((a, b) -> Double.compare(a.collTime, b.collTime));
		for (int i = 0, iEnd = N - 1; i < iEnd; i++) {
			for (int j = i + 1; j < N; j++) {
				Collision c = new Collision(i, j);
				if (collTest(c)) {
					pq.add(c);
				}
			}
		}
		while (!pq.isEmpty()) {
			Collision c = pq.poll();
			int startNum = c.startAtomNum, endNum = c.endAtomNum;
			if (is[startNum]&&c.collTime!=CT[startNum] || is[endNum]&&c.collTime!=CT[endNum]) {
				//둘 중에 없는게 있고, 그 충돌 시간과 다른 경우
					continue;
			}
			Atom start = atoms[startNum];
			Atom end = atoms[endNum];
			result += (is[startNum]?0:start.K) + (is[endNum]?0:end.K);
			is[startNum] = true;
			is[endNum] = true;
			CT[startNum] = c.collTime;
			CT[endNum] = c.collTime;
		}
	}

	public static boolean collTest(Collision c) {
		Atom a = atoms[c.startAtomNum];
		Atom b = atoms[c.endAtomNum];
		int aDirX = dirs[a.dir][0];// a의 x축 이동방향
		int aDirY = dirs[a.dir][1];// a의 y축 이동방향
		int bDirX = dirs[b.dir][0];// b의 x축 이동방향
		int bDirY = dirs[b.dir][1];// b의 y축 이동방향

		if (a.dir == b.dir)
			return false;
		if (((b.x - a.x)<0&&(aDirX - bDirX)<0||(b.x - a.x)==0&&(aDirX - bDirX)==0||(b.x - a.x)>0&&(aDirX - bDirX)>0)&& 
				((b.y - a.y)<0&&(aDirY - bDirY)<0||(b.y - a.y)==0&&(aDirY - bDirY)==0||(b.y - a.y)>0&&(aDirY - bDirY)>0)) {//가야할 방향이 같은 경우
			if(b.x-a.x==0) {
				c.collTime = Math.abs((b.y - a.y)/2);
			}
			else if(b.y - a.y==0) {
				c.collTime = Math.abs((b.x - a.x)/2);
			}
			else {
				double xR=Math.abs(a.x-b.x);
				double yR=Math.abs(a.y-b.y);
				if(xR!=yR) return false;
				c.collTime = xR;
			}
			return true;
		}
		return false;
	}

}