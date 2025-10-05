import java.io.*;
import java.util.*;

/**
 * @author python98
 * @description 그래프 관계 전파 + 인접 행렬 + Queue
 * @performance 메모리: 14,240 KB, 동작시간: 96 ms
 */
public class Main {
    static int N, M;
    static int[][] left, right;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        left = new int[N + 1][N + 1];
        right = new int[N + 1][N + 1];
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            if (left[r][l] == 0) {
                left[r][l] = 1;
                left[r][0]++;
            }
            if (right[l][r] == 0) {
                right[l][r] = 1;
                right[l][0]++;
            }
        }
        int digit = 1<<7;
        int[] q = new int[digit--];
        int b = 0, t = 0;
        for (int i = 1; i <= N; i++) {
            if (left[i][0] == 0) {
                q[t++] = i;
                t&=digit;
            }
        }
        while (b!=t) {
            int cur = q[b++];
            b&=digit;
            for (int i = 1; i <= N; i++) {
                if (left[i][cur] > 0) {
                    for (int j = 1; j <= N; j++) {
                        left[i][j] |= left[cur][j];
                    }
                    left[i][0]--;
                    if (left[i][0] == 0) {
                        q[t++] = i;
                        t&=digit;
                    }
                }
            }
        }
        for (int i = 1; i <= N; i++) {
            if (right[i][0] == 0) {
                q[t++] = i;
                t&=digit;
            }
        }
        while (b!=t) {
            int cur = q[b++];
            b&=digit;
            for (int i = 1; i <= N; i++) {
                if (right[i][cur] > 0) {
                    for (int j = 1; j <= N; j++) {
                        right[i][j] |= right[cur][j];
                    }
                    right[i][0]--;
                    if (right[i][0] == 0) {
                        q[t++] = i;
                        t&=digit;
                    }
                }
            }
        }
        for (int i = 1; i <= N; i++) {
            int cnt = 0;
            for (int j = 1; j <= N; j++) {
                if (left[i][j] > 0 || right[i][j] > 0) cnt++;
            }
            sb.append(N - cnt - 1).append('\n');
        }
        System.out.print(sb);
    }

}