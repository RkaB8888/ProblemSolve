import java.io.*;
import java.util.*;

/**
 * @description
 *    - A 길이의 빵을 X로 나눈 몫(S)을 구함 -> 최대 S번 끼니 해결.<br>
 *    <ul>
 *    	<li> 만약 A > Y * S 라면 전부 Y길이로 먹어도 남는다는 것.<br>
 *    	<li> 아니라면 X~Y의 길이로 모두 해결 가능하여 남지 않는다는 것.<br>
 *    </ul>
 * @performance 메모리: ? KB, 동작시간: ? ms
 * @author python98
 */
public class Main {
	static int N, X, Y, S, A, result, rest;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		Y = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; i++) {
			A = Integer.parseInt(st.nextToken());
			S = A / X;
			result += S;
			if(A > Y * S) {
				rest += A % Y;
			}
		}
		sb.append(result).append("\n").append(rest);
		System.out.print(sb);
	}
}