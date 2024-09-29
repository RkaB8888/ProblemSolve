import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author python98 세그먼트 트리 ? KB ? ms
 *
 */

public class Main {
	static int N, M, K, arr[], treeLen, len, divideNum;
	static long segmentTree[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		arr = new int[N + 1];
		len = 1;
		divideNum = 1000000007;
		while (len < N) {
			len <<= 1;
		}
		treeLen = 2 * len;
		segmentTree = new long[treeLen+1];
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		createTree(1, len, 1);
		
		for (int i = 0, iEnd = M+K; i < iEnd; i++) {
			st = new StringTokenizer(br.readLine());
			int queryType = Integer.parseInt(st.nextToken());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            
			if (queryType==1) {
				changeNode(A,B,1,1,len);
			} else {
				sb.append(calcPart(A, B, 1, 1, len)).append('\n');
			}
		}

		System.out.println(sb);
	}

	private static long changeNode(int idx, int num, int cur_idx, int str, int end) {
		if (cur_idx > treeLen) return 1;
		if(str==end) {
			return segmentTree[cur_idx]=num;
		}
		int mid = (str+end)>>1;
		if(mid<idx) {
			return segmentTree[cur_idx] = (segmentTree[cur_idx*2]*changeNode(idx,num,cur_idx*2+1,mid+1,end))%divideNum;
		} else {
			return segmentTree[cur_idx] = (changeNode(idx,num,cur_idx*2, str ,mid)*segmentTree[cur_idx*2+1])%divideNum;
		}
		
	}

	private static long calcPart(int a, int b, int cur_idx, int str, int end) {
		if (cur_idx > treeLen) return 1;
	
		if(str >= a && end <= b) return segmentTree[cur_idx];
		
		if(end < a || str > b) return 1;
			
		int mid = (str + end) >> 1;
		return (calcPart(a, b, cur_idx*2, str, mid)*calcPart(a, b, cur_idx*2+1, mid+1, end))%divideNum;
	}

	public static void createTree(int str, int end, int cur_idx) {
		if(cur_idx > treeLen) return;
		if (str == end) {
			if (str <= N) {
				segmentTree[cur_idx] = arr[str];
			} else {
				segmentTree[cur_idx] = 1;
			}
			return;
		}
		int mid = (str + end) >> 1;
		createTree(str, mid, cur_idx * 2);
		createTree(mid + 1, end, cur_idx * 2 + 1);
		segmentTree[cur_idx] = (segmentTree[cur_idx * 2] * segmentTree[cur_idx * 2 + 1]) % divideNum;
	}

}