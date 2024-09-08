import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 메모리 76,612KB 시간 297ms
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
		int dir, K;// x,y,이동방향,에너지
		double x, y;

		public Atom(int i, int j, int dir, int k, int sn) {
			super();
			this.x = i;
			this.y = j;
			this.dir = dir;
			K = k;
		}

	}

	// 충돌이 발생했을 때 정보를 큐에 저장하기 위한 클래스
	static class Collision implements Comparable<Collision>{
		int startAtomNum;
		int endAtomNum;
		double collTime;

		public Collision(int startAtomNum, int endAtomNum) {
			this.startAtomNum = startAtomNum;
			this.endAtomNum = endAtomNum;
		}

		@Override
		public int compareTo(Collision o) {
			return Double.compare(this.collTime, o.collTime);
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
			//여기까지가 입력 받는 것
			process();
			sb.append('#').append(tc).append(' ').append(result).append('\n');
		}
		System.out.println(sb);
	}
	//각 원자의 충돌을 우선순위 큐에 담아서 하나씩 충돌 시킨다.
	//이때 충돌 시간이 빠른 것을 먼저 처리하며, 이후에 나온 것에서 이미 충돌로 없어진 경우 그냥 넘어간다
	public static void process() {
		Collision list[] = new Collision[N*(N-1)/2];
		int size = 0;
		for (int i = 0, iEnd = N - 1; i < iEnd; i++) {
			for (int j = i + 1; j < N; j++) {
				Collision c = new Collision(i, j);
				if (collTest(c)) {
					list[size++] = c;
				}
			}
		}
		list = Arrays.copyOf(list, size);
		Arrays.sort(list);
		int cnt = 0;
		while (cnt<size) {
			Collision c = list[cnt];
			cnt++;
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

	// 두 개의 원자가 충돌이 일어날 지 검사하는 부분
	// 충돌이 일어난다면 c에 충돌 시간을 담고 true를 반환한다.
	public static boolean collTest(Collision c) { 
		Atom a = atoms[c.startAtomNum];
		Atom b = atoms[c.endAtomNum];
		int aDirX = dirs[a.dir][0];// a의 x축 이동방향
		int aDirY = dirs[a.dir][1];// a의 y축 이동방향
		int bDirX = dirs[b.dir][0];// b의 x축 이동방향
		int bDirY = dirs[b.dir][1];// b의 y축 이동방향

		if (a.dir == b.dir)//두 원자의 이동방향이 같다면 충돌이 발생하지 않음
			return false;
		//좌표의 벡터와 이동방향의 벡터의 부호가 같다면 -> 거리만 생각했을 때 충돌 가능성을 계산 가능
		// 서로 부호가 다르면 원자가 충돌하기 위해 가야하는 방향과 서로의 이동 방향이 다르다는 의미
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