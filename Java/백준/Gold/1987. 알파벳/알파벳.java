import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
/**
 * 
 * 메모리:14,928KB, 시간:1,424ms
 * 
 * @author SSAFY
 *
 */
public class Main {
	static int R;
	static int C;
	static char[][] map;
	static int maxMoveCnt;
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		for(int i = 0 ; i < R ; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		//////bfs
		PriorityQueue<int[]> q = new PriorityQueue<>((o1, o2) -> o2[3]-o1[3]);
		int bit = 0|(1<<(map[0][0]-'A'));
		q.add(new int[] {0,0,bit,1});
		int[][] dr_dc = {{-1,0},{1,0},{0,-1},{0,1}};
		int[] qV;
		int row, col, bitmask, moveCnt;
		while(!q.isEmpty()) {
				qV = q.poll();
				row = qV[0];
				col = qV[1];
				bitmask = qV[2];
				moveCnt = qV[3];
				maxMoveCnt = Math.max(maxMoveCnt, moveCnt);
				for(int i = 0 ; i < 4 ; i++) {
					int r = row+dr_dc[i][0];
					int c = col+dr_dc[i][1];
					if(r<0||c<0||r>=R||c>=C) continue;
					int nextbit = 1<<(map[r][c]-'A');
					if((bitmask&nextbit)>0) continue;
					int[] temp = {r,c,bitmask|nextbit,moveCnt+1};
					q.add(temp);
				}
		}
		////////////
		System.out.println(maxMoveCnt);
	}

}