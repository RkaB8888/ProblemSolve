import java.io.*;
import java.util.*;

/**
 * @description 이 클래스에 대한 동작 설명
 * @performance 메모리: ? KB, 동작시간: ? ms
 * @author python98
 */
public class Main {

	static final int[][] DIR = {{1,0},{-1,0},{0,1},{0,-1}};

	static int R, C, front, rear;
	static int[][] q;
	static char[][] map;

	private static int sim(){
		int time = 0;
		while(front<rear){
			time++;
			int cnt = rear-front;
			while(cnt-- > 0){
				int curR = q[front][0];
				int curC = q[front][1];
				front++;
				boolean isF = map[curR][curC]=='#';
				for(int i = 0 ; i < 4 ; i++){
					int nextR = curR+DIR[i][0];
					int nextC = curC+DIR[i][1];
					if(nextR<0||nextC<0||nextR>=R||nextC>=C) {
						if(isF) continue;
						else return time;
					}
					if(map[nextR][nextC]!='.') continue;
					map[nextR][nextC] = map[curR][curC];
					q[rear][0] = nextR;
					q[rear][1] = nextC;
					rear++;
				}
			}
		}
		return 0;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		q = new int[R*C][2];
		int[] J = new int[2];
		for(int i = 0 ; i < R ; i++) {
			map[i] = br.readLine().toCharArray();
			for(int j = 0 ; j < C ; j++) {
				if(map[i][j]=='F') {
					q[rear][0] = i;
					q[rear][1] = j;
					rear++;
					map[i][j] = '#';
				} else if(map[i][j] == 'J') {
					J[0] = i;
					J[1] = j;
				}
			}
		}
		q[rear][0] = J[0];
		q[rear][1] = J[1];
		rear++;
		int time = sim();
		System.out.print(time==0?"IMPOSSIBLE":time);
	}
}