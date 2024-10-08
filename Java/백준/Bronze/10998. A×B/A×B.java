import java.io.*;
import java.util.*;
/**
* 메모리 ? KB 시간 ? ms
*/
public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        System.out.print(Integer.parseInt(st.nextToken())*Integer.parseInt(st.nextToken()));
    }
}