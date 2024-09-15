import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 *  20,848KB 149ms
 *  dp 사용
 */
public class Solution {
    static int fees[]= new int[4],dp[]= new int[13];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int TC = Integer.parseInt(br.readLine().trim());
        
        for (int tc = 1; tc <= TC; tc++) {
            st = new StringTokenizer(br.readLine().trim());
            for(int i = 0 ; i < 4 ; i++) {
            	fees[i] = Integer.parseInt(st.nextToken());
            }
            dp[0] = 0;
            st = new StringTokenizer(br.readLine().trim());
            for(int i = 1 ; i <= 12 ; i++) {
            	int val = Integer.parseInt(st.nextToken());
            	if(val!=0) {
            		dp[i] = dp[i-1]+Math.min(fees[0]*val,fees[1]);
            		if(i>=3) {
            			dp[i] = Math.min(dp[i], dp[i-3]+fees[2]);
            		}
            	}
            	else dp[i] = dp[i-1];
            }
            dp[12] = Math.min(dp[12], fees[3]);
            sb.append('#').append(tc).append(' ').append(dp[12]).append('\n');
        }
        System.out.println(sb);
    }
 
}