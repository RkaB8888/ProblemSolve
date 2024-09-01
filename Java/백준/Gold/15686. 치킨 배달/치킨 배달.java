import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * 메모리:13,060KB, 164ms
 * 치킨집 M개를 선택했을 때 각 집마다 최소 거리를 갱신
 * 각 집에서의 최소 거리의 합을 출력
 */
public class Main {
	static int N, M, minSum, homeCnt, chickenCnt;
	static Home[] homes;
	static Chicken[] chickens;
	static int[] list;
	static class Home{
		int i, j;
		int[] length;

		public Home(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}
		public void setLength(int len) {
			this.length = new int[len];
		}
		public void calcLength(Chicken c) {
			length[c.num] = Math.abs(c.i-this.i)+Math.abs(c.j-this.j);
		}
		
	}
	static class Chicken{
		int i, j, num;

		public Chicken(int i, int j, int num) {
			super();
			this.i = i;
			this.j = j;
			this.num = num;
		}
		
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		minSum = Integer.MAX_VALUE;
		homes = new Home[2*N];
		chickens = new Chicken[13];
		for(int i = 0 ; i < N ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < N ; j++) {
				int temp = Integer.parseInt(st.nextToken());
				if(temp == 1) {
					homes[homeCnt++] = new Home(i,j);
				}
				else if(temp==2) {
					chickens[chickenCnt] = new Chicken(i, j, chickenCnt);
					chickenCnt++;
				}
			}
		}
		homes = Arrays.copyOf(homes, homeCnt);
		chickens = Arrays.copyOf(chickens, chickenCnt);
		for(int i = 0 ; i < homeCnt ; i++) {
			homes[i].setLength(chickenCnt);
		}
		for(int i = 0 ; i < chickenCnt ; i++) {
			Chicken temp = chickens[i];
			for(int j = 0 ; j < homeCnt ; j++) {
				homes[j].calcLength(temp);
			}
		}
		list= new int[chickenCnt];
		for(int i =chickenCnt-1, cnt = 0 ; i >=0&&cnt<M ; i--,cnt++) {
			list[i] = 1;
		}
		int result;
		do {
			result = calc();
			if(minSum>result) minSum = result;
		}while(np());
		System.out.println(minSum);
	}
	public static boolean np() {
		int i = chickenCnt-1;
		while(i>0&&list[i-1]>=list[i]) {
			i--;
		}
		if(i==0) return false;
		int j = chickenCnt-1;
		while(list[i-1]>=list[j]) {
			j--;
		}
		swap(i-1,j);
		j = chickenCnt-1;
		while(i<j) {
			swap(i,j);
			i++; j--;
		}
		return true;
	}
	public static void swap(int a, int b) {
		list[a]^=list[b];
		list[b]^=list[a];
		list[a]^=list[b];
	}
	public static int calc() {
		int sum = 0;
		int[] minList = new int[homeCnt];
		Arrays.fill(minList, Integer.MAX_VALUE);
		for(int i = 0, cnt = 0 ; i < chickenCnt&&cnt<M ; i++) {
			if(list[i]==1) {
				cnt++;
				for(int j = 0 ; j < homeCnt ; j++) {
					minList[j]=Math.min(minList[j],homes[j].length[i]);
				}
			}
		}
		for(int i = 0 ; i < homeCnt ; i++) {
			sum+=minList[i];
		}
		return sum;
	}
}