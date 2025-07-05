import java.io.*;

public class Main {
    //완전 탐색 : 숫자를 1씩 증가시키며 구성될 수 있는지 확인한다.
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] input = br.readLine().toCharArray();
        int N = 1, i = 0;
        while (i < input.length) {
            char[] digits = Integer.toString(N).toCharArray();
            for (char digit : digits) {
                if (i < input.length && digit == input[i]) {
                    i++;
                }
            }
            N++;
        }
        System.out.print(N-1);
    }
}

