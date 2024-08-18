
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	static int M;// 시간
	static int A;// BC개수
	static int result;
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
///////////////////////입력 시작//////////////////////////////////////////
		int testCase = Integer.parseInt(br.readLine());
		for (int t = 1; t <= testCase; t++) {
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			int[] aMove = new int[M];
			int[] bMove = new int[M];
			int[][] BCset = new int[A+1][4];
			for(int i = 0 ; i < 4 ; i++) {
				BCset[0][i] = 0;
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				aMove[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				bMove[i] = Integer.parseInt(st.nextToken());
			}
			for (int i = 1; i <= A; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 4; j++) {
					BCset[i][j] = Integer.parseInt(st.nextToken());
				}
			}
///////////////////////입력 끝//////////////////////////////////////////
///////////////////////맵 설정 시작//////////////////////////////////////////
			Position[][] map = new Position[11][11];
			for (int i = 1; i <= 10; i++) {
				for (int j = 1; j <= 10; j++) {
					map[i][j] = new Position(i, j);
				}
			}
			for (int i = 1; i <= A; i++) {
				int x = BCset[i][0], y = BCset[i][1];
				int range = BCset[i][2];
				int perform = BCset[i][3];
				map[x][y].addBC(perform,i);
				for (int j = 1; j <= range; j++) {
					if (x + j < 11)
						map[x + j][y].addBC(perform,i);
					if (x - j > 0)
						map[x - j][y].addBC(perform,i);
					if (y + j < 11)
						map[x][y + j].addBC(perform,i);
					if (y - j > 0)
						map[x][y - j].addBC(perform,i);
				}
				for (int j = 1; j <= range; j++) {
					for (int k = 1; k <= range; k++) {
						if (j + k <= range) {
							if (x + j < 11) {
								if (y + k < 11)
									map[x + j][y + k].addBC(perform,i);
								if (y - k > 0)
									map[x + j][y - k].addBC(perform,i);
							}
							if (x - j > 0) {
								if (y + k < 11)
									map[x - j][y + k].addBC(perform,i);
								if (y - k > 0)
									map[x - j][y - k].addBC(perform,i);
							}
						}
					}
				}
			}
///////////////////////맵 설정 끝//////////////////////////////////////////
///////////////////////사람 이동 시작//////////////////////////////////////////
			Person a = new Person(1,1);
			Person b = new Person(10,10);
			result = 0;
			Position ap = map[a.i][a.j];
			Position bp = map[b.i][b.j];
			getCharge(ap,bp);
			
			for (int i = 0; i < M; i++) {
				a.move(aMove[i]);
				b.move(bMove[i]);
				
				ap = map[a.i][a.j];
				bp = map[b.i][b.j];
				getCharge(ap,bp);
			}
///////////////////////사람 이동 끝//////////////////////////////////////////
			sb.append('#').append(t).append(' ').append(result).append('\n');
		}
		System.out.println(sb);
	}
	public static void getCharge(Position ap, Position bp) {
		if(ap.BCidx1==0&&bp.BCidx1==0) {
		}
		else if(ap.BCidx1==0) {
			result+=bp.BCP1;
		}
		else if(bp.BCidx1==0) {
			result+=ap.BCP1;
		}
		else {
			if(ap.BCidx1==bp.BCidx1) { // 제일 큰게 같다면
				if(ap.BCidx2==bp.BCidx2) { // 두번째도 같다면
					result += ap.BCP1;
					result += bp.BCP2;
				}
				else { // 두번째는 다르다면
					result+=(ap.BCP2>bp.BCP2)?(ap.BCP2+bp.BCP1):(bp.BCP2+ap.BCP1);
				}
			}
			else { // 제일 큰게 다르다면
				result+= ap.BCP1;
				result+=bp.BCP1;
			}
		}
	}

}

class Position {
	int i;
	int j;
	int BCidx1;// 그 위치의 BC 중 가장 perform이 높은 인덱스
	int BCP1;
	int BCidx2;
	int BCP2;
	List<int[]> BCP = new ArrayList<>();

	public Position(int i, int j) {
		super();
		this.i = i;
		this.j = j;
	}


	public void addBC(int perform, int BCidx) {
		if(BCP1<perform) {
			BCP2 = BCP1;
			BCidx2 = BCidx1;
			BCP1 = perform;
			BCidx1 = BCidx;
		}
		else if(BCP2<perform) {
			BCP2 = perform;
			BCidx2 = BCidx;
		}
	}
}

class Person {
	int i;
	int j;

	public Person(int i, int j) {
		super();
		this.i = i;
		this.j = j;
	}

	public void move(int path) {
		switch (path) {
		case 1://상
			j--;
			break;
		case 2://우
			i++;
			break;
		case 3://하
			j++;
			break;
		case 4://좌
			i--;
			break;

		default:
			break;
		}
	}
}