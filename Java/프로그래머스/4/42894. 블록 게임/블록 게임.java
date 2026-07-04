import java.io.*;
import java.util.*;

/**
 * @description 시뮬
 */
 
// 1x1 블록을 떨어트려 기존의 블럭을 직사각형으로 만들면 제거 가능함
// 1x1 블록은 위에서 떨어지기 때문에 떨어질 수 있는 위치가 제한적
// 먼저 검은 블록을 위에 전부 쌓고, 이미 쌓여있는 타겟들이 직사각형이 될 수 있는지 전부 검사
// 없앨 수 있는 것들을 전부 제거하고 다시 위에 쌓아서 검사 -> 반복

// 위에 쌓는 블록 갯수 N의 크기는 4이상 50이하
// NxN의 공간에서 각 원소는 0부터 200까지의 자연수로 구성되어 있으며 0은 빈공간
// 공간을 탐색하면서 각 블록의 좌상단 좌표, 우하단 좌표를 기억하여 직사각형을 저장
// 윗부분이 막혀있는 부분은 -1로 표시
// 다시 각 블록의 직사각형부분을 탐색하며 -1 또는 다른 블럭이 없는지 검사
// -> 제거할 수 있으면 제거하며 해당 직사각형 부분의 위쪽만 다시 검사
// 제거하고 검사하는 것을 반복하며 해당 턴에서 하나도 제거할 수 없다면 종료
// 시간복잡도는 최악 50*50+200*200*6*50
class Solution {
    private boolean canRemove(int num, int[] box, int[][] board){
        int cnt = 0;
        for(int r = box[0] ; r <= box[2] ; r++) {
            for(int c = box[1] ; c <= box[3] ; c++) {
                if(board[r][c] == num) continue;
                if(board[r][c] != 0) return false;
                for(int i = r-1 ; i >= 0 ; i--) {
                    if(board[i][c] != 0) return false;
                }
                cnt++;
            }
        }
        return cnt==2;
    }
    private void removeBlock(int num, int[] box, int[][] board){
        for (int r = box[0]; r <= box[2]; r++) {
            for (int c = box[1]; c <= box[3]; c++) {
                if (board[r][c] == num) {
                    board[r][c] = 0;
                }
            }
        }
    }
    public int solution(int[][] board) {
        int N = board.length;
        int[][] sq = new int[201][4]; // r, c, r ,c
        boolean[] exists = new boolean[201];
        for (int i = 1; i <= 200; i++) {
            sq[i][0] = 51; sq[i][1] = 51;
            sq[i][2] = -1; sq[i][3] = -1;
        }

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                int num = board[r][c];
                if (num > 0) {
                    exists[num] = true;
                    if (r < sq[num][0]) sq[num][0] = r;
                    if (c < sq[num][1]) sq[num][1] = c;
                    if (r > sq[num][2]) sq[num][2] = r;
                    if (c > sq[num][3]) sq[num][3] = c;
                }
            }
        }

        int answer = 0;
        boolean isRemoved = true;
        while(isRemoved) {
            isRemoved=false;
            for(int i = 1 ; i <= 200 ; i++) {
                if(!exists[i]) continue;

                if(canRemove(i,sq[i],board)) {
                    removeBlock(i,sq[i],board);
                    exists[i] = false;
                    isRemoved = true;
                    answer++;
                }
            }
        }
        return answer;
    }
}