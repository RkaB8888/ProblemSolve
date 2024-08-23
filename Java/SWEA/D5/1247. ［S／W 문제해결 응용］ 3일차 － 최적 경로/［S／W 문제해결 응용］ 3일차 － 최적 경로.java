import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 메모리:24,528KB, 시간:669ms
 */
public class Solution {
	static int min;// 최소 거리
	static int N;// 고객 수
	static int[][] map;// 각자의 거리 미리 계산
	static int[][] lengths;// 입력받은 좌표들 N-2:회사 N-1:집
	static int[] permutation;//0~N-1까지의 숫자가 들어있는  배열
	static int sum;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			min = Integer.MAX_VALUE;
			N = Integer.parseInt(br.readLine());
			N+=2;
			map = new int[N][N];
			lengths = new int[N][2];
			permutation = new int[N];
			for(int i = 0 ; i < N ; i++) {
				permutation[i] = i;
			}
			st = new StringTokenizer(br.readLine());
			lengths[N-2][0] = Integer.parseInt(st.nextToken());//N-2이 회사 좌표
			lengths[N-2][1] = Integer.parseInt(st.nextToken());
			lengths[N-1][0] = Integer.parseInt(st.nextToken());//N-1이 집 좌표
			lengths[N-1][1] = Integer.parseInt(st.nextToken());
			for (int i = 0, iEnd = N-2; i < iEnd; i++) {//0~N-3까지가 고객
				lengths[i][0] = Integer.parseInt(st.nextToken());
				lengths[i][1] = Integer.parseInt(st.nextToken());
			}
			//////////// 처리 과정 시작///////////////////////////
			//먼저 서로간의 거리를 미리 계산한다.
			for(int i = 0,iEnd = N-1; i < iEnd ; i++) {
				for(int j = i+1 ; j < N ; j++) {
					int length = Math.abs(lengths[i][0]-lengths[j][0])+Math.abs(lengths[i][1]-lengths[j][1]);
					map[i][j] = length;
					map[j][i] = length;
				}
			}
			do {
				sum = 0;
				loop(0,N-2);//N-2가 이전 방문지를 회사로 설정한 것
			}while(np());
			//////////// 처리 과정 끝 ///////////////////////////
			sb.append("#").append(tc).append(" ").append(min).append("\n");
		}
		System.out.println(sb);
	}
	public static boolean np() {
		int i = N-3;
		while(i>0&&permutation[i-1]>=permutation[i]) {
			i--;
		}
		if(i==0) return false;
		int j = N-3;
		while(i<j&&permutation[i-1]>=permutation[j]) {
			j--;
		}
		swap(i-1,j);
		j=N-3;
		while(i<j) {
			swap(i,j);
			i++;
			j--;
		}
		return true;
	}
	public static void swap(int a, int b) {
		permutation[a]^=permutation[b];
		permutation[b]^=permutation[a];
		permutation[a]^=permutation[b];
	}
	public static void loop(int cnt , int pre) {//방문 횟수, 이전 방문지, 지금까지의 거리 합
		if(min<=sum) return;//더 이상 가치 없음
		if(cnt==N-2) {//모든 방문지를 돌았다면
			sum+=calc(permutation[N-1],permutation[cnt-1]);
			if(min>sum) {
				min = sum;
			}
			return;
		}
		sum+=map[permutation[cnt]][permutation[pre]];
		loop(cnt+1,cnt);
	}
	//거리를 반환해주는 메소드
	public static int calc(int cur, int pre) {
		return map[pre][cur];
	}

}