import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		boolean map[][] = new boolean[100][100];
		int result = 0;
		for(int T = 0 ; T < 4 ; T++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			for(int i = a ; i< c ; i++) {
				for(int j = b ; j < d ; j++) {
					if(map[i][j]) continue;
					else {
						map[i][j]=true;
						result++;
					}
				}
			}
		}
		System.out.println(result);
	}

}