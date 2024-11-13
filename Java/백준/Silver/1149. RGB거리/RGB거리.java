import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * DP 11,932 KB 시간 76 ms
 */
public class Main {
    static int N;
    static int[] DP, preDP; //r g b
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());
        DP = new int[3];
        preDP = new int[3];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        DP[0] = Integer.parseInt(st.nextToken());
        DP[1] = Integer.parseInt(st.nextToken());
        DP[2] = Integer.parseInt(st.nextToken());
        for(int i = 1 ; i < N ; i++) {
        	st = new StringTokenizer(br.readLine());
        	System.arraycopy(DP, 0, preDP, 0, 3);
        	DP[0] = Math.min(preDP[1], preDP[2])+Integer.parseInt(st.nextToken());
        	DP[1] = Math.min(preDP[0], preDP[2])+Integer.parseInt(st.nextToken());
        	DP[2] = Math.min(preDP[1], preDP[0])+Integer.parseInt(st.nextToken());
        }
        System.out.print(Math.min(DP[0],Math.min(DP[1], DP[2])));
    }
}