import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * 출력 메모리 ? KB 시간 ? ms
 * 
 * @author python98
 */
public class Main {
	static char[] score;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		score = br.readLine().toCharArray();
		if(score[0]=='A') {
			if(score[1]=='+') {
				System.out.print("4.3");
			} else if(score[1]=='0') {
				System.out.print("4.0");
			} else {
				System.out.print("3.7");
			}
		} else if(score[0]=='B') {
			if(score[1]=='+') {
				System.out.print("3.3");
			} else if(score[1]=='0') {
				System.out.print("3.0");
			} else {
				System.out.print("2.7");
			}
		} else if(score[0]=='C') {
			if(score[1]=='+') {
				System.out.print("2.3");
			} else if(score[1]=='0') {
				System.out.print("2.0");
			} else {
				System.out.print("1.7");
			}
		} else if(score[0]=='D') {
			if(score[1]=='+') {
				System.out.print("1.3");
			} else if(score[1]=='0') {
				System.out.print("1.0");
			} else {
				System.out.print("0.7");
			}
		} else System.out.print("0.0");
	}
}