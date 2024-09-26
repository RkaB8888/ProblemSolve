import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 
 * @author python98
 * dfs floodfill
 * ? KB ? ms
 *
 */

public class Main {
    static int T, R, C, K, map[][], cnt, drdc[][] = {{-1,0},{1,0},{0,-1},{0,1}};
    static List<int[]> list;
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());
        for(int tc = 1 ; tc <= T ; tc++) {
        	StringTokenizer st = new StringTokenizer(br.readLine());
        	C = Integer.parseInt(st.nextToken());
        	R = Integer.parseInt(st.nextToken());
        	K = Integer.parseInt(st.nextToken());
        	map = new int[R][C];
        	list = new ArrayList<>(K);
        	cnt = 2;
        	for(int i = 0 ; i < K ; i++) {
        		st = new StringTokenizer(br.readLine());
        		int x = Integer.parseInt(st.nextToken()), y = Integer.parseInt(st.nextToken());
        		map[y][x] = 1;
        		list.add(new int[] {y,x});
        	}
        	for(int[] d : list) {
        		if(map[d[0]][d[1]]==1) {
        			floodfill(d[0],d[1]);
        			cnt++;
        		}
        	}
        	sb.append(cnt-2).append('\n');
        }
        System.out.println(sb);
    }
	private static void floodfill(int row, int col) {
		int r, c;
		for(int i = 0 ; i < 4 ; i++) {
			r = row+drdc[i][0]; c = col+drdc[i][1];
			if(r<0||c<0||r>=R||c>=C||map[r][c]!=1) continue;
			map[r][c] = cnt;
			floodfill(r,c);
		}
	}

}