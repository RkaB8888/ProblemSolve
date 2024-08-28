import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
/**
 * 메모리:20,532KB, 시간:120ms
 */
public class Solution {
	static int L; // 데이터의 길이
	static int startNodeNum;
	static int[][] adjMatrix;
	static int maxNum;
	static boolean[] Contact;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = 10;	//테스트케이스
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			L = Integer.parseInt(st.nextToken());
			L/=2;
			startNodeNum = Integer.parseInt(st.nextToken());
			Contact = new boolean[101];
			adjMatrix = new int[101][101];
			st = new StringTokenizer(br.readLine());
			for(int i = 0 ; i < L ; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				if(adjMatrix[from][to]==0)adjMatrix[from][0]++;
				adjMatrix[from][to]++;
			}
			bfs();
			sb.append('#').append(tc).append(' ').append(maxNum).append('\n');
		}
		System.out.println(sb);
	}
	public static void bfs() {
		Queue<Integer> q = new ArrayDeque<>();
		q.add(startNodeNum);
		Contact[startNodeNum] = true;
		while(!q.isEmpty()) {
			int size = q.size();
			int num = 0;
			while(--size>=0) {
				int curNode = q.poll();
				if(num<curNode) num = curNode;
				int idx = 1;
				while(adjMatrix[curNode][0]>0&&idx<101) {
					if(adjMatrix[curNode][idx]>0&&Contact[idx]==false) {//idx 노드로 길이 있다면
						Contact[idx] = true;
						q.add(idx);
						adjMatrix[curNode][idx] = 0;//지나간 간선 제거
						adjMatrix[curNode][0]--;//해당 노드의 간선 수 차감
					}
					idx++;
				}
			}
			maxNum=num;
		}
	}
}