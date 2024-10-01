import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int N, result;
	static boolean col[], lu[], ru[];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		col = new boolean[N];
		lu = new boolean[2*N];
		ru = new boolean[2*N];
		
		dfs(0);
		System.out.println(result);
	}
	private static void dfs(int row) {
		if(row==N) {
			result++;
			return;
		}
		for(int i = 0 ; i < N ; i++) {
			if(col[i]||lu[row-i+N]||ru[row+i]) continue;
			col[i] = true;
			lu[row-i+N] = true;
			ru[row+i] = true;
			dfs(row+1);
			col[i] = false;
			lu[row-i+N] = false;
			ru[row+i] = false;
		}
	}
}