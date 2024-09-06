import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 메모리 91,304 kb 실행시간 1,765 ms
 * 중복된 탐색을 하지 않도록 최적화
 */
public class Solution {
	static int N, M, result, adjMatrix[][], cnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			N = Integer.parseInt(br.readLine());
			M = Integer.parseInt(br.readLine());

			adjMatrix = new int[N + 1][N + 1]; // 학생번호 1번부터 시작

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				adjMatrix[a][b] = 1;
			}
			
			for(int i = 1 ; i <= N ; i++) {
				adjMatrix[i][0] = -1; // 탐색되지 않은 학생을 나타냄(후에 탐색이 완료되면 자신보다 큰 학생 수 저장)
			}
			
			result = 0;
			for (int i = 1; i <= N; i++) {
				if(adjMatrix[i][0] != -1) continue;
				gtDFS(i);
			}
			for(int i = 1 ; i <= N ; i++) {
				for(int j = 1 ; j <= N ; j++) {
					adjMatrix[0][j]+=adjMatrix[i][j];
				}
			}
			for(int i = 1 ; i <=N ; i++) {
				if(adjMatrix[i][0]+adjMatrix[0][i]==N-1) result++;
			}
			sb.append('#').append(tc).append(' ').append(result).append('\n');
		}
		System.out.println(sb);
	}

	public static void gtDFS(int cur) {// 나보다 큰 녀석의 수를 찾는다.
		for (int i = 1; i <= N; i++) {
			if(adjMatrix[cur][i] == 0) continue;
			if(adjMatrix[i][0]==-1) {
				gtDFS(i);
			}
			
			// 나보다 키가 큰 학생이 탐색을 완료한 상태
			// i보다 키가 큰 학생이 있다면 그 학생들의 정보를 cur에게 반영(간접 관계 직접 관계로 경로압축)
			if(adjMatrix[i][0]>0) {
				for(int j = 1 ; j <= N ; j++) {
					if(adjMatrix[i][j]!=0) adjMatrix[cur][j] = 1;
				}
			}
		}
		adjMatrix[cur][0] = 0; // 초기값이 -1 이므로 누적 위해 0으로 초기화
		for(int k = 1; k <= N ; k++) {
			adjMatrix[cur][0]+=adjMatrix[cur][k];
		}
	}
}