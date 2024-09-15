import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int N, result;
    static final int drdc[][] = { {-1, 0}, {1, 0}, {0, -1}, {0, 1} };
    static final int reflectDir[][] = { 
        {}, 
        {1, 3, 0, 2}, // 블록 1
        {3, 0, 1, 2}, // 블록 2
        {2, 0, 3, 1}, // 블록 3
        {1, 2, 3, 0}, // 블록 4
        {1, 0, 3, 2}  // 블록 5
    };
    static int[][] map;
    static WormHole[] wormHoles;

    static class WormHole {
        int r1, c1, r2, c2;
        public WormHole(int r1, int c1, int r2, int c2) {
            this.r1 = r1;
            this.c1 = c1;
            this.r2 = r2;
            this.c2 = c2;
        }
        public void warp(int[] curP) {
            if (curP[0] == r1 && curP[1] == c1) {
                curP[0] = r2;
                curP[1] = c2;
            } else {
                curP[0] = r1;
                curP[1] = c1;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int TC = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= TC; tc++) {
            result = 0;
            N = Integer.parseInt(br.readLine().trim());
            map = new int[N][N];
            wormHoles = new WormHole[5]; // 웜홀 최대 5쌍(6~10)
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine().trim());
                for (int j = 0; j < N; j++) {
                    int val = Integer.parseInt(st.nextToken());
                    map[i][j] = val;
                    if (val >= 6) { // 웜홀일 때
                        if (wormHoles[val - 6] == null) {
                            wormHoles[val - 6] = new WormHole(i, j, -1, -1);
                        } else {
                            wormHoles[val - 6].r2 = i;
                            wormHoles[val - 6].c2 = j;
                        }
                    }
                }
            }
            simulate();
            sb.append('#').append(tc).append(' ').append(result).append('\n');
        }
        System.out.println(sb);
    }

    public static void simulate() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 0) { // 빈 공간에서 시작
                    for (int d = 0; d < 4; d++) {
                        runGame(i, j, d);
                    }
                }
            }
        }
    }

    public static void runGame(int row, int col, int dir) {
        int[] curP = {row, col};
        int curDir = dir;
        int cnt = 0;

        curP[0] += drdc[curDir][0];
        curP[1] += drdc[curDir][1];

        while (true) {
            if (curP[0] < 0 || curP[0] >= N || curP[1] < 0 || curP[1] >= N || map[curP[0]][curP[1]] == 5) {
                // 경계 충돌 또는 5번 블록 충돌
                result = Math.max(result, cnt * 2 + 1);
                return;
            } else if (map[curP[0]][curP[1]] == -1 || (curP[0] == row && curP[1] == col)) {
                // 블랙홀이나 출발점으로 돌아온 경우
                result = Math.max(result, cnt);
                return;
            } else if (map[curP[0]][curP[1]] >= 6) {
                // 웜홀을 만났을 때
                wormHoles[map[curP[0]][curP[1]] - 6].warp(curP);
            } else if (map[curP[0]][curP[1]] >= 1) {
                // 블록에 부딪힐 때 방향 반사
                cnt++;
                curDir = reflectDir[map[curP[0]][curP[1]]][curDir];
            }
            curP[0] += drdc[curDir][0];
            curP[1] += drdc[curDir][1];
        }
    }
}