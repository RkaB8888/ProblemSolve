import java.io.*;

public class Main {
    //완전 탐색 : 숫자를 1씩 증가시키며 구성될 수 있는지 확인한다.
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] input = br.readLine().toCharArray();
        int len = input.length, N = 0, i = 0;
        while(i < len){
            N++;
            char[] num = Integer.toString(N).toCharArray();
            int numLen = num.length, j = 0;
            while(i < len && j < numLen) {
                if(num[j] == input[i]) {
                    j++;
                    i++;
                } else {
                    j++;
                }
            }
        }
        System.out.print(N);
    }
}

