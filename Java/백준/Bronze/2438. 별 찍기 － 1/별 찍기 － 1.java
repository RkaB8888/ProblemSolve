import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        
       for(int i = 0 ; i < N ; i++) {
    	   for(int j = 0 ; j <= i  ; j++) {
    		   sb.append('*');
    	   }
    	   sb.append('\n');
       }
       System.out.print(sb);
    }

}