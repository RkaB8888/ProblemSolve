import java.io.*;
import java.util.*;

/**
 * @description
 *    - 구현 :<br>
 * @performance 메모리: ? KB, 동작시간: ? ms
 * @author python98
 */
public class Main {
	static int R, C, Rg, Cg, Rp, Cp, result;
	static char[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][];

		st = new StringTokenizer(br.readLine());
		Rg = Integer.parseInt(st.nextToken());
		Cg = Integer.parseInt(st.nextToken());
		Rp = Integer.parseInt(st.nextToken());
		Cp = Integer.parseInt(st.nextToken());

		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		int pStartR = 0, pStartC = 0;
		out:for (int i = 0 ; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if(map[i][j]=='P') {
					pStartR = i;
					pStartC = j;
					break out;
				}
			}
		}
		
		if(pStartR+Rp<=R&&pStartC+Cp<=C) {
			out:for(int i = pStartR ; i < pStartR+Rp ; i++) {
				for(int j = pStartC ; j < pStartC+Cp ; j++) {
					if(map[i][j]!='P') {
						result = 1;
						break out;
					}
					
				}
			}
		} else {
			result = 1;
		}
		
		System.out.print(result);
	}
}