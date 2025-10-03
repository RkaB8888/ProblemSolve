import java.io.*;
import java.util.*;

/**
 * @author python98
 * @description Line Sweep + Sorting
 * @performance 메모리: 143,392 KB, 동작시간: 1,876 ms
 */
/*
동시에 겹치는 구간의 최대 갯수 찾기
 */
public class Main {
    static int N;
    static int[] L, R, T;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        L = new int[N];
        R = new int[N];
        T = new int[N << 1];
        for (int i = 0, idx = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            L[i] = s;
            R[i] = e;
            T[idx++] = ((s - 1) << 1) | 1;
            T[idx++] = (e << 1);
        }
        Arrays.sort(T);
        int cnt = 0, maxCnt = 0, time = 0;
        for (int t : T) {
            cnt += ((t & 1) == 1) ? 1 : -1;
            if (cnt > maxCnt) {
                time = (t >> 1) + 1;
                maxCnt = cnt;
            }
        }
        sb.append(maxCnt).append('\n');
        for (int i = 0; i < N; i++) {
            if (L[i] <= time && time <= R[i]) {
                sb.append(i + 1).append(' ');
            }
        }
        System.out.print(sb);
    }
}