import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 메모리:33,848KB, 시간:148ms
 */
public class Main {
	static int N;
	static int M;
	static int D;
	static int[][] map;
	static int maxKill;
	static int totalEnemy;

	static int[][] copyMap;//궁수 위치에 따른 시뮬을 위한 카피맵
	static int enemyCnt;//카피맵의 남은 적의 수
	static int enemyKill;//카피맵에서 죽인 적의 수
	static int[] emptyRow;//맵을 한칸 내릴 때 0번 행에 넣어줗 빈 행
	static int[] archerCol;//궁수들의 열 위치
	static int archerRow;//고정된 궁수들의 행 위치
	static Archer[] archer3;//궁수 3마리

	static class Archer {
		int targetR;
		int targetC;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		map = new int[N][M + 1]; // M에는 해당 열의 적의 수 저장, 웨이브 마다 남은 적 카운팅을 위함
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				int temp = Integer.parseInt(st.nextToken());
				if (temp == 1) {
					totalEnemy++;
					map[i][M]++;
				}
				map[i][j] = temp;
			}
		}

		emptyRow = new int[M + 1];
		archerCol = new int[M];
		for (int j = M - 3; j < M; j++) {
			archerCol[j] = 1;
		}
		archerRow = N;
		archer3 = new Archer[3];
		for (int i = 0; i < 3; i++) {
			archer3[i] = new Archer();
		}
		simulation();
		System.out.println(maxKill);
	}

	public static void simulation() {
		// 궁수의 위치를 바꿔줌
		do {
			mapCopy(); // 다음 궁수 위치에 따른 새로운 맵
			enemyCnt = totalEnemy; // 남아있는 적 카운팅
			enemyKill = 0; // 킬 초기화
			while (enemyCnt > 0) { // 적이 남아있다면
				attack(); // 궁수들 공격 타이밍
				nextWave(); // 적 이동 타이밍
			}
			if(enemyKill>maxKill) maxKill = enemyKill;
		} while (nextPermutation());

	}
	//궁수 위치 넥퍼
	public static boolean nextPermutation() {
		int i = M - 1;
		while (i > 0 && archerCol[i - 1] >= archerCol[i]) {
			i--;
		}
		if (i == 0)
			return false;
		int j = M - 1;
		while (archerCol[i - 1] >= archerCol[j]) {
			j--;
		}
		swap(i - 1, j);
		j = M-1;
		while (i < j) {
			swap(i, j);
			j--;
			i++;
		}
		return true;
	}
	// 두 값을 바꿈
	public static void swap(int a, int b) {
		archerCol[a]^=archerCol[b];
		archerCol[b]^=archerCol[a];
		archerCol[a]^=archerCol[b];
	}

	// 궁수 공격 타이밍
	public static void attack() {
		if (archerTargeting()) {// 타겟 설정 됐다면
			for (int i = 0; i < 3; i++) { // 각 궁수에 대해
				Archer ac = archer3[i];
				if (copyMap[ac.targetR][ac.targetC] > 1) { //해당 위치에 타겟팅 된 적이 있다면
					copyMap[ac.targetR][ac.targetC] = 0;
					copyMap[ac.targetR][M]--; // 해당 행의 적 -1
					enemyCnt--;
					enemyKill++; // 킬 카운팅
				}
			}
		}
	}

	// 궁수 중에 타겟 설정 성공 시 true
	public static boolean archerTargeting() {
		boolean isTarget = false;
		for (int col = 0, archerCnt = 0; col < M && archerCnt < 3; col++) {
			if (archerCol[col] == 0)
				continue;
			Archer ac = archer3[archerCnt++];//해당 열에 궁수가 있다면
			if (fixedTarget(ac, col)) // 그 궁수의 타겟이 있다면
				isTarget = true;
		}
		return isTarget;
	}

	// 최근접, 최좌측 타겟 설정 성공 시 true
	public static boolean fixedTarget(Archer ac, int col) {
		for (int d = 1; d <= D; d++) {//탐지 범위를 점차 늘려감
			for (int j = col - d+1, jEnd = col + d-1; j <= jEnd; j++) { // 제일 왼쪽 열부터
				for (int i = N - 1, iEnd = N - d; i >= iEnd; i--) {
					if (j < 0 || j >= M || i < 0 || copyMap[i][M] == 0 || archerRow - i + Math.abs(col - j) != d)
						continue;// 경계 벗어남, 해당 행에 적 없음, 이미 확인한 거리
					if (copyMap[i][j] > 0) {
						copyMap[i][j]++;
						ac.targetR = i;
						ac.targetC = j;
						return true;
					}
				}
			}
		}
		return false;
	}

	// 다음 웨이브를 위한 아래방향 한칸 이동, 남아 있는 적 카운팅
	public static void nextWave() {
		enemyCnt -= copyMap[N - 1][M];
		for (int i = N - 1; i > 0; i--) {
			copyMap[i] = copyMap[i - 1];
		}
		copyMap[0] = emptyRow;
	}

	// 맵을 복사함
	public static void mapCopy() {
		copyMap = new int[N][M + 1];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j <= M; j++) {
				copyMap[i][j] = map[i][j];
			}
		}
	}
}