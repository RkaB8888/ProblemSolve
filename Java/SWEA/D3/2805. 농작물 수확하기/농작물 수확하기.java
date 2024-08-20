import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * 메모리:19,436KB, 시간:120ms
 * @author SSAFY
 *
 */
public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int map[][] = new int[N][N];
			for(int i = 0 ; i < N ; i++) {
				String temp = br.readLine();
				for(int j = 0 ; j < N ; j++) {
					map[i][j] = temp.charAt(j)-'0';
				}
			}
			int centerI = N/2, centerJ = N/2;
			int length = N/2;
			int result = 0;
			for(int i = 0 ; i <= length ; i++) {
				for(int j = 0 ; j <=length ; j++) {
					if(i+j<=length) {
						result+=map[centerI+i][centerJ+j];
						map[centerI+i][centerJ+j] = 0;
						result+=map[centerI-i][centerJ+j];
						map[centerI-i][centerJ+j] = 0;
						result+=map[centerI+i][centerJ-j];
						map[centerI+i][centerJ-j] = 0;
						result+=map[centerI-i][centerJ-j];
						map[centerI-i][centerJ-j] = 0;
					}
				}
			}
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}
		System.out.println(sb);
	}
}