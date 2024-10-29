import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 수학 메모리 ? KB 시간 ? ms
 * 
 * @author python98
 */
public class Main {
	static int x1, x2, x3, x4;
	static int y1, y2, y3, y4;
	static Slop base12, base34, oneNthree, oneNfour, twoNthree, twoNfour;

	static class Slop {// 기울기
		long num, denum;

		public Slop(int num, int denum) {
			this.num = num;
			this.denum = denum;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		x1 = Integer.parseInt(st.nextToken());
		y1 = Integer.parseInt(st.nextToken());
		x2 = Integer.parseInt(st.nextToken());
		y2 = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		x3 = Integer.parseInt(st.nextToken());
		y3 = Integer.parseInt(st.nextToken());
		x4 = Integer.parseInt(st.nextToken());
		y4 = Integer.parseInt(st.nextToken());
		System.out.print(check());
	}

	//1. 서로 끝단이 겹치는 경우
	//2. 다른 선분의 한 점까지의 기울기가 동일한 경우
	//3. 
	private static int check() {
		if ((x1 == x3 && y1 == y3) || (x1 == x4 && y1 == y4) || (x2 == x3 && y2 == y3) || (x2 == x4 && y2 == y4)) {
			return 1;
		}
		base12 = new Slop(x2 - x1, y2 - y1);
		base34 = new Slop(x4 - x3, y4 - y3);
		oneNthree = new Slop(x3 - x1, y3 - y1);
		twoNthree = new Slop(x3 - x2, y3 - y2);
		oneNfour = new Slop(x4 - x1, y4 - y1);
		twoNfour = new Slop(x4 - x2, y4 - y2);

		if (isSame(base12, oneNthree)||isSame(base12, twoNthree)) { // 3번 점이 1,2 직선 상에 있다면
			if(inArea(x1,y1,x2,y2,x3,y3)) { // 1,2 범위 안에 있으면 선분 위에 있는 것
				return 1;
			}
		}
		
		if (isSame(base12, oneNfour)||isSame(base12, twoNfour)) { // 4번 점이 1,2 직선 상에 있다면
			if(inArea(x1,y1,x2,y2,x4,y4)) { // 1,2 범위 안에 있으면 선분 위에 있는 것
				return 1;
			}
		}
		
		if (isSame(base34, oneNthree)||isSame(base34, oneNfour)) { // 1번 점이 3,4 직선 상에 있다면
			if(inArea(x3,y3,x4,y4,x1,y1)) { // 3,4 범위 안에 있으면 선분 위에 있는 것
				return 1;
			}
		}
		
		if (isSame(base34, twoNthree)||isSame(base34, twoNfour)) { // 2번 점이 3,4 직선 상에 있다면
			if(inArea(x3,y3,x4,y4,x2,y2)) { // 3,4 범위 안에 있으면 선분 위에 있는 것
				return 1;
			}
		}

		if ((largeThan(base12, oneNthree) ^ largeThan(base12, oneNfour))
				&& (largeThan(base12, twoNfour) ^ largeThan(base12, twoNthree))
				&& (largeThan(base34, oneNthree) ^ largeThan(base34, twoNthree))
				&& (largeThan(base34, oneNfour) ^ largeThan(base34, twoNfour))) {
			return 1;
		}
		return 0;
	}

	// 범위 내에 있는지 확인
	private static boolean inArea(int xa, int ya, int xb, int yb, int xc, int yc) {
		if (xa >= xb) {
			if (ya >= yb) {
				if(xb<=xc&&xc<=xa&&yb<=yc&&yc<=ya) {
					return true;
				}
			} else {
				if(xb<=xc&&xc<=xa&&yb>=yc&&yc>=ya) {
					return true;
				}
			}
		} else {
			if (ya >= yb) {
				if(xb>=xc&&xc>=xa&&yb<=yc&&yc<=ya) {
					return true;
				}
			} else {
				if(xb>=xc&&xc>=xa&&yb>=yc&&yc>=ya) {
					return true;
				}
			}
		}
		return false;
	}

	// 기울기가 같은지 확인
	private static boolean isSame(Slop a, Slop b) {
		return a.num * b.denum == b.num * a.denum;
	}

	// a가 b보다 크면 true
	private static boolean largeThan(Slop a, Slop b) {
		return a.num * b.denum >= b.num * a.denum;
	}
}