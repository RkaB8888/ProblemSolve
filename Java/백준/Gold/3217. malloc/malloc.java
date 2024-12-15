import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;
/**
 * TreeSet, Greedy 메모리 최적화
 * 
 * @author python98
 */
public class Main {
    static int N;
    static String cmd;
    static StringBuilder sb;
    static Map<String, int[]> vars; // 이름, 주소
    static TreeSet<int[]> freeBlocks; // 빈 공간 관리

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        vars = new HashMap<>();
        freeBlocks = new TreeSet<>((a, b) -> a[0] - b[0]); // 시작 주소 기준 정렬
        freeBlocks.add(new int[] { 1, 100000 }); // 초기 전체 메모리 블록
        N = Integer.parseInt(br.readLine());

        String var;
        for (int i = 0; i < N; i++) {
            cmd = br.readLine();

            if (cmd.startsWith("free(")) {
                var = cmd.substring(5, 9);
                vars.putIfAbsent(var, new int[] { 0, 0 });
                free(vars.get(var));
                vars.put(var, new int[] { 0, 0 });
            } else if (cmd.startsWith("print(")) {
                var = cmd.substring(6, 10);
                vars.putIfAbsent(var, new int[] { 0, 0 });
                sb.append(vars.get(var)[0]).append('\n');
            } else { // malloc
                var = cmd.substring(0, 4);
                int size = Integer.parseInt(cmd.substring(12, cmd.length() - 2));
                vars.putIfAbsent(var, new int[] { 0, 0 });
                malloc(size, vars.get(var));
            }
        }
        System.out.print(sb);
    }

    private static void malloc(int size, int[] block) {
        // TreeSet을 순회하며 빈 공간을 탐색
        for (int[] curBlock : freeBlocks) {
            if (curBlock[1] - curBlock[0] + 1 >= size) {
                block[0] = curBlock[0];
                block[1] = curBlock[0] + size - 1;

                freeBlocks.remove(curBlock); // 기존 블록 제거

                if (block[1] < curBlock[1]) {
                    freeBlocks.add(new int[] { block[1] + 1, curBlock[1] }); // 남은 공간 삽입
                }
                return;
            }
        }
        block[0] = 0; // 할당 실패
        block[1] = 0;
    }

    public static void free(int[] block) {
        if (block[0] == 0 && block[1] == 0) return; // 할당되지 않은 메모리 처리

        // TreeSet에서 병합 가능성을 확인하며 삽입
        int[] lower = freeBlocks.floor(new int[] { block[0], 0 });
        int[] higher = freeBlocks.ceiling(new int[] { block[1], 0 });

        if (lower != null && lower[1] + 1 == block[0]) { // 이전 블록과 병합
            freeBlocks.remove(lower);
            block[0] = lower[0];
        }

        if (higher != null && block[1] + 1 == higher[0]) { // 다음 블록과 병합
            freeBlocks.remove(higher);
            block[1] = higher[1];
        }

        freeBlocks.add(block); // 병합된 결과 삽입
    }
}