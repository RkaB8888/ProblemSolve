import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int page = Integer.parseInt(br.readLine());
        boolean[] print = new boolean[1001];
        while(page > 0) {
            st = new StringTokenizer(br.readLine());
            int result = 0;
            while(st.hasMoreTokens()){
                StringTokenizer range = new StringTokenizer(st.nextToken(","));
                int low = Integer.parseInt(range.nextToken("-"));
                low = Math.max(low, 1);
                if(low > page) continue;
                if(range.hasMoreTokens()) {
                    int high = Integer.parseInt(range.nextToken());
                    high = Math.min(high, page);
                    if(low > high) continue;
                    while(low <= high) {
                        if(!print[low]) {
                            print[low] = true;
                            result++;
                        }
                        low++;
                    }
                } else {
                    if(!print[low]) {
                        print[low] = true;
                        result++;
                    }
                }
            }
            sb.append(result).append("\n");
            Arrays.fill(print, false);
            page = Integer.parseInt(br.readLine());
        }
        System.out.print(sb);
    }
}