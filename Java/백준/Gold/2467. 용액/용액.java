import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * 투포인터 메모리 ? KB 시간 ? ms
 * 
 * @author python98
 */
public class Main {
	static int N;
	static long mix = 2000000001, result[], arr[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		arr = new long[N];
		result = new long[2];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; i++) {
			arr[i] = Long.parseLong(st.nextToken());
		}
		int left = 0, right = N-1;
		while(left<right) {
			long temp = arr[left]+arr[right];
			if(Math.abs(mix)>Math.abs(temp)) {
				result[0] = arr[left];
				result[1] = arr[right];
				mix = temp;
			}
			if(temp>0) {
				right--;
			} else {
				left++;
			}
		}
		sb.append(result[0]).append(' ').append(result[1]);
		System.out.print(sb);
	}
}