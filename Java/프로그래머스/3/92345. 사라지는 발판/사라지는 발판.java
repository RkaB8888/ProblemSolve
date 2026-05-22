import java.io.*;
import java.util.*;

/**
 * @description 완전탐색
 */
// board: 게임 보드의 초기 상태를 나타내는 2차원 정수 배열
// -> 최대 5 X 5 크기, 원소는 0 또는 1
// aloc: 플레이어 A의 초기 위치, bloc: 플레이어 B의 초기 위치
// -> 항상 발판이 있는 곳에 위치하며 서로 같이 있을 수 있음
// 최적의 플레이를 했을 때 두 캐릭터의 움직인 횟수의 합 반환

// 최대 발판의 갯수는 25개, 서로 나눠서 발판을 소비하니깐 최대 13번의 선택 가능
// 플레이어는 각 선택마다 최대 4번(시작을 제외하면 최대 3번)의 경우의 수가 발생
// -> 즉, 시간 복잡도는 4^13번 -> 약 7000만

// DFS로 구현한다면
// 플레이어의 각 방향으로의 선택 과정에서 승(최소 턴)/패(최대 턴)을 얻고 결과를 반환
class Solution {
    private static final int[] DR = {0,0,1,-1};
    private static final int[] DC = {1,-1,0,0};
    int dfs(int[][] board, int cr, int cc, int or, int oc, int turn){
        if(board[cr][cc]==0) { // 현재 발판이 0이면 패배 처리
            return turn; // 1: B의 승리
        }
        board[cr][cc]=0;
        int minWinTurn = Integer.MAX_VALUE;
        int maxLoseTurn = turn;
        boolean canWin = false;
        for(int d = 0 ; d < 4 ; d++) {
            int nr = cr + DR[d];
            int nc = cc + DC[d];
            if(nr<0||nc<0||nr>=board.length||nc>=board[0].length) continue;
            if(board[nr][nc]==1) {
                int nresult = dfs(board, or, oc, nr, nc, turn+1);
                int nwin = nresult>>16; // 1이면 상대방이 이겼다는 의미
                int nturn = nresult&0xFFFF;
                if(nwin==0) { // 승리의 기회가 있다면
                    canWin = true;
                    minWinTurn = Math.min(minWinTurn,nturn);
                } else if(!canWin){ // 패배만 하는 경우
                    maxLoseTurn = Math.max(maxLoseTurn,nturn);
                }
            }
        }
        board[cr][cc]=1;
        if(canWin) return (1<<16)|minWinTurn;
        return maxLoseTurn;
    }
    public int solution(int[][] board, int[] aloc, int[] bloc) {
        int answer = dfs(board, aloc[0], aloc[1], bloc[0], bloc[1], 0);
        return answer&0xFFFF;
    }
    public static void main(String[] args) {
        int[][] board = {{1,1,1},{1,1,1},{1,1,1}};
        int[] aloc = {1,0};
        int[] bloc = {1,2};
        Solution sol = new Solution();
        System.out.println(sol.solution(board, aloc, bloc));
    }
}