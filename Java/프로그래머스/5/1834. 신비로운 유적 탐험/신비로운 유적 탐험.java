import java.io.*;
import java.util.*;

/**
 * @description TreeDP + 헝가리안 알고리즘 O(N^3) 최적화
 */

// 그래프 구성 후 트리 구성
// -> 입력을 바탕으로 양방향 그래프 생성 후 1번 노드를 루트로 트리 생성
// 두 트리의 최대 공통 갯수 구하기
// -> Top-Down 방식의 DP로 자식들마다 헝가리안 이분 매칭 시도
 
class Solution {
    private List<Integer>[] tree1;
    private List<Integer>[] tree2;
    private int[][] dp;

    private List<Integer>[] buildTree(int n, int[][] g) {
        List<Integer>[] graph = new ArrayList[n+1];
        List<Integer>[] tree = new ArrayList[n+1];
        for(int i = 0 ; i <= n ; i++) {
            graph[i] = new ArrayList<>();
            tree[i] = new ArrayList<>();
        }
        for(int[] i : g) {
            graph[i[0]].add(i[1]);
            graph[i[1]].add(i[0]);
        }
        boolean[] visited = new boolean[n+1];
        Queue<Integer> q = new ArrayDeque();
        q.add(1);
        visited[1] = true;
        while(!q.isEmpty()) {
            int parent = q.poll();
            for(int child : graph[parent]){
                if(!visited[child]) {
                    visited[child] = true;
                    tree[parent].add(child);
                    q.add(child);
                }
            }
        }
        return tree;
    }

    private int getDP(int u, int v) {
        if(dp[u][v]>0) return dp[u][v];
        List<Integer> leftTree = tree1[u];
        List<Integer> rightTree = tree2[v];
        if(tree1[u].isEmpty()||tree2[v].isEmpty()) return dp[u][v]=1;

        int len = Math.max(leftTree.size(),rightTree.size());
        int[][] matrix = new int[len][len];
        for(int i = 0 ; i < len ; i++) {
            for(int j = 0 ; j < len ; j++) {
                if(i<leftTree.size()&&j<rightTree.size()) {
                    matrix[i][j] = getDP(leftTree.get(i),rightTree.get(j));
                } else {
                    matrix[i][j] = 0;
                }
            }
        }
        Hungarian h = new Hungarian(matrix);
        return dp[u][v] = h.getVal()+1;
    }
    public int solution(int n1, int[][] g1, int n2, int[][] g2) {
        tree1 = buildTree(n1, g1);
        tree2 = buildTree(n2, g2);
        dp = new int[n1+1][n2+1];
        return getDP(1,1);
    }

    static class Hungarian{
        private int n;
        private int[][] weight;
        private int[] u, v, match, slack, pre;
        private boolean[] checkV;

        public Hungarian(int[][] matrix){
            this.n = matrix.length;
            this.weight = new int[n+1][n+1];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    this.weight[i + 1][j + 1] = matrix[i][j];
                }
            }
            this.u = new int[n+1];
            this.v = new int[n+1];
            this.match = new int[n+1];
            this.slack = new int[n+1];
            this.pre = new int[n+1];
            this.checkV = new boolean[n+1];
        }

        private int getVal(){
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    u[i] = Math.max(u[i], weight[i][j]);
                }
            }
            for(int i = 1 ; i <= n ; i++) { // 이사할 사람(i) 투입
                match[0] = i; // i를 0번 방에 준비
                int curR = 0; // i의 현재 위치는 0번
                Arrays.fill(slack,Integer.MAX_VALUE);
                Arrays.fill(checkV, false);

                while(match[curR] != 0) { // 이사할 사람이 있는가?
                    checkV[curR] = true; // 이 방은 교체된 적 있다는 표시
                    int curL = match[curR]; // 현재 방의 주인 (이사할 예정)
                    int diff = Integer.MAX_VALUE; // nextR 갱신을 위한 초기화
                    int nextR = 0;

                    for(int j = 1 ; j <= n ; j++) { // 다른 방 찾기
                        if(!checkV[j]) { // 변경된 적이 없는 방
                            int gap = u[curL] + v[j] - weight[curL][j];
                            if(gap<slack[j]) { // 각 방마다 누구와 gap이 적었는지 확인
                                slack[j] = gap; // gap 갱신
                                pre[j] = curR; // 현재 주인이 가장 gap이 적어서 할당 받는다면 남는 방 curR을 저장
                            }
                            if(slack[j]<diff) { // 제일 선호되는 방 기록
                                diff = slack[j];
                                nextR = j; // 다음 방 준비
                            }
                        }
                    }

                    for(int j = 0 ; j <= n ; j++) {
                        if(checkV[j]) { // 신입 i나 방 배정 실패한 사람도 0번 방에 배정되어 있음
                            u[match[j]] -= diff; // 그래서 checkU가 없이 같은 조건문에 가능
                            v[j] += diff;
                        } else { // diff를 차감하여 가장 gap이 작은 방의 비용을 0으로 바꾸고 nextR부터 이어서 시작
                            slack[j] -= diff;
                        }
                    }
                    curR = nextR;
                }

                // curR 방의 주인이 없으면 이사를 시작함
                // pre에 curR방으로 옮길 사람이 살던 방이 담겨 있음
                while (curR != 0) {
                    int prevR = pre[curR]; // curR로 이사하기 전 방번호
                    match[curR] = match[prevR]; // curR 방에 이전 방의 주인을 옮겨줌
                    curR = prevR;
                }
            }

            int sumWeight = 0;
            for(int i = 1 ; i <= n ; i++) {
                sumWeight += weight[match[i]][i];
            }
            return sumWeight;
        }
    }
}