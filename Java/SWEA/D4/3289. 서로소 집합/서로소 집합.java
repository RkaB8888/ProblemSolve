import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * 메모리:102,328KB, 시간:429ms
 */
public class Solution {
    static int N;
    static int M;
    static int[] group;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader( new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
        	sb.append('#').append(tc).append(' ');
        	st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            makeSet();
            for(int i = 0 ; i < M ; i++) {
            	st = new StringTokenizer(br.readLine());
            	if(st.nextToken().charAt(0)=='0') {
            		union(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
            	}
            	else {
            		findSet(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
            	}
            }
            sb.append('\n');
        }
        System.out.println(sb);
         
    }
    public static void makeSet() {
    	group = new int[N+1];
    	for(int i = 1 ; i <= N ; i++) {
    		group[i] = i;
    	}
    }
    public static int findSet(int a) {
    	if(group[a]==a) return a;
    	return group[a]=findSet(group[a]);
    }
    public static void findSet(int a, int b) {
    	int aRoot = findSet(a);
    	int bRoot = findSet(b);
    	if(aRoot==bRoot) sb.append(1);
    	else sb.append(0);
    }
    public static void union(int a, int b) {
    	int aRoot = findSet(a);
    	int bRoot = findSet(b);
    	if(aRoot==bRoot) return;
    	group[aRoot] = bRoot;
    }
 
}