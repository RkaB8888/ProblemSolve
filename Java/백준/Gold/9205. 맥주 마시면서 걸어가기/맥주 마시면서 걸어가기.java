import java.io.*;
import java.util.*;

/**
 * @description BFS
 * @performance 메모리: ? KB, 동작시간: ? ms
 * @author python98
 */
public class Main {

	private static int nextInt() throws IOException{
		int s=1, n, c;
		while((n = System.in.read())<=32);
		if(n=='-') {
			s = -1;
			n = System.in.read() & 15;
		} else {
			n &= 15;
		}
		while((c = System.in.read())>32) {
			n = (n<<3) + (n<<1) + (c&15);
		}
		return s*n;
	}

	private static int getDist(int a, int b){
		return a>b?a-b:b-a;
	}
	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		for(int t = nextInt() ; t > 0 ; t--) {
			int[] node = new int[101];
			int n = nextInt();
			int x = nextInt();
			int y = nextInt();
			node[0] = (x<<16) | (y&0xFFFF);
			for(int i = 1 ; i <= n ; i++){
				node[i] = (nextInt()<<16) | (nextInt()&0xFFFF);
			}
			x = nextInt();
			y = nextInt();

			int[] stack = new int[101];
			int top = 0;
			stack[top++] = 0;
			boolean[] visited = new boolean[101];
			visited[0] = true;
			boolean flag = false;
			while(top>0) {
				int curIdx = stack[--top];
				int curX = node[curIdx]>>16;
				int curY = (node[curIdx]<<16)>>16;
				int dist = getDist(curX,x)+getDist(curY,y);
				if(dist<=1000) {
					flag = true;
					break;
				}
				for(int i = 1 ; i <= n ; i++) {
					if(visited[i]) continue;
					int nextX = node[i]>>16;
					int nextY = (node[i]<<16)>>16;
					dist = getDist(curX,nextX)+getDist(curY,nextY);
					if(dist<=1000) {
						visited[i] = true;
						stack[top++] = i;
					}
				}
			}
			if(flag) {
				sb.append("happy\n");
			} else {
				sb.append("sad\n");
			}
		}
		System.out.print(sb);
	}
}