import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * 
 * 그리디 메모리 ? KB 시간 ? ms
 * @author python98
 *
 */
public class Main {
	static int N, result;
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        double preVal = 0;
        for(int i = 0 ; i < N ; i++) {
        	double curVal = Math.log(Integer.parseInt(st.nextToken()))/Math.log(2);
        	if(preVal<=curVal) {
        		preVal = curVal;
        	} else {
        		double diff = Math.ceil(preVal - curVal);
        		result+=diff;
        		preVal = curVal+diff;
        	}
        }
        System.out.print(result);
    }

}