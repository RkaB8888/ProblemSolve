import java.io.*;
import java.util.*;

/**
 * @author python98
 * @description ?
 * @performance 메모리: ? KB, 동작시간: ? ms
 */
public class Main {

    static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {0, -1}};
    static int N, M, result;
    static int[][] map, dp, ver;
//  map은 입력 값 그대로 저장
//  dp는 수평으로 계산한 값 저장
//  ver는 수직으로 계산한 값 저장
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        dp = new int[N][M];
        ver = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dp[0][0] = ver[0][0] = map[0][0];
        for (int j = 1; j < M; j++) {
            ver[0][j] = ver[0][j - 1] + map[0][j];
            dp[0][j] = ver[0][j];
        }
        int row = 1;
        while (row < N) {
            verCalc(row);
            for (int j = 0; j < M; j++) {
                horiCalc(row, j);
            }
            row++;
        }
        System.out.println(dp[N - 1][M - 1]);
//        for(int i = 0; i < N; i++) {
//            System.out.println(Arrays.toString(dp[i]));
//        }
    }

    private static void verCalc(int row) {
        for(int j = 0; j < M; j++) {
            ver[row][j] = dp[row - 1][j] + map[row][j];
            dp[row][j] = ver[row][j];
        }
    }

    private static void horiCalc(int row, int col) {
        int j = col-1, horiSum = 0;
        while(j>=0) {
            horiSum += map[row][j];
            dp[row][j] = Math.max(dp[row][j], ver[row][col]+horiSum);
            j--;
        }
        j = col+1;
        horiSum = 0;
        while(j<M) {
            horiSum += map[row][j];
            dp[row][j] = Math.max(dp[row][j], ver[row][col]+horiSum);
            j++;
        }
    }

}
