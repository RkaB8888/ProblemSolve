import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * 
 * @author python98
 * 세그먼트 트리 각 노드에 최소 값인 인덱스를 넣고 갱신
 * ? KB ? ms
 */
public class Main {
	static int N, arr[], M, segTree[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N + 1];
		segTree = new int[4*N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		createTree(1, 1, N);
		
		M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int type = Integer.parseInt(st.nextToken());
			int str = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			if (type == 1) {
				arr[str] = end;
				update(1, 1, N, str);
			} else if (type == 2) {
				sb.append(query(1, 1, N, str, end)).append('\n');
			}
		}
		System.out.println(sb);
	}

	private static int createTree(int curIdx, int start, int end) {
		if (start == end) {
			return segTree[curIdx] = start; // 리프 노드에 인덱스 저장
		}
		int mid = (start + end) / 2;
		int leftIdx = createTree(curIdx * 2, start, mid);
		int rightIdx = createTree(curIdx * 2 + 1, mid + 1, end);
		
		// 더 작은 값의 인덱스를 저장
		if (arr[leftIdx] <= arr[rightIdx]) {
			return segTree[curIdx] = leftIdx;
		} else {
			return segTree[curIdx] = rightIdx;
		}
	}

	private static int update(int curIdx, int start, int end, int idx) {
		if (start == end) {
			return segTree[curIdx] = start;
		}
		int mid = (start + end) / 2;
		int leftIdx, rightIdx;
		
		if (idx <= mid) {
			leftIdx = update(curIdx * 2, start, mid, idx);
			rightIdx = segTree[curIdx * 2 + 1];
		} else {
			leftIdx = segTree[curIdx * 2];
			rightIdx = update(curIdx * 2 + 1, mid + 1, end, idx);
		}
		
		// 더 작은 값의 인덱스를 저장
		if (arr[leftIdx] <= arr[rightIdx]) {
			return segTree[curIdx] = leftIdx;
		} else {
			return segTree[curIdx] = rightIdx;
		}
	}

	private static int query(int curIdx, int start, int end, int a, int b) {
		if (start > b || end < a) {
			return -1;
		}
		
		if (start >= a && end <= b) {
			return segTree[curIdx];
		}
		
		int mid = (start + end) / 2;
		int leftIdx = query(curIdx * 2, start, mid, a, b);
		int rightIdx = query(curIdx * 2 + 1, mid + 1, end, a, b);
		
		if (leftIdx == -1) return rightIdx;
		if (rightIdx == -1) return leftIdx;
		
		if (arr[leftIdx] <= arr[rightIdx]) {
			return leftIdx;
		} else {
			return rightIdx;
		}
	}
}