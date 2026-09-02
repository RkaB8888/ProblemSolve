import java.io.*;
import java.util.*;

/**
 * @description 이 클래스에 대한 동작 설명
 */
 
// key의 크기는 최소 3*3 최대 20*20 이차원 정사각배열
// kock은 key와 제한이 같음. 단 key보다는 크거나 같음
// 0은 홈, 1은 돌기
// 완전탐색의 경우 39*39(겹치는 위치) * 400(비교 최악) * 4(회전) = 2433600
// 겹치는 위치에 대한 비교 전에 해당 영역의 홈 갯수를 서로 비교해서 같은 경우만 하면 최적화 가능할 듯?

class Solution {
    private int[][] rotateKey(int[][] key){
        int m = key.length;
        int[][] result = new int[m][m];
        for(int i = 0 ; i < m ; i++) {
            for(int j = 0 ; j < m ; j++) {
                result[j][m-i-1] = key[i][j];
            }
        }
        return result;

    }
    public boolean solution(int[][] key, int[][] lock) {
        int holeCnt = 0;
        int keyCnt = 0;
        int m = key.length;
        int n = lock.length;
        for(int i = 0 ; i < n ; i++) {
            for(int j = 0 ; j < n ; j++) {
                if(lock[i][j]==0) holeCnt++;
            }
        }
        // 키 없이 자물쇠 열리는 경우
        if(holeCnt == 0) return true;
        for(int i = 0 ; i < m ; i++) {
            for(int j = 0 ; j < m ; j++) {
                if(key[i][j]==1) keyCnt++;
            }
        }
        // 자물쇠의 구멍을 키가 전부 커버 못하는 경우
        if(keyCnt < holeCnt) return false;

        int len = n+(m<<1)-2;
        int[][] map = new int[len][len];
        for(int i = 0 ; i < n ; i++) {
            for(int j = 0 ; j < n ; j++) {
                map[i+m-1][j+m-1] = lock[i][j];
            }
        }

        for(int i = 0 ; i < 4 ; i++) { // 회전 횟수
            for(int j1 = 0 ; j1 <= len - m ; j1++) { // 겹치는 영역 시작 행
                for(int j2 = 0 ; j2 <= len - m ; j2++) { // 겹치는 영역 시작 열
                    boolean flag = false;
                    int checkCnt = 0;
                    for(int k1 = 0 ; k1 < m ; k1++) { // 열쇠와 비교
                        if((j1+k1<m-1)||(j1+k1)>len-m) continue;
                        for(int k2 = 0 ; k2 < m ; k2++) {
                            if((j2+k2<m-1)||(j2+k2)>len-m) continue;
                            int mapV = map[j1+k1][j2+k2];
                            int keyV = key[k1][k2];
                            if(mapV == 0) {
                                if(keyV==0) {
                                    flag = true;
                                    break;
                                }
                                checkCnt++;
                            } else {
                                if(keyV == 1) {
                                    flag = true;
                                    break;
                                }
                            }
                        }
                        if(flag) break;
                    }
                    if(!flag&&checkCnt==holeCnt) return true;
                }
            }
            key = rotateKey(key);
        }
        return false;
    }
}