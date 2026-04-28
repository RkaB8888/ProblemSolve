import java.io.*;
import java.util.*;

/**
 * @description BFS
 */

// n: 노드의 개수 2이상 20,000이하
// edge: 간선의 개수 1이상 50,000이하 [간선번호][2]
// bfs로 1번 노드부터 모든 노드 방문 체크하며 건너간 횟수 입력 (가장 먼 거리 저장)
// visited 확인하여 가장 먼 거리인 경우 카운팅
// 시간복잡도 n + edge
class Solution {
    public int solution(int n, int[][] edge) {
        int len = edge.length;
        int[] link = new int[n+1];
        Arrays.fill(link,-1);
        int[] next = new int[len<<1];
        int[] v = new int[len<<1];

        for(int i = 0 ; i < len ; i++) {
            int a = edge[i][0];
            int b = edge[i][1];

            int e1 = i<<1;
            int e2 = e1+1;

            next[e1] = link[a];
            link[a] = e1;
            v[e1] = b;

            next[e2] = link[b];
            link[b] = e2;
            v[e2] = a;
        }
        boolean[] visited = new boolean[n+1];
        int[] q = new int[n];
        int front = 0, rear = 0;
        q[rear++] = 1;
        visited[1] = true;
        int answer = 0;
        while(front<rear) {
            int cnt = answer = rear-front;
            while(cnt-- > 0) {
                int curNode = q[front++];
                for(int e = link[curNode] ; e != -1 ; e = next[e]) {
                    int nextNode = v[e];
                    if(visited[nextNode]) continue;
                    visited[nextNode] = true;
                    q[rear++] = nextNode;
                }
            }
        }
        return answer;
    }
}