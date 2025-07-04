import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Integer[] arr = new Integer[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr,Collections.reverseOrder());
        int result = 2;
        for(int i = 0 ; i < N ; i++) {
            int temp = arr[i] + i + 2;
            if(temp > result) result = temp;
        }
        System.out.print(result);
    }
}
