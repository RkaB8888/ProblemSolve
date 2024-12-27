import java.io.*;
import java.util.*;

/**
 * @description
 *    - HashMap을 통해 정답자가 나올 때까지 모든 답변의 횟수 카운트.<br>
 *      정답자가 등장하면 정답자의 답변이 몇번 등장했는지 출력<br>
 * @performance 메모리: ? KB, 동작시간: ? ms
 * @author python98
 */
public class Main {
	static int N;
	static String answer;
	static Map<String,Integer> answerCnt;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		answerCnt = new HashMap<>();
		answer = st.nextToken();
		boolean flag = false;
		for(int i = 0 ; i < N ; i++) {
			st = new StringTokenizer(br.readLine());
			if(flag) continue;
			String user = st.nextToken();
			String chat = st.nextToken();
			if(user.equals(answer)) {
				answer = chat;
				flag = true;
			} else {
				int cnt = answerCnt.getOrDefault(chat, 0);
				answerCnt.put(chat, cnt+1);
			}
		}
		System.out.print(answerCnt.getOrDefault(answer,0));
	}
}