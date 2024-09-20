import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
       for(int tc = 1, TC = Integer.parseInt(br.readLine()) ; tc<=TC ; tc++){
           StringTokenizer st = new StringTokenizer(br.readLine());
           sb.append(Integer.parseInt(st.nextToken())+Integer.parseInt(st.nextToken())).append('\n');
       }
       System.out.println(sb);
    }

}