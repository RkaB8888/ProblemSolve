import java.io.*;
import java.util.*;

/**
 * @description DP
 */
 
// 그래프를 이동하는 경로를 최소 수정하는 횟수
// 거점 개수 n, 도로 개수 m, 거점 간 연결 정보 edge_list, 이동 거점 개수 k, 도달한 거점 정보 gps_log
// n은 2이상 200이하
// m은 1이상 10,000이하
// k는 2이상 100이하
// edge_list는 m * 2 크기의 2차원 배열로 거점 번호가 들어있음
// 거점 번호는 1부터 n까지의 숫자, 무방향, 시작과 도착은 정해져 있음

// 시작 지점부터 gps_log를 따라 갈 수 있는지 dfs로 확인
// 중간에 a - b의 간선이 없다면 b를 변경하며 그 다음과 연결 가능한지 확인
// b를 변경해도 목적지에 도달할 수 없다면 a를 변경해봄
// a를 변경해도 안된다면 그 이전을 변경해봄 을 반복
// 마지막 경로에 도달하면 그동안 수정한 횟수를 반환
// dfs를 해도 도달하지 못하고 끝나면 -1 반환
// 시간복잡도는 k개의 경로마다 n개의 노드를 전부 대입하는 경우 n의 k-2승 -> 200^(98)은 시간초과

// DP[t][v]를 활용해서 t 시간에 v에 있다면 수정하는 최소 횟수를 올려보면?
// 경로는 왔던 곳을 다시 갈 수 있으니 t를 기준
// 시작 지점과 끝 지점은 고정되어 있으니 t는 1부터 보면
// DP[1][v]는 v와 연결된 모든 노드 u에 대해 DP[0][u]를 가져오고 실제 1에서의 노드가 v가 아니라면 +1 해주면?

class Solution {
    public int solution(int n, int m, int[][] edge_list, int k, int[] gps_log) {
        int[] next = new int[m<<1];
        int[] link = new int[n]; // 0 base로 변경
        Arrays.fill(link,-1);
        int[] node = new int[m<<1];

        // 간선 리스트 생성
        for(int i = 0 ; i < m ; i++) {
            int a = edge_list[i][0]-1;
            int b = edge_list[i][1]-1;
            int idx1 = i<<1;
            int idx2 = idx1+1;

            next[idx1] = link[a];
            link[a] = idx1;
            node[idx1] = b;

            next[idx2] = link[b];
            link[b] = idx2;
            node[idx2] = a;
        }

        int[][] dp = new int[k][n];
        for(int i = 0 ; i < k ; i++) {
            Arrays.fill(dp[i],100); // 어차피 최대 교체 횟수는 최대 98임 (시작,끝 제외)
        }
        dp[0][gps_log[0]-1] = 0; // 시작지점 초기화

        for(int i = 1 ; i < k ; i++) {
            for(int v = 0 ; v < n ; v++) {
                int cnt = dp[i][v];
                for(int edge = link[v] ; edge != -1 ; edge = next[edge]) {
                    int u = node[edge];
                    if(gps_log[i]-1==v) cnt = Math.min(cnt,dp[i-1][u]);
                    else cnt = Math.min(cnt,dp[i-1][u]+1);

                }
                // 멈춰있는 경우도 고려
                if(gps_log[i]-1==v) cnt = Math.min(cnt,dp[i-1][v]);
                else cnt = Math.min(cnt, dp[i-1][v]+1);
                dp[i][v] = cnt;
            }
        }
        
        return dp[k-1][gps_log[k-1]-1]==100?-1:dp[k-1][gps_log[k-1]-1];
    }
}