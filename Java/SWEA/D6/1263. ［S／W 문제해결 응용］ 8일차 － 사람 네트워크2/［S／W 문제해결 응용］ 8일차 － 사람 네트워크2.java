import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
/**
 * 메모리:?KB, 시간:?ms
 * 플로이드 워샬을 통해 각 노드의 최단 거리를 구한다.
 * 그 중 가장 작은 값을 출력
 */
public class Solution {
	static int N, adjMatrix[][], sum[], result;
	static final int inf = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=T; tc++) {
        	StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            adjMatrix = new int[N][N];
            sum = new int[N];
            result = inf;
            for(int i = 0 ; i < N ; i++) {
            	for(int j = 0 ; j < N ; j++) {
            		int num = Integer.parseInt(st.nextToken());
            		if(i==j) continue;
            		if(num==0) {
            			adjMatrix[i][j] = inf;
            		} else {
            			adjMatrix[i][j] = num;
            		}
            	}
            }
            for(int k = 0 ; k < N ; k++) {
            	for(int i = 0 ; i < N ; i++) {
            		if(i==k) continue;
            		for(int j = 0 ; j < N ; j++) {
            			if(j==k||j==i||adjMatrix[i][k]==inf||adjMatrix[k][j]==inf) continue;
            			adjMatrix[i][j] = Math.min(adjMatrix[i][j], adjMatrix[i][k]+adjMatrix[k][j]);
            		}
            	}
            }
            for(int i = 0 ; i < N ; i++) {
            	for(int j = 0 ; j < N ; j++) {
//            		System.out.print(adjMatrix[i][j]+" ");
            		sum[i] += adjMatrix[i][j];
            	}
//            	System.out.println();
            	result = Math.min(sum[i], result);
            }
//            System.out.println(inf);
            sb.append("#").append(tc).append(" ").append(result).append("\n");
        }
        System.out.println(sb);
    }
}