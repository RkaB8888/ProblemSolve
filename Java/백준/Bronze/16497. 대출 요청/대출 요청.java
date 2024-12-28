import java.io.*;
import java.util.*;

/**
 * @description
 *    - N크기의 ArrayList에 대충 요청 정보를 담고 대출일 오름차순 정렬.<br>
 *    - PriorityQueue에 반납일을 넣는다(K-1).<br>
 *    - PQ에서 나온 반납일보다 
 *    <ul>
 *      <li>다음의 대출일보다 크다면: 더이상 PQ를 비교할 필요 없음
 *      <li>다음의 대출일보다 작거나 같다면: K++
 *    </ul>
 *    - K가
 *    <ul>
 *    	<li>0보다 작다면: 0 출력
 *    	<li>0이상이라면: 1 출력
 *    </ul>
 * @performance 메모리: ? KB, 동작시간: ? ms
 * @author python98
 */
public class Main {
	static int N, K, result;
	static List<int[]> list;
	static PriorityQueue<Integer> returnBook;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		returnBook = new PriorityQueue<>((a,b)-> a-b);
		
		list = new ArrayList<>(N);
		for(int i = 0 ; i < N ; i++) {
			st = new StringTokenizer(br.readLine());
			list.add(new int[] {Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())});
		}
		list.sort((a,b)->a[0]-b[0]);
		
		K = Integer.parseInt(br.readLine());
		
		returnBook.add(0);
		K--;
		result = 1;
		for(int[] request : list) {
			while(!returnBook.isEmpty()) {
				int returnDay = returnBook.peek();
				if(request[0]>=returnDay) {
					returnBook.poll();
					K++;
				} else {
					break;
				}
			}
			K--;
			if(K<0) {
				result = 0;
				break;
			}
			returnBook.add(request[1]);
		}
		System.out.print(result);
	}
}