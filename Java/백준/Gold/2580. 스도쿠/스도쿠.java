import java.io.*;
import java.util.*;

public class Main {
    static int[][] map;
    static List<int[]> blanks;
    static int[] rowCheck, colCheck, nineCheck;
    static boolean solved;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        map = new int[9][9];
        blanks = new ArrayList<>();
        rowCheck = new int[9];
        colCheck = new int[9];
        nineCheck = new int[9];

        // 입력받기
        for (int i = 0; i < 9; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 0) {
                    blanks.add(new int[]{i, j});
                } else {
                    int num = map[i][j];
                    updateState(i, j, num, true); // 초기 상태 업데이트
                }
            }
        }

        // 백트래킹 시작
        solve(0);

        // 출력
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sb.append(map[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    static void solve(int depth) {
        if (solved) return; // 이미 해결된 경우 탐색 중지
        if (depth == blanks.size()) {
            solved = true;
            return;
        }

        int[] blank = blanks.get(depth);
        int r = blank[0], c = blank[1];
        int grid = (r / 3) * 3 + (c / 3);

        // 가능한 숫자 탐색
        for (int num = 1; num <= 9; num++) {
            if (isValid(r, c, grid, num)) {
                updateState(r, c, num, true); // 상태 업데이트
                map[r][c] = num;

                solve(depth + 1);

                if (solved) return; // 해결된 경우 종료
                updateState(r, c, num, false); // 상태 되돌리기
                map[r][c] = 0;
            }
        }
    }

    static boolean isValid(int r, int c, int grid, int num) {
        int bit = 1 << num;
        return (rowCheck[r] & bit) == 0 &&
               (colCheck[c] & bit) == 0 &&
               (nineCheck[grid] & bit) == 0;
    }

    static void updateState(int r, int c, int num, boolean set) {
        int bit = 1 << num;
        int grid = (r / 3) * 3 + (c / 3);
        if (set) {
            rowCheck[r] |= bit;
            colCheck[c] |= bit;
            nineCheck[grid] |= bit;
        } else {
            rowCheck[r] &= ~bit;
            colCheck[c] &= ~bit;
            nineCheck[grid] &= ~bit;
        }
    }
}