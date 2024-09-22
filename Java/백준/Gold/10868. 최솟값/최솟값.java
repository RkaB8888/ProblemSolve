import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * ? KB ? ms
 * 세그먼트 트리
 */

public class Main {
	static int N, M;
	static long[] numList, minSegTree;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int treeLen = 1;
		while(treeLen<N) {
			treeLen<<=1;
		}
		numList = new long[N];
		minSegTree = new long[2*treeLen];
		for (int i = 0; i < N; i++) {
			numList[i] = Long.parseLong(br.readLine());
		}
		setMin(0, 0, N);
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int low = Integer.parseInt(st.nextToken())-1;
			int up = Integer.parseInt(st.nextToken());
			sb.append(findMin(0, low, up, 0, N)).append('\n');
		}
		System.out.println(sb);
	}

	private static long findMin(int curIdx, int low, int up, int start, int end) {
		int mid = (start+end)>>1;
		if (low <= start && up >= end) {// 주어진 범위를 포함
			return minSegTree[curIdx];
		} else if (low > start && up >= end) {
			if(mid<=low) {
				return findMin(curIdx*2+2, low, up, mid, end);
			}
			else {
				return Math.min(findMin(curIdx*2+2, low, up, mid, end), findMin(curIdx*2+1, low, up, start, mid));
			}
		} else if (low <= start && up < end) {
			if(mid>=up) {
				return findMin(curIdx*2+1, low, up, start, mid);
			}
			else {
				return Math.min(findMin(curIdx*2+2, low, up, mid, end), findMin(curIdx*2+1, low, up, start, mid));
			}
		} else {
			if(mid<=low) {
				return findMin(curIdx*2+2, low, up, mid, end);
			}
			else if(mid>=up) {
				return findMin(curIdx*2+1, low, up, start, mid);
			}
			else {
				return Math.min(findMin(curIdx*2+2, low, up, mid, end), findMin(curIdx*2+1, low, up, start, mid));
			}
		}
	}

	private static long setMin(int curIdx, int start, int end) {
		if (start == end - 1)
			return minSegTree[curIdx] = numList[start];
		int mid = (start + end) >> 1;
		return minSegTree[curIdx] = Math.min(setMin(curIdx * 2 + 1, start, mid), setMin(curIdx * 2 + 2, mid, end));
	}

}