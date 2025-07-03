import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cnt = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int min = 1000000, max = 2;

        for(int i = 0 ; i < cnt ; i++){
            int cur = Integer.parseInt(st.nextToken());
            if(min > cur) min = cur;
            if(max < cur) max = cur;
        }
        System.out.print(min*max);
    }
}
