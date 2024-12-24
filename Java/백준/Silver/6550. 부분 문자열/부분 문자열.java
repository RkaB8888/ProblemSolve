import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String line;

        while ((line = br.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(line);
            char[] s = st.nextToken().toCharArray();
            char[] t = st.nextToken().toCharArray();

            int i = 0, j = 0;
            while (i < s.length && j < t.length) {
                if (s[i] == t[j]) {
                    i++;
                }
                j++;
            }

            if (i == s.length) {
                sb.append("Yes\n");
            } else {
                sb.append("No\n");
            }
        }
        System.out.print(sb);
    }
}