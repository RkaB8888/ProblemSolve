import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * 입출력 메모리 ? KB 시간 ? ms
 */
public class Main {
    static int max, result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        for(int i = 1 ; i <= 9 ;i++) {
        	int temp = Integer.parseInt(br.readLine());
        	if(max<temp) {
        		max = temp;
        		result = i;
        	}
        }
        sb.append(max).append('\n').append(result);
        System.out.print(sb);
    }

}