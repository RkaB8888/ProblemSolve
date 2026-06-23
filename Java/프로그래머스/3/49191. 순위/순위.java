import java.io.*;
import java.util.*;

/**
 * @description BFS + ArrayDeque
 */
 
// 단방향 그래프 생성 후
// 순방향 이동 시 방문하는 노드의 갯수 + 역방향 이동 시 방문하는 노드의 갯수 = n-1이 되면
// 순위를 매길 수 있음 +1

// n은 최대 100, 간선은 최대 4500
// 시간 복잡도 100 * (100+4500) (각 노드마다 연결된 간선이 최악 4500개인 경우)
class Solution {
    public int solution(int n, int[][] results) {
        int[] next = new int[n+1];
        Arrays.fill(next,-1);
        int[] link = new int[results.length];
        int[] val = new int[results.length];

        int[] rnext = new int[n+1];
        Arrays.fill(rnext,-1);
        int[] rlink = new int[results.length];
        int[] rval = new int[results.length];

        for(int i = 0 ; i < results.length ; i++) {
            int a = results[i][0];
            int b = results[i][1];

            // a -> b로 가는 순방향 간선
            link[i] = next[a];
            next[a] = i;
            val[i] = b;

            // b -> a로 가는 역방향 간선
            rlink[i] = rnext[b];
            rnext[b] = i;
            rval[i] = a;
        }

        int answer = 0;
        int[] q = new int[n+1];
        boolean[] visited = new boolean[n+1];
        for(int i = 1 ; i <= n ; i++) {
            int cnt = 1;
            Arrays.fill(visited,false);
            visited[i] = true;

            // 순방향 노드 갯수
            int front = 0, rear = 0;
            q[rear++] = i;
            while(front<rear) {
                int curV = q[front++];
                for(int e = next[curV] ; e != -1 ; e = link[e]) {
                    int nextV = val[e];
                    if(visited[nextV]) continue;
                    visited[nextV] = true;
                    cnt++;
                    q[rear++] = nextV;
                }
            }

            // 역방향 노드 갯수
            q[rear++] = i;
            while(front<rear) {
                int curV = q[front++];
                for(int e = rnext[curV] ; e != -1 ; e = rlink[e]) {
                    int nextV = rval[e];
                    if(visited[nextV]) continue;
                    visited[nextV] = true;
                    cnt++;
                    q[rear++] = nextV;
                }
            }

            // 모든 노드와의 비교가 가능하다면
            if(cnt==n) answer++;
        }
        return answer;
    }
}