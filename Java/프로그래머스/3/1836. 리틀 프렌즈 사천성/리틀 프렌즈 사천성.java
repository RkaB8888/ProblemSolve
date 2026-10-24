// 알파벳 순으로 가장 먼저인 문자열을 리턴해야 하므로 탐색 순서는 알파벳 순으로 하는 것이 합당
// 최대 한번만 꺾일 수 있다는 것은 A의 두 좌표가 (6,8), (7,9)인 경우 (6,9) 또는 (7,8)에서만 꺾어야 함
// 해당 좌표까지의 길이 있는지 검사하면 될 듯
class Solution {
    private boolean hori(int c1, int c2, int r, boolean[][] map) {
        if(c1>c2) {
            int temp = c1;
            c1 = c2;
            c2 = temp;
        }
        for(int i = c1+1 ; i < c2 ; i++) {
            if(!map[r][i]) return false;
        }
        return true;
    }
    private boolean verti(int r1, int r2, int c, boolean[][] map) {
        if(r1>r2) {
            int temp = r1;
            r1 = r2;
            r2 = temp;
        }
        for(int i = r1+1 ; i < r2 ; i++) {
            if(!map[i][c]) return false;
        }
        return true;
    }
    public String solution(int m, int n, String[] board) {
        int[][][] pos = new int[26][2][2]; // 각 알파벳의 좌표
        boolean[] check = new boolean[26]; // 알파벳 존재 유무
        boolean[][] map = new boolean[m][n];
        int cnt = 0; // 없애는 페어 갯수
        for(int i = 0 ; i < m ; i++) {
            String row = board[i];
            for(int j = 0 ; j < n ; j++) {
                char c = row.charAt(j);
                if(c=='.') {
                    map[i][j] = true;
                    continue;
                } else if(c=='*') {
                    continue;
                }
                int temp = c-'A';
                if(check[temp]) {
                    pos[temp][1][0] = i;
                    pos[temp][1][1] = j;
                } else {
                    check[temp] = true;
                    cnt++;
                    pos[temp][0][0] = i;
                    pos[temp][0][1] = j;
                }
            }
        }
        int result = 0;
        StringBuilder sb = new StringBuilder();
        while(result<cnt) {
            boolean removed = false;
            for(int i = 0 ; i < 26 ; i++) {
                if(!check[i]) continue;
                int ax = pos[i][0][0];
                int ay = pos[i][0][1];
                int bx = pos[i][1][0];
                int by = pos[i][1][1];
                boolean possible = false;
                if(ax==bx) {
                    possible = hori(ay,by,ax,map);
                } else if(ay==by) {
                    possible = verti(ax,bx,ay,map);
                } else {
                    possible = (map[ax][by]&&hori(ay,by,ax,map)&&verti(ax,bx,by,map))||(map[bx][ay]&&verti(ax,bx,ay,map)&&hori(ay,by,bx,map));
                }
                if(!possible) continue;
                sb.append((char)('A'+i));
                check[i] = false;
                map[ax][ay] = true;
                map[bx][by] = true;
                result++;
                removed = true;
                break;
            }
            if(!removed) return "IMPOSSIBLE";
        }
        return sb.toString();
    }
}