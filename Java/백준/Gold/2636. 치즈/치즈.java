import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * 메모리:13,460KB, 시간:92ms
 */
public class Main {
	static int R;
	static int C;
	static int[][] map;
	static int[][] dr_dc = {{-1,0},{1,0},{0,-1},{0,1}};
	static int time = 1;//1로 표시하면 치즈랑 헷갈려서
	static int cheeseSum;
	static int preCheeseSum;
	public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
    	
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        
        map = new int[R][C];
        
        for(int i = 0 ; i < R ; i++) {
        	st = new StringTokenizer(br.readLine());
        	for(int j = 0 ; j < C ; j++) {
        		int num = Integer.parseInt(st.nextToken());
        		map[i][j] = num;
        		cheeseSum+=num;
        	}
        }
        while(cheeseSum>0) {
        	time++;
        	preCheeseSum = cheeseSum;
        	findSurface(0,0);
        }
        sb.append(time-1).append('\n').append(preCheeseSum);
        System.out.println(sb);
    }
	//표면 표시
	public static void findSurface(int row, int col) {
		map[row][col] = time;
		int r,c;
		for(int i = 0 ; i < 4 ; i++) {
			r = row+dr_dc[i][0];
			c = col+dr_dc[i][1];
			if(r<0||c<0||r>=R||c>=C||map[r][c]==time) continue;
			if(map[r][c]==1) {
				map[r][c]=time;
				cheeseSum--;
				continue;
			}
			//1도 아니고 time도 아니면
			findSurface(r,c);
		}
	}
}