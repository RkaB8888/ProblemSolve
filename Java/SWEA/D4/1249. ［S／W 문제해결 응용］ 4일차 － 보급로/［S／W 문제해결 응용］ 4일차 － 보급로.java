import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
	static int N, map[][];
	static final int[] dr = { -1, 1, 0, 0 };
	static final int[] dc = { 0, 0, -1, 1 };
	
	static class Node{
		int i,j,w;
		Node next,pre;

		public Node(int i, int j, int w, Node next, Node pre) {
			super();
			this.i = i;
			this.j = j;
			this.w = w;
			this.next = next;
			this.pre = pre;
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				String temp = br.readLine();
				for (int j = 0; j < N; j++) {
					map[i][j] = temp.charAt(j) - '0';
				}
			}
			sb.append('#').append(tc).append(' ').append(getMinDistance(0, 0, N - 1, N - 1)).append('\n');
		}
		System.out.println(sb);
	}

	static int getMinDistance(int startI, int startJ, int endI, int endJ) {
		int[][] minDistance = new int[N][N];// 시작 정점에서 자신으로의 최소 거리
		boolean[][] visited = new boolean[N][N];// 방문한 정점 관리
		final int INF = Integer.MAX_VALUE;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				minDistance[i][j] = INF;
			}
		}
		minDistance[startI][startJ] = 0;
		Node nodes = new Node(startI,startJ,0,null,null);
		
		while (nodes!=null) {
			Node node = null;
			int minW = INF;
			for(Node temp = nodes ; temp!=null ; temp = temp.next) {
				if(!visited[temp.i][temp.j]&&temp.w<minW) {
					node = temp;
					minW=temp.w;
				}
			}
			if(node==null)break;
			
			// 리스트에서 노드 제거
	        if (node.pre != null) node.pre.next = node.next;
	        if (node.next != null) node.next.pre = node.pre;
	        if (nodes == node) nodes = node.next; // 헤드 노드 업데이트
	        
			visited[node.i][node.j] = true;
			
			if (node.i == endI && node.j == endJ)
				return node.w;
			
			for (int i = 0; i < 4; i++) {
				int nr = node.i + dr[i];
				int nc = node.j + dc[i];
				if (nr >= 0 && nr < N && nc >= 0 && nc < N && !visited[nr][nc]
						&& minDistance[nr][nc] > node.w + map[nr][nc]) {
					minDistance[nr][nc] = node.w + map[nr][nc];
					nodes = new Node(nr,nc,minDistance[nr][nc],nodes,null);
					if(nodes.next!=null) nodes.next.pre = nodes;
				}
			}
		}
		return -1;
	}

}