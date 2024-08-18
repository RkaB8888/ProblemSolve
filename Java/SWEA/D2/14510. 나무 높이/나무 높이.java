import java.util.Arrays;

 
public class Solution {
 
    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        int T = readInt();
        for (int test_case = 1; test_case <= T; test_case++) {
            int treeN = readInt();
            int tree[] = new int[treeN];
            int max = 0;
            for (int i = 0; i < treeN; i++) {
                tree[i] = readInt();
                if (tree[i] > max)
                    max = tree[i];
            }
            for (int i = 0; i < treeN; i++) {
                tree[i] = max - tree[i];
            }
            Arrays.sort(tree);
            /////////////////////입력 처리 끝//////////////////////////////////////
            int result = 0;
            boolean day = true; // 홀수가 트루
            int i = 0;
            while (i < treeN) {
                if (tree[i] == 0) {// 차이가 없으면 그냥 넘어감
                    i++;
                    continue;
                }
                if (day) {// 홀수날 1을 준다.
                    if (tree[i] > 2) {
                        tree[i]--;
                        result++; // 물 준 횟수 +1
                        day ^= true; // 다음 날로 변경
                    } else {
                        process(tree, i, treeN, 1);
                        result++;
                        day ^= true; // 다음 날로 변경
                    }
                } else { // 짝수날 2를 준다.
                    if (tree[i] > 2) {
                        tree[i] -= 2;
                        result++; // 물 준 횟수 +1
                        day ^= true; // 다음 날로 변경
                    } else {
                        process(tree, i, treeN, 2);
                        result++;
                        day ^= true; // 다음 날로 변경
                    }
                }
            }
            sb.append('#').append(test_case).append(' ').append(result).append('\n');
        } // 테스트 끝
        System.out.println(sb);
    }
 
    public static void process(int[] tree, int idx, int treeN, int num) { // num은 물을 줘야 하는 값
        for (int i = idx; i < treeN; i++) {
            if (tree[i] == num) { // 동일하다면 그냥 0으로 만들어 버림
                tree[i] -= num;
                return;
            }
        }
        for (int i = idx; i < treeN; i++) { 
            if (tree[i] > 2) { // 이후에 2보다 큰 값에다가 num을 처리함
                tree[i] -= num;
                return;
            }
        }
        if (num == 2) // 여기까지 왔는데 num이 2라면 방법이 없으니 그냥 넘어감
            return;
        int cnt2 = 0;
        for (int i = idx; i < treeN; i++) {
            if (tree[i] == 2)
                cnt2++; // 2가 있는 갯수를 세고
        }
        if ((cnt2 - 1) % 3 == 0) // 2는 3묶음 단위로 그냥 처리 가능하니 넘어감
            return;
        for (int i = idx; i < treeN; i++) {
            if (tree[i] > num) { // 2를 만나면 num을 빼줌
                tree[i] -= num;
                return;
            }
        }
    }
    public static int readInt() throws Exception {
	    int val = 0;
	    int c = System.in.read();
	    while (c <= ' ') {  // 공백 문자 건너뛰기
	        c = System.in.read();
	    }
	    do {
	        val = 10 * val + c - 48;  // 아스키 값으로 숫자 변환
	    } while ((c = System.in.read()) >= 48 && c <= 57);

	    return val;  // 양수 반환
	}
 
}