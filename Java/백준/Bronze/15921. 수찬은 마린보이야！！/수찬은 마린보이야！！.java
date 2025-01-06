import java.io.*;
//import java.util.*;

/**
 * @description 수학
 * @performance 메모리: ? KB, 동작시간: ? ms
 * @author python98
 */
public class Main {
	static int N;
//	static int[] score;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		if(N==0) {
			System.out.print("divide by zero");
			return;
		}
//		score = new int[N];
//		StringTokenizer st = new StringTokenizer(br.readLine());
//		for(int i = 0 ; i < N ; i++) {
//			score[i] = Integer.parseInt(st.nextToken());
//			sum += score[i];
//		}
		System.out.print("1.00");
	}
}