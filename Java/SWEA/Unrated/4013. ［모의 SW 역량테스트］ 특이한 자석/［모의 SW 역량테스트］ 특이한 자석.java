
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int K;
	static boolean[][] mgList = new boolean[4][8];
	static int[] score = {1,2,4,8};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int testCase = Integer.parseInt(br.readLine());
		for (int t = 1; t <= testCase; t++) {
			K = Integer.parseInt(br.readLine());
			for(int i = 0 ; i < 4 ; i++) {
				st = new StringTokenizer(br.readLine());
				boolean[] mg = mgList[i];
				for(int j = 0 ; j < 8 ; j++) {
					int val = Integer.parseInt(st.nextToken());
					if(val==0)
						mg[j] = false;
					else mg[j] = true;
				}
			}
			for(int i = 0 ; i < K ; i++) {
				st = new StringTokenizer(br.readLine());
				int mgNum = Integer.parseInt(st.nextToken())-1;
				int slidePath = 0;
				if(st.nextToken().equals("1")) {
					rotate(mgNum,true,slidePath);
				} else {
					rotate(mgNum,false,slidePath);
				}
			}
			
			//점수 처리
			int result = 0;
			for(int i = 0 ; i < 4 ; i++) {
				if(mgList[i][0]) result+=score[i];
			}
			
			
			sb.append('#').append(t).append(' ').append(result).append('\n');
		}
		System.out.println(sb);
	}
	//rotatePath true(시계), false(반시계) slidePath -1(왼쪽), 0(양쪽), 1(오른쪽)
	private static void rotate(int idx, boolean rotatePath, int slidePath) {
		
		boolean[] mg = mgList[idx];
		
		//왼쪽 방향 확인
		if(slidePath!=1) {
			if(idx>0) {
				if(mg[6]!=mgList[idx-1][2]) {
					//반대 방향으로 회전
					rotate(idx-1,!rotatePath,-1);
				}
			}
			
		}
		//오른쪽 방향 확인
		if(slidePath!=-1) {
			if(idx<3) {
				if(mg[2]!=mgList[idx+1][6]) {
					//반대 방향으로 회전
					rotate(idx+1,!rotatePath,1);
				}
			}
		}
		
		//본인을 회전시킨다.
		if(rotatePath) { // 시계
			boolean temp = mg[7];
			for(int i = 7 ; i > 0 ; i--) {
				mg[i] = mg[i-1];
			}
			mg[0] = temp;
		}
		else { // 반시계
			boolean temp = mg[0];
			for(int i = 0 ; i < 7 ; i++) {
				mg[i] = mg[i+1];
			}
			mg[7] = temp;
		}
	}
}