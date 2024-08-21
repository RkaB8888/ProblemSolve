/**
 * 메모리:12,816KB, 시간:76ms
 * @author SSAFY
 *
 */
public class Main {
	static int[][] map;
	static  StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception {
		
		int N = readInt1();
		map = new int[N][N];
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < N ; j++) {
				map[i][j] = readInt();
			}
		}
		divide(0,0,N);
		System.out.println(sb);
	}
	
	public static void divide(int r, int c, int len) {
		if(len==1) {
			sb.append(map[r][c]);
			return;
		}
		int sum = 0;
		for(int i = r, iEnd =r+len ; i < iEnd ; i++) {
			for(int j = c, jEnd = c+len ; j < jEnd ; j++) {
				sum+=map[i][j]; 
			}
		}
		if(sum==0||sum==len*len) {
			sb.append((sum==0)?0:1);
		}
		else {
			int half = len/2;
			sb.append('(');
			divide(r,c,half);
			divide(r,c+half,half);
			divide(r+half,c,half);
			divide(r+half,c+half,half);
			sb.append(')');
		}
	}
	
	public static int readInt1() throws Exception {
		int val = 0;
		int c = System.in.read();
		while(c<=' ') {
			c = System.in.read();
		}
		do {
			val = 10*val+c-48;
		}while((c=System.in.read())>=48&&c<=57);
		return val;
	}
	public static int readInt() throws Exception {
		int c = System.in.read();
		while(c<=' ') {
			c = System.in.read();
		}
		return c-48;
	}

}