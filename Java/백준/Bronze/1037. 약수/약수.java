import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cnt = Integer.parseInt(br.readLine());
        int[] list = new int[cnt];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < cnt; i++){
            list[i] = Integer.parseInt(st.nextToken());
        }
        int result;
        if(cnt==1) {
            result = list[0]*list[0];
        } else {
            Arrays.sort(list);
            result = list[0] * list[cnt-1];
        }
        System.out.print(result);
    }
}
