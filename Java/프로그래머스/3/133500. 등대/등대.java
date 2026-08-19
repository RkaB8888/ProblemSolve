import java.io.*;
import java.util.*;

/**
 * @description TreeDP + BFS
 */
 
// n은 2이상 100,000이하
// 등대 경로의 갯수는 n-1
// 각 등대의 번호는 1이상 n이하

// 트리 구조에서 부모 자식 간의 ON/OFF 관리해야 함
// 유사한 문제에서 트리를 만들고 해당 트리의 자식부터 DP형태로 켜졌을 때/꺼졌을 때를 관리한 것 같음
// 1. 일단 트리 구조를 형성하고
// 2. 리프 노드를 모아두고 루트 방향으로 진행
// 3. 우선 리프 노드는 켰을 때 0, 껐을 때 0으로 초기화 해둠
// 4. 그 부모 노드에서 봤을 때 
// 4-1. 켰을 때 = 모든 자식들의 켰을 때/껐을 때의 최소 값만 합한 값
// 4-2. 껐을 때 = 모든 자식들의 켰을 때를 합한 값
// 5. 최종적으로 루트 노드의 켰을 때/껐을 때의 최소 값을 반환한다
class Solution {

    public int solution(int n, int[][] lighthouse) {
        int[] link = new int[n];
        Arrays.fill(link,-1);
        int[] next = new int[n<<1];
        int[] node = new int[n<<1];
        for(int i = 0 ; i < lighthouse.length ; i++) {
            int a = lighthouse[i][0]-1;
            int b = lighthouse[i][1]-1;

            int idx1 = i<<1;
            int idx2 = idx1+1;

            next[idx1] = link[a];
            link[a] = idx1;
            node[idx1] = b;

            next[idx2] = link[b];
            link[b] = idx2;
            node[idx2] = a;
        }
        
        int[] parent = new int[n];
        int[] q = new int[n];
        boolean[] visited = new boolean[n];

        int front = 0, rear = 0;
        parent[0] = -1;
        q[rear++] = 0;
        visited[0] = true;
        while(front<rear) {
            int curNode = q[front++];
            for(int e = link[curNode] ; e != -1 ; e = next[e]) {
                int nextNode = node[e];
                if(!visited[nextNode]) {
                    visited[nextNode] = true;
                    parent[nextNode] = curNode;
                    q[rear++] = nextNode;
                }
            }
        }

        int[] dpOff = new int[n];
        int[] dpOn = new int[n];
        for(int i = n-1 ; i >= 0 ; i--) {
            int curNode = q[i];
            int parentNode = parent[curNode];
            dpOn[curNode]++;

            if(parentNode!=-1) {
                dpOff[parentNode] += dpOn[curNode];
                dpOn[parentNode] += Math.min(dpOff[curNode],dpOn[curNode]);
            }
        }
        return Math.min(dpOff[0],dpOn[0]);
    }
}