import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 *  ? KB ? ms
 */
public class Solution {
    static int fees[]= new int[4], month[]= new int[12], result;
    static boolean check[];

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
            result = fees[3];
            check = new boolean[12];
            st = new StringTokenizer(br.readLine().trim());
            for(int i = 0 ; i < 12 ; i++) {
            	int val = Integer.parseInt(st.nextToken());
            	month[i] = val;
            	if(val!=0) {
            		check[i] = true;
            	}
            }
            dfs(0,0);
            sb.append('#').append(tc).append(' ').append(result).append('\n');
        }
        System.out.println(sb);
    }
    public static void dfs(int str, int sum) {
    	if(sum >= result) return;
    	if(str>=12) {
    		result = sum;
    		return;
    	}
    	while(str<12) {
    		if(check[str])break;
    		str++;
    	}
    	if(str==12) {
    		dfs(str,sum);
    		return;
    	}
    	check[str] = false;
    	if(str<11) {
    		check[str+1] = false;
    		if(str<10) {
    			check[str+2] = false;
    		}
    	}
    	dfs(str+3,sum+fees[2]);
    	if(str<11) {
    		check[str+1] = true;
    		if(str<10) {
    			check[str+2] = true;
    		}
    	}
    	
    	dfs(str+1,sum+fees[1]);
    	
    	dfs(str+1, sum+fees[0]*month[str]);
    	check[str] = true;
    }
}