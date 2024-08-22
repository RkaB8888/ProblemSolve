import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *넥퍼를 이용한 연산
 *
 */
public class Solution {
	
	static int N;
	static int[] Operand;
	static int[] Operator = new int[4]; // + - * /
	static int[] Permutation;
	static int PermutationLen;
	static int max, min;
	static int result;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			
			N = Integer.parseInt(br.readLine());
			Operand = new int[N];
			PermutationLen = N-1;
			Permutation = new int[PermutationLen];
			max = Integer.MIN_VALUE;
			min = Integer.MAX_VALUE;
			
			st = new StringTokenizer(br.readLine());
			int pCnt = 0;
			for(int i = 0 ; i < 4 ; i++) {
				Operator[i] = Integer.parseInt(st.nextToken());
				for(int j = 0, jEnd = Operator[i];j<jEnd;j++) {
					Permutation[pCnt++] = i;
				}
			}
			st = new StringTokenizer(br.readLine());
			for(int i = 0 ; i < N ; i++) {
				Operand[i] = Integer.parseInt(st.nextToken());
			}
			int cnt;
			int start = Operand[0];
			do {
				result = start;
				cnt = 0;
				while(cnt<PermutationLen) {
					switch (Permutation[cnt]) {
					case 0:
						result+=Operand[cnt+1];
						break;
					case 1:
						result-=Operand[cnt+1];
						break;
					case 2:
						result*=Operand[cnt+1];
						break;
					case 3:
						result/=Operand[cnt+1];
						break;
					}
					cnt++;
				}
				if(result<min) min = result;
				if(result>max) max = result;
			}while(np());
			sb.append('#').append(tc).append(' ').append(max-min).append('\n');
		}
		System.out.println(sb);
	}
	
	public static boolean np() {
		int i = PermutationLen-1;
		while(i>0&&Permutation[i]<=Permutation[i-1]) {
			i--;
		}
		if(i==0) return false; // 순열 생성 끝
		int j = PermutationLen-1;
		while(Permutation[i-1]>=Permutation[j]) {
			j--;
		}
		swap(i-1,j);
		j = PermutationLen-1;
		while(i<j) {
			swap(i,j);
			i++;j--;
		}
		return true;
		
	}
	public static void swap(int a, int b) {
		Permutation[a]^=Permutation[b];
		Permutation[b]^=Permutation[a];
		Permutation[a]^=Permutation[b];
	}
}