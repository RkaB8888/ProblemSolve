import java.io.*;
import java.util.StringTokenizer;
/**
 * 구현 메모리 ? KB 시간 ? ms
 * 
 * @author python98
 */
public class Main {
	static int Soongsil,Korea,Hanyang,sum;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Soongsil=Integer.parseInt(st.nextToken());
		Korea=Integer.parseInt(st.nextToken());
		Hanyang=Integer.parseInt(st.nextToken());
		sum = Soongsil+Korea+Hanyang;
		if(sum<100) {
			if(Soongsil<Korea) {
				if(Soongsil<Hanyang) {
					System.out.print("Soongsil");
				} else {
					System.out.print("Hanyang");
				}
			} else {
				if(Korea<Hanyang) {
					System.out.print("Korea");
				} else {
					System.out.print("Hanyang");
				}
			}
		} else {
			System.out.print("OK");
		}
	}
}