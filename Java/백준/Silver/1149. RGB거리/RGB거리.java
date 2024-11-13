import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * DP ? KB 시간 ? ms
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
        int r, g, b;
        for(int i = 1 ; i < N ; i++) {
//        	System.out.println("DP : "+Arrays.toString(DP));
        	st = new StringTokenizer(br.readLine());
        	r=Integer.parseInt(st.nextToken());
        	g=Integer.parseInt(st.nextToken());
        	b=Integer.parseInt(st.nextToken());
        	System.arraycopy(DP, 0, preDP, 0, 3);
//        	System.out.println("preDP : "+Arrays.toString(preDP));
        	DP[0] = Math.min(preDP[1], preDP[2])+r;
        	DP[1] = Math.min(preDP[0], preDP[2])+g;
        	DP[2] = Math.min(preDP[1], preDP[0])+b;
        }
//        System.out.println("DP : "+Arrays.toString(DP));
        System.out.print(Math.min(DP[0],Math.min(DP[1], DP[2])));
    }
}