import java.io.*;
import java.util.*;

// 초기 위치 : 0, 0, 동쪽
// turn 0 -> 왼쪽 90도 회전, turn 1 -> 오른쪽 90도 회전
public class Main {
    static int M, n;
    static boolean flag = true;
    static int curX = 0, curY = 0, curZ = 0;

    static final int[][] DIR = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            String input = br.readLine();
            if (flag) flag = inputCommand(input.split(" "));
        }
        if (flag) System.out.print(curX + " " + curY);
        else System.out.print(-1);
    }

    private static boolean inputCommand(String[] input) {
        if (input[0].equals("MOVE")) {
            int cnt = Integer.parseInt(input[1]);
            for (int i = 0; i < cnt; i++) {
                curX += DIR[curZ][0];
                curY += DIR[curZ][1];
            }
        } else {
            if (Integer.parseInt(input[1]) == 0) curZ = (curZ + 1) % 4;
            else curZ = (curZ + 3) % 4;
        }

        return curX <= M && curY <= M && curX >= 0 && curY >= 0;
    }
}