import java.io.*;
import java.util.*;

/**
 * @description 유니온 파인드
 */
 
// 유니온 파인드 or BFS
// BFS: 배열 순회 n^2 + 타고 넘어가는 간선 e 
// 유니온 파인드:  배열 순회 n^2 + 집합 설정 n
class Solution {

    static class UnionFind{
        int[] gn;
        int[] depth;
        int gCnt;
        UnionFind(int len){
            gn = new int[len];
            for(int i = 0 ; i < len ; i++) {
                gn[i] = i;
            }
            depth = new int[len];
            gCnt = len;
        }
        void set(int a, int b){
            int gnA = this.find(a);
            int gnB = this.find(b);
            if(gnA==gnB) return;
            if(depth[gnA]<depth[gnB]) {
                gn[gnA] = gnB;
            }else {
                gn[gnB] = gnA;
                if(depth[gnA]==depth[gnB]) depth[gnA]++;
            }
            gCnt--;
        }
        int find(int a){
            if(gn[a]==a) return a;
            return gn[a] = this.find(gn[a]);
        }
    }
    public int solution(int n, int[][] computers) {
        if(n==1) return 1;
        UnionFind uf = new UnionFind(n);
        for(int i = 0 ; i < n ; i++) {
            for(int j = i+1 ; j < n ; j++) {
                if(computers[i][j]==1)
                    uf.set(i,j);
            }
        }
        return uf.gCnt;
    }
}