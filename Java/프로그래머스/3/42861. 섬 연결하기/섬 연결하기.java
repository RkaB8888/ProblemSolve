import java.io.*;
import java.util.*;

/**
 * @description Kruskal + Union-Find
 */

// 간선을 비용 오름차순으로 정렬
// 가져온 간선이 연결하는 두 노드가 서로 같은 집합이면 버림
// 모든 간선을 순환할지? -> O(n^2) / 가장 큰 집합의 크기가 n인지 확인할지?

// n은 1이상 100이하
// cost 길이는 ((n-1) * n) / 2이하
// 연결할 수 없는 섬은 없음
class Solution {
    static class Union{
        int[] group;

        Union(int len){
            this.group = new int[len];
            for(int i = 0 ; i < len ; i++) {
                this.group[i] = i;
            }
        }
        boolean set(int v1, int v2) {
            int g1 = find(v1);
            int g2 = find(v2);
            if(g1==g2) return false;
            group[g2] = g1;
            return true;
        }
        int find(int v) {
            if(group[v]==v) return v;
            return group[v] = find(group[v]);
        }
    }
    public int solution(int n, int[][] costs) {
        Arrays.sort(costs,(a,b)->Integer.compare(a[2],b[2]));
        Union union = new Union(n);
        int answer = 0;
        int cnt = 0;
        for(int[] u : costs) {
            int v1 = u[0];
            int v2 = u[1];
            int cost = u[2];
            if(union.set(v1,v2)) {
                answer+=cost;
                cnt++;
                if(cnt == n-1) break;
            }
        }
        return answer;
    }
}