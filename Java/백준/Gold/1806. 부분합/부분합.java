import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * 누적합 분할정복 메모리 ? KB 시간 ? ms
 */
public class Main {
	static int N, S, arr[], result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        arr = new int[N+1];
        st = new StringTokenizer(br.readLine());
        arr[0] = 0;
        for(int i = 1 ; i <= N ; i++) {
        	arr[i] = Integer.parseInt(st.nextToken())+arr[i-1];
        }
        if(arr[N]<S) result = 0;
        else {
        	process(N);
        }
        System.out.print(result);
    }
    
    private static void process(int len) {
    	for(int i = len ; i <= N ; i++) {
    		int findSum = arr[i]-arr[i-len];
    		if(findSum>=S) {
    			result = len;
    			process(len-1);
    			break;
    		}
    	}
    }
}