import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        boolean[] print = new boolean[1001];

        String line;
        while ((line = br.readLine()) != null) {
            int page = Integer.parseInt(line.trim());
            if (page == 0) break;

            Arrays.fill(print, false);
            String[] tokens = br.readLine().split(",");
            int result = 0;

            for (String token : tokens) {
                String[] range = token.split("-");
                int start = Integer.parseInt(range[0]);
                int end = (range.length == 2) ? Integer.parseInt(range[1]) : start;
                end = Math.min(end, page);
                if (start > end) continue;
                for (int i = start; i <= end; i++) {
                    if (!print[i]) {
                        print[i] = true;
                        result++;
                    }
                }
            }
            sb.append(result).append("\n");
        }
        System.out.print(sb);
    }
}

