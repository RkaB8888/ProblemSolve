import java.io.*;
import java.util.*;

/**
 * @description BFS 완전탐색 순열
 */

// board는 4*4 크기 2차원 배열
// board의 각 원소는 0이상 6이하 자연수
// - 0은 빈칸, 1~6은 2개 씩 존재
// r은 시작 좌표의 행 위치, c는 시작 좌표의 열 위치 (0이상 3이하)
// - 좌상단이 0,0 우하단이 3,3

// 이동은 상하좌우 한칸 이동, 상하좌우에서 끝이나 만나는 카드까지 이동, 카드 뒤집기 존재
// 모든 숫자가 있는 칸에서 다른 숫자가 있는 칸까지의 횟수를 모두 저장하고
// 시작 -> 최초의 카드 -> 쌍카드 -> 다른 카드 -> 다른 쌍카드 ...의 최소 횟수를 계산하면
// 시작 좌표 -> 12군데 -> 1군데 -> 10군데 -> 1군데 -> 8군데 ... -> 7!
// 그럼 7*7 2차원 배열에는 각 숫자까지의 최소 횟수를 담아두고
// 7 1차원 배열에는 서로 쌍을 맞추기 위한 최소 횟수를 담아두고

class Solution {
    int[][] board;
    List<Integer> cardNums = new ArrayList<>();
    Map<Integer, List<int[]>> cardPos = new HashMap<>();
    int minMoves = Integer.MAX_VALUE;

    int[] dr = {0,0,1,-1};
    int[] dc = {1,-1,0,0};

    private int bfs(int sr, int sc, int er, int ec){
        if(sr==er&&sc==ec) return 0;

        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[4][4];
        q.add(new int[]{sr,sc,0});
        visited[sr][sc] = true;

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int cr = cur[0], cc = cur[1], cnt = cur[2];
            if(cr==er&&cc==ec) return cnt;

            for(int d = 0 ; d < 4 ; d++) {
                // 1칸 이동
                int nr = cr+dr[d];
                int nc = cc+dc[d];
                if(nr >= 0 && nr < 4 && nc >= 0 && nc < 4) {
                    if(!visited[nr][nc]) {
                        visited[nr][nc] = true;
                        q.add(new int[]{nr, nc, cnt + 1});
                    }
                }

                // ctrl 이동
                nr = cr; 
                nc = cc;
                while(true) {
                    nr += dr[d];
                    nc += dc[d];

                    if(nr < 0 || nr >= 4 || nc < 0 || nc >= 4) {
                        nr -= dr[d];
                        nc -= dc[d];
                        break;
                    }

                    // 카드 충돌
                    if(board[nr][nc] != 0) break;
                }
                
                if(!visited[nr][nc]) {
                    visited[nr][nc] = true;
                    q.add(new int[]{nr, nc, cnt + 1});
                }
            }
        }
        return 0;
    }

    private void permutation(int depth, int r, int c, int totalMoves, boolean[] visited) {
        if(totalMoves >= minMoves) return;
        if(depth == cardNums.size()) {
            minMoves = Math.min(minMoves, totalMoves);
            return;
        }

        for(int i = 0 ; i < cardNums.size() ; i++) {
            if(visited[i]) continue;
            visited[i] = true;
            int num = cardNums.get(i);
            int[] pos1 = cardPos.get(num).get(0);
            int[] pos2 = cardPos.get(num).get(1);

            int move1 = bfs(r,c,pos1[0], pos1[1]) + bfs(pos1[0],pos1[1],pos2[0],pos2[1]) + 2;
            board[pos1[0]][pos1[1]] = 0;
            board[pos2[0]][pos2[1]] = 0;
            permutation(depth+1, pos2[0], pos2[1], totalMoves+move1, visited);
            board[pos1[0]][pos1[1]] = num; // bfs에서 카드 충돌 고려해야 함
            board[pos2[0]][pos2[1]] = num;

            int move2 = bfs(r,c,pos2[0], pos2[1]) + bfs(pos2[0],pos2[1],pos1[0],pos1[1]) + 2;
            board[pos1[0]][pos1[1]] = 0;
            board[pos2[0]][pos2[1]] = 0;
            permutation(depth+1, pos1[0], pos1[1], totalMoves+move2, visited);
            board[pos1[0]][pos1[1]] = num;
            board[pos2[0]][pos2[1]] = num;

            visited[i] = false;
        }
    }

    public int solution(int[][] board, int r, int c) {
        this.board = board;

        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                if(board[i][j] != 0) {
                    int num = board[i][j];
                    if(!cardNums.contains(num)) {
                        cardNums.add(num);
                    }
                    cardPos.putIfAbsent(num, new ArrayList<>());
                    cardPos.get(num).add(new int[]{i, j});
                }
            }
        }

        boolean[] visited = new boolean[cardNums.size()];
        permutation(0,r,c,0,visited);

        return minMoves;
    }
}