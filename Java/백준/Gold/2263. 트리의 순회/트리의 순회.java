import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
/**
 * 
 * @author python98
 * 재귀 구현
 * 50,404 KB 4260 ms
 */
public class Main {
	static int N, inOrder[], postOrder[];
	static List<Integer> preOrder;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		inOrder = new int[N+1];
		postOrder = new int[N+1];
		preOrder = new ArrayList<>();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
            inOrder[i] = Integer.parseInt(st.nextToken());
        }
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
            postOrder[i] = Integer.parseInt(st.nextToken());
        }
		makeTree(1, N, 1, N);//해당 범위의 트리 구성
		
		for(int i : preOrder) {
			sb.append(i).append(' ');
		}
		System.out.println(sb);
	}

	  // inStart: 현재 inOrder 배열의 시작, inEnd: inOrder 배열의 끝
    // postStart: 현재 postOrder 배열의 시작, postEnd: postOrder 배열의 끝
    private static void makeTree(int inStart, int inEnd, int postStart, int postEnd) {
        if (inStart > inEnd || postStart > postEnd) {
            return;  // 범위가 잘못된 경우 리턴
        }

        int rootNode = postOrder[postEnd];  // postOrder의 마지막 값이 루트 노드
        preOrder.add(rootNode);  // 루트 노드를 preOrder에 추가

        // inOrder에서 루트 노드의 인덱스 찾기
        int rootIdxIn = -1;
        for (int i = inStart; i <= inEnd; i++) {
            if (inOrder[i] == rootNode) {
                rootIdxIn = i;
                break;
            }
        }

        // 왼쪽 서브트리의 노드 개수 계산
        int leftSubtreeSize = rootIdxIn - inStart;

        // 왼쪽 서브트리 처리
        makeTree(inStart, rootIdxIn - 1, postStart, postStart + leftSubtreeSize - 1);

        // 오른쪽 서브트리 처리
        makeTree(rootIdxIn + 1, inEnd, postStart + leftSubtreeSize, postEnd - 1);
    }

}