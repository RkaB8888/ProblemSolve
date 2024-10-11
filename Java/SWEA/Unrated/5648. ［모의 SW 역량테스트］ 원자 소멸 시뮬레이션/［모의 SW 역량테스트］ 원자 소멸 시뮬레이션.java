import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 메모리 76,992KB 시간 236ms
 * 각 원자들의 충돌 예상 시간을 측정하고 PQ에 담음(충돌시간이 빠른 순서)
 * 충돌시간이 빠른 것 부터 충돌시키면서 원자들을 제거함
 * 두 원자 중 하나가 이미 충돌로 사라진 경우, 
 * - 해당 원자가 사라진 시간과 충돌 시간이 동일하면 다자 충돌로 계산함
 * - 해당 원자가 사라진 시간과 충돌 시간이 동일하지 않다면 충돌이 발생하지 않는 것으로 계산
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
			this.x = i;
			this.y = j;
			this.dir = dir;
			K = k;
		}

	}

	// 충돌이 발생했을 때 정보를 큐에 저장하기 위한 클래스
	static class Collision implements Comparable<Collision> {
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
			// 여기까지가 입력 받는 것
			process();
			sb.append('#').append(tc).append(' ').append(result).append('\n');
		}
		System.out.println(sb);
	}

	// 각 원자의 충돌을 우선순위 큐에 담아서 하나씩 충돌 시킨다.
	// 이때 충돌 시간이 빠른 것을 먼저 처리하며, 이후에 나온 것에서 이미 충돌로 없어진 경우 그냥 넘어간다
	public static void process() {
		List<Collision> collisionList = new ArrayList<>();
		for (int i = 0; i < N - 1; i++) {
			for (int j = i + 1; j < N; j++) {
				Collision c = new Collision(i, j);
				if (collTest(c)) {
					collisionList.add(c);
				}
			}
		}
		collisionList.sort(null);
		int cnt = 0, size = collisionList.size();
		while (cnt < size) {
			Collision c = collisionList.get(cnt);
			cnt++;
			int startNum = c.startAtomNum;
			int endNum = c.endAtomNum;
			
			if (is[startNum] && c.collTime != CT[startNum] || is[endNum] && c.collTime != CT[endNum]) {
				// 둘 중에 없는게 있고, 그 충돌 시간과 다른 경우
				continue;
			}
			result += (is[startNum] ? 0 : atoms[startNum].K) + (is[endNum] ? 0 : atoms[endNum].K);
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

		double deltaX = b.x - a.x;
		double deltaY = b.y - a.y;
		int dX = aDirX - bDirX;
		int dY = aDirY - bDirY;

		if (a.dir == b.dir)// 두 원자의 이동방향이 같다면 충돌이 발생하지 않음
			return false;
		// 좌표의 벡터와 이동방향의 벡터의 부호가 같다면 -> 거리만 생각했을 때 충돌 가능성을 계산 가능
		// 서로 부호가 다르면 원자가 충돌하기 위해 가야하는 방향과 서로의 이동 방향이 다르다는 의미
		if ((deltaX<0&&dX<0||deltaX==0&&dX==0||deltaX>0&&dX>0)&& 
				(deltaY<0&&dY<0||deltaY==0&&dY==0||deltaY>0&&dY>0)) {// 가야할 방향이 같은 경우
			if (deltaX == 0) {
				c.collTime = Math.abs(deltaY / 2);
			} else if (deltaY == 0) {
				c.collTime = Math.abs(deltaX / 2);
			} else if (Math.abs(deltaX) == Math.abs(deltaY)) {
				c.collTime = Math.abs(deltaX); // 대각선 충돌
			}
			else {
				return false;
			}
			return true;
		}
		return false;
	}

}