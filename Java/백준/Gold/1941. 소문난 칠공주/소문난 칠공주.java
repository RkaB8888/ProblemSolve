import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
	static int result;
	static char map[][] = new char[5][];
	static boolean checkMap[][] = new boolean[5][5];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int i = 0 ; i < 5 ; i++) {
			map[i] = br.readLine().toCharArray();
		}
		loop(0,0,0,0);
		System.out.println(result);
	}
	public static void loop(int cnt, int str, int Scnt, int Ycnt) {
		if(Ycnt>3) return;
		if(cnt==7) {
			if(check()) {
				result++;
			}
			return;
		}
		for(int i = str ; i < 25 ; i++) {
			int r = i/5;
			int c = i%5;
			int s = 0, y = 0;
			if(map[r][c]=='S') {
				s = 1;
				y = 0;
			}
			else {
				s = 0;
				y = 1;
			}
			checkMap[r][c] = true;
			loop(cnt+1, i+1, Scnt+s, Ycnt+y);
			checkMap[r][c] = false;
		}
	}
	public static boolean check() {
		boolean copyCheck[][] = new boolean[5][5];
		int drdc[][] = {{-1,0},{1,0},{0,-1},{0,1}};
		for(int i = 0 ; i < 5 ; i++) {
			System.arraycopy(checkMap[i], 0, copyCheck[i], 0, 5);
		}
		Queue<int[]> q = new ArrayDeque<>();
		a: for(int i = 0 ; i < 5 ; i++) {
			for(int j = 0 ; j < 5 ; j++) {
				if(copyCheck[i][j]) {
					copyCheck[i][j] = false;
					q.add(new int[] {i,j});
					break a;
				}
			}
		}
		int cnt = 0;
		while(!q.isEmpty()) {
			int[] temp = q.poll();
			cnt++;
			for(int i = 0 ; i < 4 ; i++) {
				int r = temp[0]+drdc[i][0];
				int c = temp[1]+drdc[i][1];
				if(r<0||c<0||r>=5||c>=5||!copyCheck[r][c]) continue;
				copyCheck[r][c]=false;
				q.add(new int[] {r,c});
			}
		}
		if(cnt==7) return true;
		return false;
	}
}