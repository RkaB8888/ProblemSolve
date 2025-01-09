import java.io.*;

/**
 * @description 대규모 문자열에 대한 크로아티아 알파벳 변환
 * @performance 메모리: ? KB, 동작시간: ? ms
 * @author python98
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 크로아티아 알파벳 패턴 정의
        String input = br.readLine();
        int length = input.length();
        int count = 0;

        for (int i = 0; i < length; i++) {
            count++; // 기본적으로 한 글자
            if (i < length - 1) {
                // 두 글자 패턴 확인
                if (input.charAt(i) == 'c' && (input.charAt(i + 1) == '=' || input.charAt(i + 1) == '-')) {
                    i++;
                } else if (input.charAt(i) == 'd') {
                    if (i < length - 2 && input.charAt(i + 1) == 'z' && input.charAt(i + 2) == '=') {
                        i += 2;
                    } else if (input.charAt(i + 1) == '-') {
                        i++;
                    }
                } else if (input.charAt(i) == 'l' && input.charAt(i + 1) == 'j') {
                    i++;
                } else if (input.charAt(i) == 'n' && input.charAt(i + 1) == 'j') {
                    i++;
                } else if (input.charAt(i) == 's' && input.charAt(i + 1) == '=') {
                    i++;
                } else if (input.charAt(i) == 'z' && input.charAt(i + 1) == '=') {
                    i++;
                }
            }
        }

        System.out.print(count);
    }
}