import java.io.*;
import java.util.*;

/**
 * @description 시뮬레이션
 * @performance 메모리: 49,336 KB, 동작시간: 296 ms
 * @author python98
 */
public class Main {

	static final int[][] DIR = {{1,0},{-1,0},{0,1},{0,-1}};

	static int R, C, front, rear;
	static int[][] q;
	static int[][] map;

	private static int nextInt() throws IOException {
		int c, n;
		while ((n = System.in.read()) <= 32);
		n &= 15;
		while ((c = System.in.read()) > 32) {
			n = (n << 3) + (n << 1) + (c & 15);
		}
		return n;
	}

	public static void main(String[] args) throws IOException {
		R = nextInt();
		C = nextInt();
		map = new int[R][C];
		q = new int[R*C][2];
		int[] J = new int[2];
		for(int i = 0 ; i < R ; i++) {
			for(int j = 0 ; j < C ; j++) {
				map[i][j] = System.in.read();
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
			System.in.read();
		}
		q[rear][0] = J[0];
		q[rear][1] = J[1];
		rear++;
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
						else {
							System.out.print(time);
							return;
						}
					}
					if(map[nextR][nextC]!='.') continue;
					map[nextR][nextC] = map[curR][curC];
					q[rear][0] = nextR;
					q[rear][1] = nextC;
					rear++;
				}
			}
		}
		System.out.print("IMPOSSIBLE");
	}
}