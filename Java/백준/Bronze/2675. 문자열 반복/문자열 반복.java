import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * 입출력 메모리 ? KB 시간 ? ms
 */
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1 ; tc <= T ;tc++) {
        	StringTokenizer st = new StringTokenizer(br.readLine());
        	int R = Integer.parseInt(st.nextToken());
        	char[] c = st.nextToken().toCharArray();
        	for(int i = 0 ; i < c.length ; i++) {
        		for(int j = 0 ; j < R ; j++) {
        			sb.append(c[i]);
        		}
        	}
        	sb.append('\n');
        }
        System.out.print(sb);
    }

}