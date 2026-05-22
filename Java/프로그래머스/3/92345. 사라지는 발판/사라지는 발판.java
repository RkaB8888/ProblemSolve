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
    int[] dfs(int[][] board, int ar, int ac, int br, int bc, int turn){
        if((turn&1)==0) { // turn이 짝수면 A 차례
            if(board[ar][ac]==0) { // 현재 발판이 0이면 패배 처리
                return new int[] {1, turn}; // 1: B의 승리
            }
            board[ar][ac]=0;
            int[] result = new int[] {1,turn};
            for(int d = 0 ; d < 4 ; d++) {
                int nr = ar + DR[d];
                int nc = ac + DC[d];
                if(nr<0||nc<0||nr>=board.length||nc>=board[0].length) continue;
                if(board[nr][nc]==1) {
                    int[] answer = dfs(board, nr, nc, br, bc, turn+1);
                    if(answer[0]==0) { // 승리의 기회가 있다면
                        if(result[0]==1) {
                            result[0] = 0;
                            result[1] = answer[1];
                        } else result[1] = Math.min(result[1],answer[1]);
                    }
                    else if(result[0]==1){ // 패배만 하는 경우
                        result[1] = Math.max(result[1],answer[1]);
                    }
                }
            }
            board[ar][ac]=1;
            return result;
        }else {
            if(board[br][bc]==0) {
                return new int[] {0, turn}; // 0: A의 승리
            }
            board[br][bc]=0;
            int[] result = new int[] {0,turn};
            for(int d = 0 ; d < 4 ; d++) {
                int nr = br + DR[d];
                int nc = bc + DC[d];
                if(nr<0||nc<0||nr>=board.length||nc>=board[0].length) continue;
                if(board[nr][nc]==1) {
                    int[] answer = dfs(board, ar, ac, nr, nc, turn+1);
                    if(answer[0]==1) { // 승리의 기회가 있다면
                        if(result[0]==0) {
                            result[0] = 1;
                            result[1] = answer[1];
                        } else result[1] = Math.min(result[1],answer[1]);
                    }
                    else if(result[0]==0){ // 패배만 하는 경우
                        result[1] = Math.max(result[1],answer[1]);
                    }
                }
            }
            board[br][bc]=1;
            return result;
        }
    }
    public int solution(int[][] board, int[] aloc, int[] bloc) {
        int[] answer = dfs(board, aloc[0], aloc[1], bloc[0], bloc[1], 0);
        // if(answer[0]==1) System.out.println("A 패배");
        return answer[1];
    }
    public static void main(String[] args) {
        int[][] board = {{1,1,1},{1,0,1},{1,1,1}};
        int[] aloc = {1,0};
        int[] bloc = {1,2};
        Solution sol = new Solution();
        System.out.println(sol.solution(board, aloc, bloc));
    }
}