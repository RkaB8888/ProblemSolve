import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * 수학 메모리 ? KB 시간 ? ms
 */
public class Main {
	static boolean isNotSosu[];
	static int N, result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        isNotSosu = new boolean[1001];
        isNotSosu[0]= true;
        isNotSosu[1] = true;
        for(int i = 2, iEnd = (int)Math.sqrt(1000) ; i <= iEnd ; i++) {
        	if(!isNotSosu[i]) {
        		int num = i+i;
        		while(num<=1000) {
        			isNotSosu[num] = true;
        			num += i;
        		}
        	}
        }
        
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i++) {
        	if(isNotSosu[Integer.parseInt(st.nextToken())]) continue;
        	result++;
        }
        System.out.print(result);
    }
}