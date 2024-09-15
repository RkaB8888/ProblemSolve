import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 *  ?kb ?ms
 */
public class Solution {
	static int N, L, result, map[][];
	static final int drdc[][] = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		int TC = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc <= TC; tc++) {
			result = 0;
			st = new StringTokenizer(br.readLine().trim());
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine().trim());
				for(int j = 0 ; j < N ; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			dfs();
			sb.append('#').append(tc).append(' ').append(result).append('\n');
		}
		System.out.println(sb);
	}
	public static void dfs() {
		for(int i = 0 ; i < N ; i++) {
			if(checkHori(i)) {
//				System.out.println(i+"번 행 가능");
				result++;
			}
			if(checkVerti(i)) {
//				System.out.println(i+"번 열 가능");
				result++;
			}
		}
	}
	public static boolean checkHori(int row) {
		int preNum = map[row][0], cnt = L-1;
		boolean check[] = new boolean[N];
		boolean flag = false;
		for(int i = 1 ; i < N ; i++) {
			if(flag) {
				if(map[row][i]!=preNum) {//경사로 설치했는데 연속이지 않으면
					return false;
				}
				else {//경사로 설치했는데 연속적이면
					if(check[i]) return false;
					check[i] = true;
					cnt--;
				}
				if(cnt==0) {//경사로 설치 성공적
					flag = false;
				}
			}
			else {
				if(map[row][i]<preNum-1) {//2칸 이상 낮으면
					return false;
				}
				else if(map[row][i]==preNum-1) {//1칸 낮으면
					if(check[i]) return false;//이미 설치함 
					check[i] = true;
					flag = true;
					cnt = L-1;
				}
				preNum = map[row][i];//이전 높이 갱신
			}
		}
		if(flag) return false;//경사로가 경계 밖을 넘으면
		preNum = map[row][N-1];
		for(int i = N-2 ; i >= 0 ; i--) {//반대 방향으로 확인
			if(flag) {//경사로 설치
				if(map[row][i]!=preNum) {
					return false;
				}
				else {
					if(check[i]) return false;//이미 설치함 
					check[i] = true;
					cnt--;
				}
				if(cnt==0) {
					flag = false;
				}
			}
			else {
				if(map[row][i]<preNum-1) {
					return false;
				}
				else if(map[row][i]==preNum-1) {
					if(check[i]) return false;//이미 설치함 
					check[i] = true;
					flag = true;
					cnt = L-1;
				}
				preNum = map[row][i];
			}
		}
		if(flag) return false;
		return true;
	}
	
	public static boolean checkVerti(int col) {
		int preNum = map[0][col], cnt = L-1;
		boolean check[] = new boolean[N];
		boolean flag = false;
		for(int i = 1 ; i < N ; i++) {
			if(flag) {
				if(map[i][col]!=preNum) {//경사로 설치했는데 연속이지 않으면
					return false;
				}
				else {//경사로 설치했는데 연속적이면
					if(check[i]) return false;//이미 설치함 
					check[i] = true;
					cnt--;
				}
				if(cnt==0) {//경사로 설치 성공적
					flag = false;
				}
			}
			else {
				if(map[i][col]<preNum-1) {//2칸 이상 낮으면
					return false;
				}
				else if(map[i][col]==preNum-1) {//1칸 낮으면
					if(check[i]) return false;//이미 설치함 
					check[i] = true;
					flag = true;
					cnt = L-1;
				}
				preNum = map[i][col];//이전 높이 갱신
			}
		}
		if(flag) return false;//경사로가 경계 밖을 넘으면
		preNum = map[N-1][col];
		for(int i = N-2 ; i >= 0 ; i--) {//반대 방향으로 확인
			if(flag) {//경사로 설치
				if(map[i][col]!=preNum) {
					return false;
				}
				else {
					if(check[i]) return false;//이미 설치함 
					check[i] = true;
					cnt--;
				}
				if(cnt==0) {
					flag = false;
				}
			}
			else {
				if(map[i][col]<preNum-1) {
					return false;
				}
				else if(map[i][col]==preNum-1) {
					if(check[i]) return false;//이미 설치함 
					check[i] = true;
					flag = true;
					cnt = L-1;
				}
				preNum = map[i][col];
			}
		}
		if(flag) return false;
		return true;
	}
}