import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * 메모리 ? KB 시간 ? ms
 */
public class Main {
	static int M,N, map[][],cntMap[][], drdc[][] = {{-1,0},{1,0},{0,-1},{0,1}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new int[M][N];
		cntMap = new int[M][N];
		for(int i = 0 ; i < M ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j< N ; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dfs(map[0][0],0,0);
		System.out.print(cntMap[0][0]==-1?0:cntMap[0][0]);
	}
	private static boolean dfs(int num, int R, int C) {
		if(cntMap[R][C]>0) {//이미 끝까지 갔던 길
			return true;
		}
		if(cntMap[R][C]==-1) {//이미 끝까지 갔던 길
			return false;
		}
		if(R==M-1&&C==N-1) {
			cntMap[R][C] = 1;
			return true;
		}
		for(int i = 0 ; i < 4 ; i++) {
			int nR = R+drdc[i][0], nC = C+drdc[i][1];
			if(nR<0||nC<0||nR>=M||nC>=N||map[nR][nC]>=num) continue;
			if(dfs(map[nR][nC],nR,nC)) {
				cntMap[R][C] += cntMap[nR][nC];
			}
		}
		if(cntMap[R][C]==0) {
			cntMap[R][C] = -1;
			return false;
		}
		else return true;
	}

}