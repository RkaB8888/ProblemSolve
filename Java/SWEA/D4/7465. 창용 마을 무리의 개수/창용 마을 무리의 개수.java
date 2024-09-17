import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 *  25,964KB 139ms
 * Union Find
 */
public class Solution {
    static int N, M, group[], result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        int TC = Integer.parseInt(br.readLine().trim());
        
        for (int tc = 1; tc <= TC; tc++) {
            st = new StringTokenizer(br.readLine().trim());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            result = N;
            makeSet();
            for(int i = 0 ; i < M ; i++) {
            	st = new StringTokenizer(br.readLine().trim());
            	if(Union(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()))) {
            		result--;
            	}
            }

            sb.append('#').append(tc).append(' ').append(result).append('\n');
        }
        System.out.println(sb);
    }
    public static void makeSet() {
    	group = new int[N+1];
    	for(int i = 1 ; i <= N ; i++) {
    		group[i] = i;
    	}
    }
    public static int find(int a) {
    	if(group[a]==a) return a;
    	return group[a] = find(group[a]);
    }
    public static boolean Union(int a, int b) {
    	int aRoot = find(a);
    	int bRoot = find(b);
    	if(aRoot==bRoot) return false;
    	group[aRoot] = bRoot;
    	return true;
    }
}