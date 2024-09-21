import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;



public class Main {
	static int N, leftCnt, rightCnt, A[], B[], C[], D[];
	static long result;
	static int[] leftList, rightList;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		leftCnt = 0; rightCnt = 0;
		leftList = new int[N*N];
		rightList = new int[N*N];
		A = new int[N];
		B = new int[N];
		C = new int[N];
		D = new int[N];
		for(int i = 0 ; i < N ; i++) {
			st = new StringTokenizer(br.readLine());
			A[i] = Integer.parseInt(st.nextToken());
			B[i] = Integer.parseInt(st.nextToken());
			C[i] = Integer.parseInt(st.nextToken());
			D[i] = Integer.parseInt(st.nextToken());
		}
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < N ; j++) {
				leftList[leftCnt++] = A[i]+B[j];
				rightList[rightCnt++] = C[i]+D[j];
			}
		}
		int left = 0, right = rightCnt-1;
		Arrays.sort(leftList);
		Arrays.sort(rightList);
		while(left<leftCnt&&right>=0) {
			int leftNum = leftList[left];
			int rightNum = rightList[right];
			if(leftNum+rightNum>0) {
				right--;
			}
			else if(leftNum+rightNum<0) {
				left++;
			}
			else {
				int lCnt = 0, rCnt = 0;
				while(left<leftCnt&&leftNum==leftList[left]) {
					lCnt++;
					left++;
				}
				while(right>=0&&rightNum==rightList[right]) {
					rCnt++;
					right--;
				}
				result+=(long)lCnt*rCnt;
			}
		}
		System.out.println(result);
	}

}