import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/**
 * 메모리:110,496KB, 시간:1,974ms
 * KRUSKAL 알고리즘 사용
 */
public class Solution {
	static int V, E;
	static int[] Groups = new int[100001];;
	static int[][] Es;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			Es = new int[E][3];
			makeSet();
			for(int i = 0 ; i < E ; i++) {
				st = new StringTokenizer(br.readLine());
				Es[i][0] = Integer.parseInt(st.nextToken());
				Es[i][1] = Integer.parseInt(st.nextToken());
				Es[i][2] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(Es,(a,b)-> a[2]-b[2]);
			sb.append("#").append(tc).append(" ").append(process()).append("\n");
		}
		System.out.println(sb);
	}
	
	public static long process() {
		long minVal = 0;
		for(int i = 0 ; i < E ; i++) {
			if(union(Es[i][0],Es[i][1])) {
				minVal+=Es[i][2];
				if(Groups[findSet(Es[i][0])]==-V) break;
			}
		}
		
		return minVal;
	}
	public static void makeSet() {
		for(int i =1 ; i <= 100000 ; i++) {
			Groups[i] = -1;
		}
	}
	public static int findSet(int a) {
		if(Groups[a]<0) return a;
		return Groups[a] = findSet(Groups[a]);
	}
	public static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if(aRoot==bRoot) return false;
		Groups[bRoot]+=Groups[aRoot];
		Groups[aRoot] = bRoot;
		return true;
	}
}