import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
/**
 * 
 * @author python98
 * 구현?
 * ? KB ? ms
 */
public class Main {
	static int N, Order[][], inOrder[], postOrder[];
	static List<Integer> preOrder;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		Order = new int[2][N+1];
		inOrder = new int[N+1];
		postOrder = new int[N+1];
		preOrder = new ArrayList<>();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 1 ; i <= N ; i++) {
			int num = Integer.parseInt(st.nextToken());
			Order[0][num] = i;
			inOrder[i] = num;
		}
		st = new StringTokenizer(br.readLine());
		for(int i = 1 ; i <= N ; i++) {
			int num = Integer.parseInt(st.nextToken());
			Order[1][num] = i;
			postOrder[i] = num;
		}
		makeTree(1, N);//해당 범위의 트리 구성
		
		for(int i : preOrder) {
			sb.append(i).append(' ');
		}
		System.out.println(sb);
	}

	private static void makeTree(int str, int end) {
		//해당 범위의 RootNode 찾기
		int rootIdxPost = -1;
		int rootIdxIn = -1;
		for(int i = str ; i <= end ; i++) {
			if(rootIdxPost<Order[1][inOrder[i]]) {
				rootIdxPost = Order[1][inOrder[i]];
				rootIdxIn = i;
			}
		}
		int rootNode = postOrder[rootIdxPost];
		preOrder.add(rootNode);
		
		if(rootIdxIn!=str) {
			makeTree(str,rootIdxIn-1);//인 오더에서 루트 노드의 왼쪽
		}
		if(rootIdxIn!=end) {
			makeTree(rootIdxIn+1,end);//인 오더에서 루트 노드의 오른쪽
		}
	}

}