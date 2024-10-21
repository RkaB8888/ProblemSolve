import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * 메모리 ? KB 시간 ? ms 다시 풀어봄
 * 메모이제이션
 * @author python98
 */
public class Main {
	static int N, arr[];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		arr[0] = Integer.parseInt(st.nextToken());
		int cnt = 1;
		for(int i = 1 ; i < N ; i++) {
			int nextNum = Integer.parseInt(st.nextToken());
			if(arr[cnt-1]<nextNum) {
				arr[cnt++] = nextNum;
			} else {
				int idx = bSearch(0,cnt,nextNum);
				arr[idx] = nextNum;
			}
		}
		System.out.print(cnt);
	}
	private static int bSearch(int str, int end, int nextNum) {
		while(str<end) {
			int mid = (str+end)>>1;
			if(arr[mid]<nextNum) {
				str = mid+1;
			} else {
				end = mid;
			}
		}
		return str;
	}

}