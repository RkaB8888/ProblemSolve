import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 메모리 13,524KB 시간 112ms
 */
public class Main {
	static int N, M, trees[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		trees = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; i++) {
			trees[i] = Integer.parseInt(st.nextToken());
			
		}
		int min = 0, max = 1999999999, result=min;
		while(min<=max) {
			long sum = 0;
			int mid = (min+max)/2;
			for(int i = 0 ; i < N ; i++) {
				if(trees[i]<mid) continue;
				sum+=trees[i]-mid;
				if(sum>M) break;
			}
			if(sum>M) {
				result = mid;
				min = mid+1;
			}
			else if(sum<M){
				max = mid-1;
			}
			else {
				result = mid;
				break;
			}
		}
		System.out.println(result);
	}

}