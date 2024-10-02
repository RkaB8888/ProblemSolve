import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 메모리:?KB, 시간:?ms N/2개만 선택해서 그룹을 지정한다. 해당 그룹내의 요소들이 서로 연결되어 있는지 확인한다. 연결 되어 있다면
 * 인구 최솟값을 계산한다.
 */
public class Main {
	static int N, pop[], adjMatrix[][], result = Integer.MAX_VALUE;
	static boolean[] group;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		pop = new int[N+1];
		adjMatrix = new int[N+1][N+1];
		group = new boolean[N+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			pop[i] = Integer.parseInt(st.nextToken());
		}
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1, jEnd = Integer.parseInt(st.nextToken()); j <= jEnd; j++) {
				int num = Integer.parseInt(st.nextToken());
				adjMatrix[i][num] = 1;
				adjMatrix[num][i] = 1;
			}
		}
		////// 입력 끝///////
		for (int i = 1; i <= N / 2; i++) {
			dfs(0, 0, i);
		}
		if(result==Integer.MAX_VALUE) result = -1;
		System.out.println(result);
	}

	private static void dfs(int str, int cnt, int goal) {//시작, 뽑은 개수, 뽑을 개수
		if(cnt==goal) {
			linkCheck(goal);
			return;
		}
		for(int i = str ; i <= N ; i++) {
			group[i] = true;
			dfs(i+1,cnt+1, goal);
			group[i] = false;
		}
	}

	private static void linkCheck(int trueN) {
		int falseN = N-trueN, truePop = 0, falsePop = 0;
		boolean[] visited = new boolean[N+1];
		
		int trueCnt = 0;
		for(int i = 1 ; i <= N ; i++) {
			if(group[i]&&!visited[i]) {
				trueCnt = bfs(i, visited, true);
				if(trueCnt!=trueN) return;
				break;
			}
		}
		int falseCnt = 0;
		for(int i = 1 ; i <= N ; i++) {
			if(!group[i]&&!visited[i]) {
				falseCnt = bfs(i, visited, false);
				if(falseCnt!=falseN) return;
				break;
			}
		}
		for (int i = 1; i <= N; i++) {
	        if (group[i]) truePop += pop[i];
	        else falsePop += pop[i];
	    }
	    result = Math.min(result, Math.abs(falsePop - truePop));
	}
	private static int bfs(int start, boolean[] visited, boolean isTrueGroup) {
	    int cnt = 0;
	    Queue<Integer> q = new ArrayDeque<>();
	    q.add(start);
	    visited[start] = true;
	    cnt++;

	    while (!q.isEmpty()) {
	        int curNode = q.poll();
	        for (int next = 1; next <= N; next++) {
	            if (adjMatrix[curNode][next] == 1 && !visited[next] && group[next] == isTrueGroup) {
	                q.add(next);
	                visited[next] = true;
	                cnt++;
	            }
	        }
	    }
	    return cnt;  // 연결된 노드 수 리턴
	}
}