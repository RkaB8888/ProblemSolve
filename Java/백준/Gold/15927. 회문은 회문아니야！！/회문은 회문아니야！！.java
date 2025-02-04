import java.io.*;

/**
 * @description 그리디 최적화
 * @performance 메모리: ? KB, 동작시간: ? ms
 * @author python98
 */
public class Main {
    static char[] input;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input = br.readLine().toCharArray();
        int len = input.length;

        boolean isPal = true;
        boolean isAllSame = true;

        for (int i = 0; i <= len / 2; i++) {
            if (input[i] != input[len - 1 - i]) {
                isPal = false;
                isAllSame = false;
                break;
            } else if (input[i] != input[0]) {
                isAllSame = false;
            }
        }

        if (isAllSame) {
            System.out.print(-1);
        } else if (isPal) {
            System.out.print(len - 1);
        } else {
            System.out.print(len);
        }
    }
}