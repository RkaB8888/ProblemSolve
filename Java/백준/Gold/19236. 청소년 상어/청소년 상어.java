import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * BFS 구현 메모리 ? KB 시간 ? ms
 * 
 * @author python98
 *
 */
public class Main {
	static int[][] drdc = { { -1, 0 }, { -1, -1 }, { 0, -1 }, { 1, -1 }, { 1, 0 }, { 1, 1 }, { 0, 1 }, { -1, 1 } };
	static Fish[] fishes;
	static Shark shark;
	static int[][] map;
	static int result;

	static abstract class Ani {
        int dir, x, y;

        Ani(int dir, int x, int y) {
            this.dir = dir;
            this.x = x;
            this.y = y;
        }
    }

	static class Fish extends Ani {
        public Fish(int dir, int x, int y) {
            super(dir, x, y);
        }

        Fish next(int[][] nextMap) {
            int nx = this.x;
            int ny = this.y;
            int nDir = this.dir;
            for (int i = 0; i < 8; i++) {
                nx = this.x + drdc[(i + this.dir) % 8][0];
                ny = this.y + drdc[(i + this.dir) % 8][1];
                if (nx < 0 || nx >= 4 || ny < 0 || ny >= 4 || nextMap[nx][ny] == 0)
                    continue;
                nDir = (i + this.dir) % 8;
                break;
            }
            return new Fish(nDir, nx, ny);
        }
    }

    static class Shark extends Ani {
        int eat;

        public Shark(int dir, int x, int y, int eat) {
            super(dir, x, y);
            this.eat = eat;
        }
    }

    static class Data {
        Fish[] fishesData;
        Shark sharkData;
        int[][] mapData;

        Data(Fish[] fishesData, Shark sharkData, int[][] mapData) {
            this.fishesData = fishesData;
            this.sharkData = sharkData;
            this.mapData = mapData;
        }
    }

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		fishes = new Fish[17];
		map = new int[4][4];
		for (int i = 0; i < 4; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 4; j++) {
				int num = Integer.parseInt(st.nextToken());
				Fish fish = new Fish(Integer.parseInt(st.nextToken()) - 1, i, j);
				fishes[num] = fish;
				map[i][j] = num;
			}
		}
		Fish fish = (Fish) fishes[map[0][0]];
		shark = new Shark(fish.dir, fish.x, fish.y, map[0][0]);
		fishes[map[0][0]] = null;
		map[0][0] = 0;

		bfs();

		System.out.print(result);
	}

	public static void bfs() {
		Queue<Data> q = new ArrayDeque<>();
		q.add(new Data(copyFishes(fishes),shark,copyMap(map)));

		while (!q.isEmpty()) {
			Data curData = q.poll();
			int[][] curMap = curData.mapData;
			Fish[] curFishes = curData.fishesData;
			Shark curShark = curData.sharkData;
			result = Math.max(curShark.eat, result);
			// 물고기 이동
			for (int i = 1; i < 17; i++) {
				if (curFishes[i] == null)
					continue;
				Fish curFish = curFishes[i];// 해당 번호 물고기
				Fish nextFish = curFish.next(curMap);// 이동한다면 물고기 정보

				// 맵 갱신
				int num = curMap[nextFish.x][nextFish.y];// 이동할 자리에 있는 물고기 번호
				curMap[nextFish.x][nextFish.y] = curMap[curFish.x][curFish.y];
				curMap[curFish.x][curFish.y] = num;

				// 물고기 목록 갱신
				curFishes[i] = nextFish;
				if (num != -1) {
					curFishes[num].x = curFish.x;
					curFishes[num].y = curFish.y;
				}
			}
			
			int nx = curShark.x;
			int ny = curShark.y;
			// 상어 이동
			for (int i = 0; i < 3; i++) {
				nx += drdc[curShark.dir][0];
				ny += drdc[curShark.dir][1];
				if (nx < 0 || ny < 0 || nx >= 4 || ny >= 4)
					break;// 맵을 넘어가면 더 이상 상어이동 불가
				if (curMap[nx][ny] == -1)
					continue;// 물고기 없으면 다음 이동으로
								
				int eatNum = curMap[nx][ny];// 먹을 물고기 번호
				Fish eatFish = curFishes[eatNum];// 먹을 물고기 정보 가져오기
				Shark nextShark = new Shark(eatFish.dir, nx, ny, curShark.eat + eatNum);// 먹은 상어

				// 물고기 목록 갱신
				Fish[] nextFishes = copyFishes(curFishes);// 다음으로 보낼 물고기 목록 생성
				nextFishes[eatNum] = null;

				// 맵 갱신
				int[][] nextMap = copyMap(curMap);
				nextMap[nx][ny] = 0;
				nextMap[curShark.x][curShark.y] = -1;

				q.add(new Data(nextFishes, nextShark,nextMap));
			}
		}
	}

	public static Fish[] copyFishes(Fish[] origin) {
		Fish[] newFishes = new Fish[17];
		for (int i = 1; i < 17; i++) {
			if (origin[i] == null)
				continue;
			Fish curFish = origin[i];
			Fish newFish = new Fish(curFish.dir, curFish.x, curFish.y);
			newFishes[i] = newFish;
		}
		return newFishes;
	}

	public static int[][] copyMap(int[][] map) {
		int[][] newMap = new int[4][4];
		for (int i = 0; i < 4; i++) {
			System.arraycopy(map[i], 0, newMap[i], 0, 4);
		}
		return newMap;
	}
}