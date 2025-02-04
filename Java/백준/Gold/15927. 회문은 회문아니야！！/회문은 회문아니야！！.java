import java.io.*;
import java.util.*;

/**
 * @description 그리디
 * @performance 메모리: ? KB, 동작시간: ? ms
 * @author python98
 */
public class Main {
	static char[] input, temp;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		input = br.readLine().toCharArray();
		int len = input.length;
		temp = new char[2*len+1];
		Arrays.fill(temp, input[0]);
		for(int i = 0 ; i < len ; i++) {
			temp[2*i+1] = input[i]; 
		}
		boolean isPal = true;
		boolean isAllSame = true;
		for(int i = 0 ; i <= len ; i++) {
			if(temp[i]!=temp[len+len-i]) {
				isPal = false;
				isAllSame = false;
			} else if(temp[i]!=input[0]) {
				isAllSame = false;
			}
		}
		if(isAllSame) {
			System.out.print(-1);
		} else if(isPal) {
			System.out.print(len-1);
		} else {
			System.out.print(len);
		}
	}
}