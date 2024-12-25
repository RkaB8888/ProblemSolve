import java.io.*;
import java.util.*;
/**
 * 구현 메모리 ? KB 시간 ? ms
 * 
 * @author python98
 */
public class Main {
	static int score[], N, temp, result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        score = new int[3];
        score[0] = Integer.parseInt(st.nextToken());
        score[1] = Integer.parseInt(st.nextToken());
        score[2] = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(br.readLine());
        for(int i = 0 ; i < N ; i++) {
        	for(int j = 0 ; j < 3 ; j++) {
        		st = new StringTokenizer(br.readLine());
        		for(int k = 0 ; k < 3 ; k++) {
        			temp+= score[k]*Integer.parseInt(st.nextToken());
        		}
        	}
        	result = Math.max(result, temp);
        	temp = 0;
        }
        System.out.print(result);
    }
}