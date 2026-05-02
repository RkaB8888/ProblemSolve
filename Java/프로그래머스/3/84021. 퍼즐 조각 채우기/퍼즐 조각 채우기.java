import java.io.*;
import java.util.*;

/**
 * @description flood-fill
 */
// 빈공간 하나 당 퍼즐은 무조건 하나만 들어가야 됨!!!
class Solution {
    int[] dr = {-1,1,0,0};
    int[] dc = {0,0,-1,1};
    int len;
    static class Point implements Comparable<Point> {
        int r, c;
        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public int compareTo(Point o) {
            if(this.r==o.r) return Integer.compare(this.c,o.c);
            return Integer.compare(this.r,o.r);
        }
    }
    public int solution(int[][] game_board, int[][] table) {
        len = game_board.length;
        List<List<Point>> board = new ArrayList<>();
        List<List<Point>> puzz = new ArrayList<>();
        boolean[][] visitedBoard = new boolean[len][len];
        boolean[][] visitedTable = new boolean[len][len];

        // 영역 추출
        for(int i = 0 ; i < len ; i++) {
            for(int j = 0 ; j < len ; j++) {
                if(game_board[i][j]==0&&!visitedBoard[i][j]) {
                    board.add(popArea(i,j,game_board,0,visitedBoard));
                }
                if(table[i][j]==1&&!visitedTable[i][j]) {
                    puzz.add(popArea(i,j,table,1,visitedTable));
                }
            }
        }
        int answer = 0;
        boolean[] usedPuzz = new boolean[puzz.size()];

        for(List<Point> b : board) {
            for(int i = 0 ; i < puzz.size() ; i++) {
                if(usedPuzz[i]) continue;
                List<Point> pu = puzz.get(i);
                if(b.size()!=pu.size()) continue;
                boolean isMatched = false;
                for(int j = 0 ; j < 4 ; j++) {
                    if(isMatch(b, pu)) {
                        isMatched = true;
                        break;
                    }
                    pu=rotate(pu);
                }
                if(isMatched) {
                    usedPuzz[i] = true;
                    answer+=pu.size();
                    // System.out.println("블럭 크기: "+pu.size());
                    break;
                }
            }
        }
        return answer;
    }
    private List<Point> popArea(int r, int c, int[][] arr, int val, boolean[][] visited) {
        List<Point> list = new ArrayList<>();
        Point[] s = new Point[6];
        int top = 0;
        s[top++] = new Point(r,c);
        visited[r][c] = true;
        while(top>0) {
            Point cur = s[--top];
            list.add(cur);
            for(int i = 0 ; i < 4 ; i++) {
                int nr = cur.r+dr[i];
                int nc = cur.c+dc[i];
                if(nr<0||nr>=len||nc<0||nc>=len) continue;
                if(arr[nr][nc]!=val||visited[nr][nc]) continue;
                visited[nr][nc] = true;
                s[top++] = new Point(nr,nc);
            }
        }
        return normalize(list);
    }
    private List<Point> normalize(List<Point> list) {
        int minR = Integer.MAX_VALUE;
        int minC = Integer.MAX_VALUE;
        for(Point p : list) {
            minR = Math.min(minR,p.r);
            minC = Math.min(minC,p.c);
        }
        List<Point> norm = new ArrayList<>();
        for(Point p : list) {
            norm.add(new Point(p.r-minR, p.c-minC));
        }
        Collections.sort(norm);
        return norm;
    }
    private List<Point> rotate(List<Point> list) {
        List<Point> result = new ArrayList<>();
        for(Point p : list) {
            result.add(new Point(p.c,-p.r));
        }
        return normalize(result);
    }
    private boolean isMatch(List<Point> space, List<Point> block) {
        for(int i = 0 ; i < space.size() ; i++) {
            if(space.get(i).r!=block.get(i).r || space.get(i).c!=block.get(i).c) return false;
        }
        return true;
    }
}