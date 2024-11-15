import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * 입출력 메모리 ? KB 시간 ? ms
 */
public class Main {
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();
        
        for(char c : br.readLine().toCharArray()) {
        	result+=c-'0';
        }
        System.out.print(result);
    }

}