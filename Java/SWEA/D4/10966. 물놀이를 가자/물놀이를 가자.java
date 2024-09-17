import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * ?KB ?ms
 * 
 */
public class Solution {
    static int N, M, result, drdc[][] = {{-1,0},{1,0},{0,-1},{0,1}};
    static char map[][];
    static boolean check[][];
    static Queue<int[]> q;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        int TC = Integer.parseInt(br.readLine().trim());
        
        for (int tc = 1; tc <= TC; tc++) {
            st = new StringTokenizer(br.readLine().trim());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            result = 0;
            map = new char[N][];
            check = new boolean[N][M];
            q = new ArrayDeque<>();
            for(int i = 0 ; i < N ; i++) {
            	map[i] = br.readLine().toCharArray();
            	for(int j = 0 ; j < M ; j++) {
            		if(map[i][j]=='W') {
            			q.add(new int[] {i,j,0});
            			check[i][j] = true;
            		}
            	}
            }
            while(!q.isEmpty()) {
            	int cur[] = q.poll();
            	for(int i = 0 ; i < 4 ; i++) {
            		int nr = cur[0]+drdc[i][0],nc = cur[1]+drdc[i][1];
            		if(nr<0||nc<0||nr>=N||nc>=M||check[nr][nc]) continue;
            		check[nr][nc] = true;
            		result+=cur[2]+1;
            		q.add(new int[] {nr,nc,cur[2]+1});
            	}
            }
            sb.append('#').append(tc).append(' ').append(result).append('\n');
        }
        System.out.println(sb);
    }

}