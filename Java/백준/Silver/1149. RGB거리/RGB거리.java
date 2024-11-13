import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * DP 12,036 KB 시간 72 ms
 */
public class Main {
    static int N, R, G, B;
    static int[] DP; //r g b
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());
        DP = new int[3];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        DP[0] = Integer.parseInt(st.nextToken());
        DP[1] = Integer.parseInt(st.nextToken());
        DP[2] = Integer.parseInt(st.nextToken());
        for(int i = 1 ; i < N ; i++) {
        	st = new StringTokenizer(br.readLine());
        	R = Math.min(DP[1], DP[2])+Integer.parseInt(st.nextToken());
        	G = Math.min(DP[0], DP[2])+Integer.parseInt(st.nextToken());
        	B = Math.min(DP[1], DP[0])+Integer.parseInt(st.nextToken());
        	DP[0] = R;
            DP[1] = G;
            DP[2] = B;
        }
        System.out.print(Math.min(DP[0],Math.min(DP[1], DP[2])));
    }
}