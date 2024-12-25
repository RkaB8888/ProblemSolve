import java.io.*;
import java.util.*;
/**
 * 구현 메모리 ? KB 시간 ? ms
 * 
 * @author python98
 */
public class Main {
	static final int OFFSET = 10000000;
	static int N, M;
	static int[] have;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        have = new int[20000001];
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i++) {
        	have[Integer.parseInt(st.nextToken())+OFFSET]++;
        }
        M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < M ; i++) {
        	sb.append(have[Integer.parseInt(st.nextToken())+OFFSET]).append(" ");
        }
        System.out.print(sb);
    }
}