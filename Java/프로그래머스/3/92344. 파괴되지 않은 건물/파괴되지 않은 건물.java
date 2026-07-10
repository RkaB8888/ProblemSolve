import java.io.*;
import java.util.*;

/**
 * @description 누적합
 */
 
// board 길이는 1이상 1000이하
// board 요소 길이는 1이상 1000이하
// board의 원소는 1이상 1000이하
// skill의 길이는 1이상 250,000이하
// skill 요소 길이는 6

// 단순 시뮬레이터로 하면 시간복잡도 1000*1000*250000
// 2차원 누적합으로 배열 관리 시간복잡도 1000*1000
class Solution {
    public int solution(int[][] board, int[][] skill) {
        int n = board.length;
        int m = board[0].length;
        
        int[][] sumArr = new int[n + 1][m + 1];

        for (int[] s : skill) {
            int r1 = s[1];
            int c1 = s[2];
            int r2 = s[3];
            int c2 = s[4];
            int val = (s[0] == 1) ? -s[5] : s[5];

            sumArr[r1][c1] += val;
            sumArr[r1][c2 + 1] -= val; // 원복
            sumArr[r2 + 1][c1] -= val;
            sumArr[r2 + 1][c2 + 1] += val;
        }

        for (int r = 0; r < n; r++) {
            for (int c = 1; c < m; c++) {
                sumArr[r][c] += sumArr[r][c - 1];
            }
        }

        for (int c = 0; c < m; c++) {
            for (int r = 1; r < n; r++) {
                sumArr[r][c] += sumArr[r - 1][c];
            }
        }

        int answer = 0;
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                if (board[r][c] + sumArr[r][c] > 0) {
                    answer++;
                }
            }
        }

        return answer;
    }
}