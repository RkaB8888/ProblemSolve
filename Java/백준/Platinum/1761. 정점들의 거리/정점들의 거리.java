import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
/**
 * DFS, tree, sparse table, LCA  메모리 ? KB 시간 ? ms
 * 
 * @author python98
 */
public class Main {
	static int N, M, parentLen;
	static int[] depths;
	static long[] dists;
	static int[][] parent;
	static List<int[]>[] adjList;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		adjList = new ArrayList[N+1];
		for(int i = 0 ; i <= N ; i++) {
			adjList[i] = new ArrayList<int[]>();
		}
		
		parentLen = (int)(Math.log(N)/Math.log(2))+1;
		parent = new int[N+1][parentLen]; // 해당 노드의 1,2,4,8..번째 부모 번호 저장
		depths = new int[N+1]; // 1번 노드를 root로 봤을 때 깍 노드의 깊이 저장
		dists = new long[N+1]; // 1번 노드를 root로 봤을 때 각 노드까지의 거리 저장
		
		StringTokenizer st;
		for(int i = 1 ; i < N ; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());
			adjList[from].add(new int[]{to,dist});
			adjList[to].add(new int[]{from,dist});
		}
		
		depths[0] = -1;
		makeTree(1,0); // dfs(curNode, preNode, dist)
		setParent();
		
		M = Integer.parseInt(br.readLine());
		for(int i = 0 ; i < M ; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			sb.append(query(from, to)).append('\n');
		}
		System.out.print(sb);
	}
	
	private static long query(int node1, int node2) {
		if(depths[node1]>depths[node2]) {
			int temp = node1;
			node1 = node2;
			node2 = temp;
		}
		
		int lca = findCommonNode(node1,findEqualDepthNode(node1, node2));
		
//		System.out.println("node1 : "+node1+", node2 : "+node2+", lca : "+lca);
		return dists[node1]+dists[node2] - 2*dists[lca];
	}
	
	private static int findCommonNode(int node1, int node2) {
		if(node1==node2) return node1;
		for(int i = parentLen-1 ; i >= 0 ; i--) {
			if(parent[node1][i]!=parent[node2][i]) {
				node1 = parent[node1][i];
				node2 = parent[node2][i];
			}
		}
		return parent[node1][0];
	}

	private static int findEqualDepthNode(int node1, int node2){
		int diff = depths[node2]-depths[node1];
		for(int i = 0 ; diff > 0 ; i++) {
			if((diff&1) != 0) {
				node2 = parent[node2][i];
			}
			diff>>=1;
		}
		return node2;
	}
	
	private static void setParent() {
		for(int j = 1 ; j < parentLen ; j++) {
			for(int i = 1 ; i <= N ; i++) {
				if(parent[i][j-1]!=0) { // 이전 단계의 부모노드가 있다면
					parent[i][j] = parent[parent[i][j-1]][j-1];
				}
			}
		}
	}
	
	private static void makeTree(int curNode, int preNode) {
		parent[curNode][0] = preNode;
		depths[curNode] = depths[preNode]+1;
		for(int[] next : adjList[curNode]) {
			int nextNode = next[0];
			int nextDist = next[1];
			if(nextNode == preNode) continue;
			dists[nextNode] = dists[curNode]+nextDist;
			makeTree(nextNode,curNode);
		}
	}
}