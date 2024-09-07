import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Solution {
    static int N, K, sideLen;
    static TreeSet<Integer> treeSet = new TreeSet<>((a, b) -> b - a);

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            sideLen = N / 4; // 한 변의 길이
            treeSet.clear();
            
            // 16진수로 이루어진 문자열을 배열로 변환
            char[] c = br.readLine().toCharArray();
            
            // 각 회전마다 새로운 수를 추가
            for (int start = 0; start < sideLen; start++) {
                for (int i = 0; i < 4; i++) {  // 각 변을 10진수로 변환
                    int num = 0;
                    for (int j = 0; j < sideLen; j++) {
                        char ch = c[(start + i * sideLen + j) % N];
                        if (ch <= '9') {
                            num = (num << 4) + (ch - '0'); // 숫자면
                        } else {
                            num = (num << 4) + (ch - 'A' + 10); // 문자면
                        }
                    }
                    treeSet.add(num); // 회전한 후의 16진수를 treeSet에 추가
                }
            }

            // TreeSet을 통해 내림차순으로 정렬된 상태에서 K번째로 큰 값을 찾음
            int count = 0;
            int result = 0;
            for (int num : treeSet) {
                count++;
                if (count == K) {
                    result = num;
                    break;
                }
            }
            sb.append('#').append(tc).append(' ').append(result).append('\n');
        }
        System.out.println(sb);
    }
}