
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	static int N;
	static int[] weight;
	static int result;
	static int half;
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int testCase = Integer.parseInt(br.readLine());
		for (int t = 1; t <= testCase; t++) {
			N=Integer.parseInt(br.readLine());
			weight = new int[N];
			st = new StringTokenizer(br.readLine());
			for(int i = 0 ; i < N ; i++) {
				weight[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(weight);
			result = 0;
			do {
//				System.out.println(Arrays.toString(weight));
				Subset(0,0,0);//깊이, 왼쪽 무게, 오른쪽 무게
			}while(np());

			sb.append('#').append(t).append(' ').append(result).append('\n');
		}
		System.out.println(sb);
	}
	
	public static boolean np() {
		int i = N-1;
		while(i>0) {
			if(weight[i]>weight[i-1]) {
				break;
			}
			i--;
		}
		
		if(i==0) return false;
		
		int j = N-1;
		while(j>i) {
			if(weight[j]>weight[i-1]) {
				break;
			}
			j--;
		}
		
		swap(i-1,j);
		 j = N-1;
		while(i<j) {
			swap(i++,j--);
		}
		return true;
	}
	
	public static void swap(int a, int b) {
		int temp = weight[a];
		weight[a] = weight[b];
		weight[b] = temp;
//		weight[a]^=weight[b]^=weight[a]^=weight[b];
	}
	
	public static void Subset(int cnt, int leftW, int rightW) {
		if(rightW>leftW) return;
		if(cnt == N) {
			result++;
			return;
		}
			Subset(cnt+1,leftW+weight[cnt],rightW);
			Subset(cnt+1,leftW,rightW+weight[cnt]);
		
	}
	
}