import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Solution {
    static int N; // 단어 수
    static int result;
    static List<Integer> bitmaskList = new LinkedList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        // 입력 시작
        int testCase = Integer.parseInt(br.readLine());
        for (int t = 1; t <= testCase; t++) {
            bitmaskList.clear();
            N = Integer.parseInt(br.readLine());
            result = 0;
            for (int i = 0; i < N; i++) {
                String temp = br.readLine();
                int bitmask = 0;
                for (int j = 0; j < temp.length(); j++) {
                    char c = temp.charAt(j);
                    if ('a' <= c && c <= 'z') {
                        bitmask |= (1 << (c - 'a'));
                    }
                }
                bitmaskList.add(bitmask);
            }
            // 입력 끝
            
            loop(0, 0);

            sb.append('#').append(t).append(' ').append(result).append('\n');
        }
        System.out.println(sb);
    }

    // 비트 마스크를 사용하여 모든 조합을 탐색
    public static void loop(int cnt, int total) {
        if (cnt == N) {
            if (Integer.bitCount(total) == 26) { // 26개 비트가 모두 설정되어 있는지 확인
                result++;
            }
            return;
        }
        // 현재 단어를 포함하지 않는 경우
        loop(cnt + 1, total);
        // 현재 단어를 포함하는 경우
        loop(cnt + 1, total | bitmaskList.get(cnt));
    }
}
