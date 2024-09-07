import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.TreeSet;

/**
 * 메모리:19,404KB, 시간:128ms
 *
 */
public class Solution {
	static int N, K, sideLen;
	static TreeSet<Integer> treeSet = new TreeSet<>((a,b)-> b-a);
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			sideLen = N/4;//한 변의 길이
			treeSet.clear();
			char c[] = br.readLine().toCharArray();
			int[] arr = new int[N];
			for(int i = 0 ; i < N ; i++) {//각 단위 10진수 변환
				if(c[i]<='9') {
					arr[i] = c[i]-'0';
				}
				else {
					arr[i] = c[i]-'A'+10;
				}
			}
			int num = 0;//한 변을 10진수로 나타낸 수
			for(int i = 0; i < sideLen ; i++) {
				num = (num<<4)|arr[i];
			}
			treeSet.add(num);
			int leftShift = (sideLen-1)*4;
			for(int i = N-1 ; i > 0 ; i--) {
				num>>=4;
				num+=arr[i]<<leftShift;
			    treeSet.add(num);
			}
			List<Integer> list = new ArrayList<>(treeSet);
			sb.append('#').append(tc).append(' ').append(list.get(K-1)).append('\n');
		}
		System.out.println(sb);
	}

}