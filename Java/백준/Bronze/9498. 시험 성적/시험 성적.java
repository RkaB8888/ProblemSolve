import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * 
 * 입출력 메모리 ? KB 시간 ? ms
 *
 */
public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int score = Integer.parseInt(br.readLine());
		if(score>=90) {
			System.out.println('A');
			return;
		} else if(score>=80) {
			System.out.println('B');
			return;
		}else if(score>=70) {
			System.out.println('C');
			return;
		}else if(score>=60){
			System.out.println('D');
			return;
		} else {
			System.out.println('F');
			return;
		}
	}
}