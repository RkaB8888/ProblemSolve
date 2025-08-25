import java.io.*;
import java.util.*;

/**
 * @author python98
 * @description ?
 * @performance 메모리: ? KB, 동작시간: ? ms
 */
public class Main {

    static int R, C;
    static int[] points = new int[2];
    static final int[][] DIR = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    static char[][] map;
    static boolean[] visited;
    static Queue<Integer> meltList = new ArrayDeque<>();


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        //입력값 저장
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][];
        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
        }

        //백조 위치 저장
        int cnt = 0;
        o: for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                if(map[i][j] == 'L') {
                    points[cnt++] = i*C+j;
                    map[i][j] = '.';
                    if(cnt == 2) break o;
                }
            }
        }

        //빙판과 인접한 물 위치 저장
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 'X') {
                    for (int k = 0; k < 4; k++) {
                        int r = i + DIR[k][0];
                        int c = j + DIR[k][1];
                        if (r < 0 || r >= R || c < 0 || c >= C) continue;
                        if (map[r][c] != 'X') {
                            meltList.add(r * C + c);
                            break;
                        }
                    }
                }
            }
        }

        //시작백조에서 처음 접하는 얼음 위치 저장
        Queue<Integer> swanIce = new ArrayDeque<>();
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(points[0]);

        visited = new boolean[R*C];
        visited[points[0]]=true;

        while(!queue.isEmpty()) {
            int idx = queue.poll();
            if(idx == points[1]) {
                System.out.print(0);
                return;
            }
            int r = idx / C;
            int c = idx % C;
            for (int k = 0; k < 4; k++) {
                int nr = r + DIR[k][0];
                int nc = c + DIR[k][1];
                if (nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
                if (visited[nr * C + nc]) continue;
                if (map[nr][nc] == 'X') {
                    swanIce.add(nr * C + nc);
                } else {
                    queue.add(nr * C + nc);
                }
                visited[nr * C + nc] = true;
            }
        }

        //날짜 이동 시작
        int day = 0;
        while(true) {
            //빙판 녹이기
            melt();
            day++;

            //연결 확인
            swanIce = bfs(swanIce);
            if(swanIce.isEmpty()) break;
        }
        System.out.print(day);
    }

    private static Queue<Integer> bfs(Queue<Integer> start) {
        Queue<Integer> end = new ArrayDeque<>();
        while (!start.isEmpty()) {
            int idx = start.poll();
            if(idx == points[1]) {
                end.clear();
                return end;
            }
            int r = idx / C;
            int c = idx % C;
            for (int k = 0; k < 4; k++) {
                int nr = r + DIR[k][0];
                int nc = c + DIR[k][1];
                if (nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
                if (visited[nr*C+nc]) continue;
                if (map[nr][nc] == 'X') {
                    end.add(nr * C + nc);
                } else {
                    start.add(nr * C + nc);
                }
                visited[nr*C+nc] = true;
            }
        }
        return end;
    }

    private static void melt() {
        if (meltList.isEmpty()) return;
        int len = meltList.size();
        while (--len >= 0) {
            int idx = meltList.poll();
            int r = idx / C;
            int c = idx % C;
            for (int k = 0; k < 4; k++) {
                int nr = r + DIR[k][0];
                int nc = c + DIR[k][1];
                if (nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
                if (map[nr][nc] == 'X') {
                    map[nr][nc] = '.';
                    meltList.add(nr * C + nc);
                }
            }
        }
    }
}
