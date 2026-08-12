import java.io.*;
import java.util.*;

/**
 * @description BFS
 */
 
// 2 x 1 크기의 로봇이 1,1에서 N,N 까지 도달하는데 걸리는 시간 계산
// 90도 회전과 이동에 1초씩 걸림
// board의 길이는 5이상 100이하
// board의 원소는 0 또는 1(벽)

// 이동 방법은 상/하/좌/우
// 회전 방법은 왼쪽 축으로 시계/반시계, 오른쪽 축으로 시계/반시계

// 1번축 기준 상(0)/우(1)/하(2)/좌(3) 로 뻗는 경우
// 2번축 기준 하(0)/좌(1)/상(2)/우(3) 와 동일한 형태
// BFS로 8가지 이동에 대해 큐에 담고 N,N에 도달하는지 보면 될 듯?
class Solution {
    private boolean isEnd(int r, int c, int s, int n){
        if(r==n-1&&c==n-1) return true;
        if(r==n-2&&c==n-1&&s==2) return true;
        if(r==n-1&&c==n-2&&s==1) return true;
        return false;
    }
    private boolean inBound(int r, int c, int n){
        return r<n&&c<n&&r>=0&&c>=0;
    }
    private void test(int r1, int c1, int r2, int c2, int s, int[] nextS, int t, int n, int[][] rotate, int[][] check, int[][] board, boolean[][][] visited, Queue<int[]> q){
        // 1번축 시계방향 회전 검사
        int tempr = r1+check[s][0];
        int tempc = c1+check[s][1];
        if(inBound(tempr, tempc, n)&&board[tempr][tempc]==0) {
            int nr2 = r2+rotate[s][0];
            int nc2 = c2+rotate[s][1];
            int ns = nextS[s];
            // 이동한 2번좌표가 안에 있는지, 빈공간인지, 온적 있는지 검사
            if(inBound(nr2, nc2, n)&&board[nr2][nc2]==0&&!visited[r1][c1][ns]) {
                visited[r1][c1][ns] = true;
                q.add(new int[]{r1,c1,ns,t+1});
            }
        }
    }
    public int solution(int[][] board) {
        int n = board.length;
        boolean[][][] visited = new boolean[n][n][4]; // 1번 축 기준 위치 4개를 체크
        visited[0][0][1] = true;
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{0,0,1,0}); // (0,0)에 1의 형태 0초

        int[][] other = {{-1,0},{0,1},{1,0},{0,-1}}; // 1번축 기준 2번축 위치, 겸사겸사 이동까지

        int[][] cw = {{1,1},{1,-1},{-1,-1},{-1,1}}; // 기준축을 시계방향 회전시 반대축이 이동되는 위치
        int[][] cwCheck = {{-1,1},{1,1},{1,-1},{-1,-1}}; // 기준축을 시계방향 회전시 체크위치
        int[] nextcwS = {1,2,3,0};

        int[][] ccw = {{1,-1},{-1,-1},{-1,1},{1,1}}; // 기준축을 반시계방향 회전시 반대축이 이동되는 위치
        int[][] ccwCheck = {{-1,-1},{-1,1},{1,1},{1,-1}}; // 기준축을 반시계방향 회전시 체크위치
        int[] nextccwS = {3,0,1,2};

        int[] otherS = {2,3,0,1}; // 축 변경시 모양 변경
        while(!q.isEmpty()) {
            int[] pos = q.poll();
            int r1 = pos[0];
            int c1 = pos[1];
            int s = pos[2];
            int t = pos[3];

            int r2 = r1 + other[s][0];
            int c2 = c1 + other[s][1];

            if(isEnd(r1, c1, s, n)) return t;
            for(int i = 0 ; i < 4 ; i++) { // 평행이동 검사
                int nr1 = r1+other[i][0];
                int nc1 = c1+other[i][1];
                int nr2 = r2+other[i][0];
                int nc2 = c2+other[i][1];
                if(inBound(nr1, nc1, n)&&inBound(nr2, nc2, n)) {
                    if(board[nr1][nc1]==0&&board[nr2][nc2]==0&&!visited[nr1][nc1][s]) {
                        visited[nr1][nc1][s] = true;
                        q.add(new int[]{nr1,nc1,s,t+1});
                    }
                }
            }
            test(r1, c1, r2, c2, s, nextcwS, t, n, cw, cwCheck, board, visited, q);
            test(r1, c1, r2, c2, s, nextccwS, t, n, ccw, ccwCheck, board, visited, q);
            test(r2, c2, r1, c1, otherS[s], nextcwS, t, n, cw, cwCheck, board, visited, q);
            test(r2, c2, r1, c1, otherS[s], nextccwS, t, n, ccw, ccwCheck, board, visited, q);
        }
        return -1;
    }
}