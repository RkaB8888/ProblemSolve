import java.io.*;
import java.util.*;

/**
 * @description 크루스칼 + 유니온 파인드
 */

// 각 칸마다 주변 칸을 검사하며 사다리가 필요없는 칸끼리 그룹 지정
// 사다리가 필요한 경우 간선 목록에 저장
// 간선들을 비용 오름차순으로 정렬 후 하나씩 그룹 결합

// 시간복잡도: N^2(초기 land 순회) log N(집합 처리)
class Solution {
    static class Union{
        int[] group;
        Union(int len){
            this.group = new int[len];
            for(int i = 0 ; i < len ; i++) {
                group[i] = i;
            }
        }
        int find(int v){
            if(group[v]==v) return v;
            return group[v]=find(group[v]);
        }
        boolean set(int v1, int v2){
            int g1 = find(v1);
            int g2 = find(v2);
            if(g1==g2) return false;
            group[g2] = g1;
            return true;
        }
    }
    static class Edge implements Comparable<Edge>{
        int v1, v2, val;
        Edge(int v1, int v2, int val){
            this.v1 = v1;
            this.v2 = v2;
            this.val = val;
        }
        @Override
        public int compareTo(Edge o){
            return Integer.compare(this.val, o.val);
        }
    }
    public int solution(int[][] land, int height) {
        int n = land.length;
        Union u1 = new Union(n*n);
        List<Edge> edges = new ArrayList<>();

        int[] dr = {1,0};
        int[] dc = {0,1};

        for(int i = 0 ; i < n ; i++) {
            for(int j = 0 ; j < n ; j++) {
                int cur = i*n+j;
                for(int d = 0 ; d < 2 ; d++) {
                    int ni = i + dr[d];
                    int nj = j + dc[d];
                    if(ni<n && nj<n) {
                        int next = ni*n+nj;
                        int diff = Math.abs(land[i][j] - land[ni][nj]);

                        if(diff <= height) {
                            u1.set(cur,next);
                        }else {
                            edges.add(new Edge(cur, next, diff));
                        }
                    }
                }
            }
        }
        Collections.sort(edges);

        int answer = 0;
        for(Edge e : edges) {
            if(u1.set(e.v1, e.v2)) {
                answer+=e.val;
            }
        }
        return answer;
    }
    // public static void main(String[] args) {
    //     int[][] land = {{10, 11, 10, 11},{2, 21, 20, 10},{1, 20, 21, 11},{2, 1, 2, 1}};
    //     int height = 1;
    //     Solution sol = new Solution();
    //     System.out.println(sol.solution(land, height));
    // }
}