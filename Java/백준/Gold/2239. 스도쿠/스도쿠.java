import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 메모리:?KB, 시간:?ms
 */
public class Main {
	static boolean rowCheck[][], colCheck[][], sqCheck[][], flag;
	static int map[][], N;
	static List<int[]> list;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		map = new int[9][9];
		rowCheck = new boolean[9][10];
		colCheck = new boolean[9][10];
		sqCheck = new boolean[9][10];
		list = new ArrayList<>();
		for(int i = 0 ; i < 9 ; i++) {
			char c[] = br.readLine().toCharArray();
			for(int j = 0 ; j < 9 ; j++) {
				int num = c[j]-'0';
				map[i][j] = num;
				if(num==0) {
					list.add(new int[] {i,j});
				} else {
					rowCheck[i][num] = true;
					colCheck[j][num] = true;
					sqCheck[j/3+(i/3)*3][num] = true;
				}
			}
		}
		N = list.size();
		dfs(0);
		for(int i = 0 ; i < 9 ; i++) {
			for(int j = 0 ; j < 9 ; j++) {
				sb.append(map[i][j]);
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}
	private static void dfs(int cnt) {
		if(cnt==N) {
			flag = true;
			return;
		}
		int[] curZero = list.get(cnt);
		for(int i = 1 ; i <= 9 ; i++) {
			if(rowCheck[curZero[0]][i]||colCheck[curZero[1]][i]||sqCheck[curZero[1]/3+(curZero[0]/3)*3][i]) continue;
			rowCheck[curZero[0]][i] = true;
			colCheck[curZero[1]][i] = true;
			sqCheck[curZero[1]/3+(curZero[0]/3)*3][i] = true;
			map[curZero[0]][curZero[1]] = i;
			dfs(cnt+1);
			if(flag) return;
			rowCheck[curZero[0]][i] = false;
			colCheck[curZero[1]][i] = false;
			sqCheck[curZero[1]/3+(curZero[0]/3)*3][i] = false;
			map[curZero[0]][curZero[1]] = 0;
		}
		
	}

}