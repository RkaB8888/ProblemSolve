import java.io.*;
import java.util.*;

/**
 * @description
 *    - 32길이의 달력배열에 책 대출 기간동안 +1씩 해줌.<br>
 *    - 1~31 인덱스의 값이.<br>
 *    <ul>
 *      <li>K보다 크다면: 0 출력
 *      <li>K보다 작거나 같다면: 1 출력
 *    </ul>
 * @performance 메모리: ? KB, 동작시간: ? ms
 * @author python98
 */
public class Main {
	static int N, K, result;
	static int[] day;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		day = new int[32];
		for(int i = 0 ; i < N ; i++) {
			st = new StringTokenizer(br.readLine());
			int startDay = Integer.parseInt(st.nextToken())+1;
			int endDay = Integer.parseInt(st.nextToken());
			for(;startDay<=endDay;startDay++) {
				day[startDay]++;
			}
		}
		
		K = Integer.parseInt(br.readLine());
		result = 1;
		for(int i = 1 ; i < 32 ; i++) {
			if(day[i]>K) {
				result = 0;
				break;
			}
		}
		System.out.print(result);
	}
}