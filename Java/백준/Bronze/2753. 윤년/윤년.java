import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * 입출력 메모리 ? KB 시간 ? ms
 */
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int year = Integer.parseInt(br.readLine());
        System.out.print(year%400==0||!(year%100==0||year%4!=0)?1:0);
    }

}