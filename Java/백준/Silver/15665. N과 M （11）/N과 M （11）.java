import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/**
 * 
 * 조합 메모리 ? KB 시간 ? ms
 * 
 * @author python98
 *
 */
public class Main {
	static int N, M, uniqueLen;
	static int[] temp, arr1, arr2;
	static StringBuilder sb;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		temp = new int[N];
		arr2 = new int[M];
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; i++) {
			temp[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(temp);
		for(int i = 1 ; i < N ; i++) {
			if(temp[uniqueLen]!=temp[i]) {
				temp[++uniqueLen] = temp[i];
			}
		}
		uniqueLen++;
		arr1 = new int[uniqueLen];
		System.arraycopy(temp, 0, arr1, 0, uniqueLen);
		combination(0);
		System.out.print(sb);
	}
	private static void combination(int depth) {
		if(depth==M) {
			for(int num : arr2) {
				sb.append(num).append(' ');
			}
			sb.append('\n');
			return;
		}
		for(int i = 0 ; i < uniqueLen ; i++) {
			arr2[depth] = arr1[i];
			combination(depth+1);
		}
		arr2[depth] = 0;
	}
}