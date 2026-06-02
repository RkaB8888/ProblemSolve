import java.io.*;
import java.util.*;

/**
 * @description 시뮬레이션
 */
 
// 2차원 좌표계에서 조건을 만족하는 기등/보 설치 및 삭제 시뮬
// 기둥은 바닥(보, 기둥, 지면)이 존재하는 곳에서 설치 시 위쪽으로 생성
// 보는 한쪽이 기둥과 닿아있거나 양쪽이 보랑 닿아있어야 하며 설치 시 오른쪽으로 생성
// 구조물이 조건을 만족하지 않게 되면 명령을 무시한다.
// n은 5이상 100이하 자연수
// build_frame 길이는 최대 1000
// build_frame[i] 길이는 4개 [x,y,기둥/보,삭제/설치]
// return 값은 [x,y,기둥/보]로 설치된 정보를 담고 있어야 함 (x오름차순 -> y오름차순 -> 기둥 우선)

// 설치할 때 주변 좌표4개를 확인하며 가능한지 체크
// 삭제할 때 삭제 후 주변 좌표 4개를 확인하며 정상인지 체크
// map[n][n][2]로 각 좌표의 기둥,보가 존재하는지 체크

// 만약 (x,y)에 기둥을 설치한다면
// -> y가 0이면 지면 -> 바로 설치
// -> x,y-1에 기둥이 존재하는지 -> 바로 설치
// -> x,y에 보가 존재하는지 -> 바로 설치
// -> x-1,y에 보가 존재하는지 -> 바로 설치
// -> 아니면 무시

// 만약 (x,y)에 보를 설치한다면
// -> x,y-1에 기둥이 존재하는지 -> 바로 설치
// -> x+1,y-1에 기둥이 존재하는지 -> 바로 설치
// -> x-1,y과 x+1,y에 동시에 보가 존재하는지 -> 바로 설치
// -> 아니면 무시

// 만약 (1,1)에 보나 기둥을 삭제한다면
// 위의 설치 가능 여부를 1,1를 제거한 후 사방으로 체크하며 하나라도 불가 뜨면 무시

class Solution {
    private int[][][] map;
    private int n;
    
    private boolean columnIsPossible(int x, int y) {
        if(y==0) return true;
        if(y>0&&map[x][y-1][0]==1) return true;
        if(map[x][y][1]==1) return true;
        if(x>0&&map[x-1][y][1]==1) return true;
        return false;
    }
    private boolean beamIsPossible(int x, int y) {
        if(y>0&&map[x][y-1][0]==1) return true;
        if(x<n&&y>0&&map[x+1][y-1][0]==1) return true;
        if(x>0&&x<n&&map[x-1][y][1]==1&&map[x+1][y][1]==1) return true;
        return false;
    }
    private boolean isAllValid() {
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                if (map[i][j][0] == 1 && !columnIsPossible(i, j)) return false;
                if (map[i][j][1] == 1 && !beamIsPossible(i, j)) return false;
            }
        }
        return true;
    }

    public int[][] solution(int n, int[][] build_frame) {
        this.n = n;
        map = new int[n+1][n+1][2]; // [0]은 기둥, [1]은 보, 0은 없음, 1은 있음
        int cnt = 0;

        for(int[] order : build_frame) {
            int x = order[0], y = order[1], a = order[2], b = order[3];
            if(b==1) { // 설치
                map[x][y][a] = 1;
                if(!isAllValid()) {
                    map[x][y][a] = 0;
                }else {
                    cnt++;
                }
            } else { // 삭제
                map[x][y][a] = 0;
                if(!isAllValid()) {
                    map[x][y][a] = 1;
                }else {
                    cnt--;
                }
            }
        }
        int[][] answer = new int[cnt][3];
        int idx = 0;
        for(int i = 0 ; i <= n ; i++) {
            for(int j = 0 ; j <= n ; j++) {
                if(map[i][j][0]==1) answer[idx++] = new int[]{i,j,0};
                if(map[i][j][1]==1) answer[idx++] = new int[]{i,j,1};
                
            }
        }
        // Arrays.sort(answer,(a,b)->{
        //     if(a[0]==b[0]) {
        //         if(a[1]==b[1]) {
        //             return Integer.compare(a[2],b[3]);
        //         }else return Integer.compare(a[1], b[1]);
        //     } else return Integer.compare(a[0],b[0]);
        // });
        return answer;
    }
    // public static void main(String[] args) {
    //     Solution sol = new Solution();
    //     int n = 5;
    //     int[][] build_frame = {{1,0,0,1},{1,1,1,1},{2,1,0,1},{2,2,1,1},{5,0,0,1},{5,1,0,1},{4,2,1,1},{3,2,1,1}};
    //     int[][] result = sol.solution(n, build_frame);
    //     for(int[] arr : result) {
    //         System.out.println(arr[0]+", "+arr[1]+", "+arr[2]);
    //     }
    // }
}