import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static final int DIFF = 'a' - 'A';
    static int R, C, result;
    static char[][] map;
    static boolean usedKey[], visited[][];
    static List<Door>[] doors;
    static int[][] drdc = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
    static Queue<int[]> queue;
    static class Door {
        int r, c;
        public Door(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();

        while (T-- > 0) {
            input(br);
            bfs();
            sb.append(result).append('\n');
        }
        System.out.print(sb);
    }

    private static void input(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken()) + 2;
        C = Integer.parseInt(st.nextToken()) + 2;

        map = new char[R][C];
        visited = new boolean[R][C];
        doors = new List[26]; // 각 문을 저장하는 리스트
        for (int i = 0; i < 26; i++) doors[i] = new ArrayList<>();
        usedKey = new boolean[26];
        queue = new ArrayDeque<>();
        result = 0;

        // 외곽을 빈 공간으로 초기화
        Arrays.fill(map[0], '.');
        Arrays.fill(map[R - 1], '.');

        for (int i = 1; i < R - 1; i++) {
            char[] row = br.readLine().toCharArray();
            map[i][0] = map[i][C - 1] = '.';
            System.arraycopy(row, 0, map[i], 1, row.length);

            for (int j = 1; j < C - 1; j++) {
                if (map[i][j] >= 'A' && map[i][j] <= 'Z') {
                    doors[map[i][j] - 'A'].add(new Door(i, j)); // 문 위치 저장
                }
            }
        }

        char[] initialKeys = br.readLine().toCharArray();
        if (initialKeys[0] != '0') {
            for (char key : initialKeys) {
                usedKey[key - 'a'] = true; // 초기 키 저장
                openDoors(key - 'a'); // 초기 키로 열 수 있는 문 열기
            }
        }
    }

    private static void bfs() {
        queue.add(new int[] { 0, 0 }); // 시작점
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int r = cur[0], c = cur[1];

            for (int[] d : drdc) {
                int nr = r + d[0], nc = c + d[1];
                if (nr < 0 || nr >= R || nc < 0 || nc >= C || visited[nr][nc] || map[nr][nc] == '*') 
                    continue;

                char cell = map[nr][nc];
                visited[nr][nc] = true;

                if (cell == '$') {
                    result++; // 문서 획득
                    map[nr][nc] = '.';
                } else if (cell >= 'a' && cell <= 'z') { // 키를 발견한 경우
                    int key = cell - 'a';
                    if (!usedKey[key]) { // 새 키라면
                        usedKey[key] = true;
                        openDoors(key); // 해당 키로 열 수 있는 문 열기
                    }
                    map[nr][nc] = '.';
                } else if (cell >= 'A' && cell <= 'Z') { // 문을 만난 경우
                    int door = cell - 'A';
                    if (!usedKey[door]) continue; // 열쇠가 없으면 무시
                    map[nr][nc] = '.';
                }
                queue.add(new int[] { nr, nc });
            }
        }
    }

    private static void openDoors(int key) {
        for (Door door : doors[key]) {
            map[door.r][door.c] = '.';
            if(check(door.r, door.c)) {
            	queue.add(new int[] {door.r, door.c});
            }
        }
    }
    
    private static boolean check(int r, int c) {
    	for(int i = 0 ; i < 4 ; i++) {
    		int nr = r + drdc[i][0], nc = c + drdc[i][1];
    		if(nr<0||nc<0||nr>=R||nc>=C) continue;
    		if(visited[nr][nc]) return true;
    	}
    	return false;
    }
}