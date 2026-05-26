import java.io.*;
import java.util.*;

/**
 * @description TreeDP + 헝가리안 알고리즘
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
        private int[] u, v, match, slack;
        private boolean[] checkU, checkV;

        public Hungarian(int[][] weight){
            this.n = weight.length;
            this.weight = weight;
            this.u = new int[n];
            this.v = new int[n];
            this.match = new int[n];
            this.slack = new int[n];
            this.checkU = new boolean[n];
            this.checkV = new boolean[n];

            Arrays.fill(match,-1);
        }
        private boolean dfs(int row){
            checkU[row] = true;
            for(int col = 0 ; col < n ; col++) {
                if(checkV[col]) continue;
                int diff = u[row] + v[col] - weight[row][col];
                if(diff==0) {
                    checkV[col] = true;
                    if(match[col]==-1||dfs(match[col])) {
                        match[col] = row;
                        return true;
                    }
                } else {
                    slack[col] = Math.min(slack[col],diff);
                }
            }
            return false;
        }
        private void update(){
            int diff = Integer.MAX_VALUE;
            for(int i = 0 ; i < n ; i++) {
                if(!checkV[i]) diff = Math.min(diff, slack[i]);
            }
            for(int i = 0 ; i < n ; i++) {
                if(checkU[i]) u[i] -= diff;
                if(checkV[i]) v[i] += diff;
            }
        }
        private int getVal(){
            for(int i = 0 ; i < n ; i++) {
                for(int j = 0 ; j < n ; j++) {
                    u[i] = Math.max(u[i],weight[i][j]);
                }
            }
            for(int i = 0 ; i < n ; i++) {
                Arrays.fill(slack,Integer.MAX_VALUE);
                while(true) {
                    Arrays.fill(checkU,false); // 행 방문 여부
                    Arrays.fill(checkV,false); // 열 방문 여부
                    if(dfs(i)) break;
                    update();
                }
            }

            int sumWeight = 0;
            for(int i = 0 ; i < n ; i++) {
                sumWeight += weight[match[i]][i];
            }
            return sumWeight;
        }
    }
}