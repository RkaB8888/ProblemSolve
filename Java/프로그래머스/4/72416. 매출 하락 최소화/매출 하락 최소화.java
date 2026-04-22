import java.io.*;
import java.util.*;

/**
 * @description BFS + TreeDP
 * @performance 메모리: ? KB, 동작시간: ? ms
 */

// 자기 매출, 자식 번호
// 자신이 선택되면 자신의 자식들과 부모의 자식들에게 영향을 줌
// 자식부터 올라가면서 트리를 쌓아감
// 자신이 선택 됐을 때 -> 자식의 선택 유무는 상관없음
// 선택되지 않았을 때 -> 자식들 중 하나는 선택되어야 함(자식이 참가했을 때와 참가하지 않았을 때의 비용차이 확인)

class Solution {
    static class Node{
        int s; // 매출액
		int link; // 자식 링크 인덱스
        
		{
			this.s = 0;
			this.link = -1;
		}
    }

	// sales: 직원들의 하루평균 매출액 값을 담은 배열
	// links: 직원들의 팀장-팀원 의 관계를 나타내는 2차원 배열
    public int solution(int[] sales, int[][] links) {
		int len = sales.length+1;
		Node[] nodes = new Node[len];
		for(int i = 0 ; i < len-1 ; i++) {
			nodes[i+1] = new Node();
			nodes[i+1].s = sales[i];
		}

		int[] next, child;
		next = new int[links.length];
		child = new int[links.length];
		for(int i = 0 ; i < links.length ; i++) {
			int a, b;
			a = links[i][0];
			b = links[i][1];
			next[i] = nodes[a].link;
			nodes[a].link = i;
			child[i] = b;
		}

		int[] arr = new int[len];
		int arrIdx = 0;
		int[] q = new int[len];
		int front=0, rear=0;
		q[rear++] = 1;

		while(front<rear){
			int cur = q[front++];
			arr[arrIdx++] = cur;
			for(int ni = nodes[cur].link ; ni!=-1 ; ni = next[ni]) {
				q[rear++] = child[ni];
			}
		}

		int[][] dp = new int[len][2]; // [0]: 자신이 참석, [1]: 불참석
		for(int i = arrIdx-1 ; i>=0 ; i--) {
			int nodeNum = arr[i];
			if(nodes[nodeNum].link==-1) {
				dp[nodeNum][0] = nodes[nodeNum].s;
				dp[nodeNum][1] = 0;
				continue;
			}

			int minDiff = Integer.MAX_VALUE;
			dp[nodeNum][0] = nodes[nodeNum].s;
			dp[nodeNum][1] = 0;
			boolean childAttend = false;
			// 자신이 참석하는 경우 -> 자식이 참석하던 말던 최소 값을 합함
			// 자신이 불참하는 경우 -> (자식 모두 불참석이 더 이득인 경우)자식이 불참하는 걸로 다 합한 뒤 참석했을 때 차이가 작은 걸 더해줌
			for(int ni = nodes[nodeNum].link ; ni!=-1 ; ni = next[ni]) {
				int cost = Math.min(dp[child[ni]][0],dp[child[ni]][1]);
				dp[nodeNum][0] += cost;
				dp[nodeNum][1] += cost;
				if(dp[child[ni]][0]<dp[child[ni]][1]) {// 자식 중 참석이 이득인 경우
					childAttend = true;
				}else {
					minDiff = Math.min(minDiff,dp[child[ni]][0]-dp[child[ni]][1]);
				}
			}
			if(!childAttend)
				dp[nodeNum][1]+=minDiff;
		}

        return Math.min(dp[1][0],dp[1][1]);
    }
}