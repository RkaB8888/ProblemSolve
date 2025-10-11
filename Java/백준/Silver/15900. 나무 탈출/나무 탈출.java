import java.io.*;
import java.util.*;

/**
 * @author python98
 * @description 이 클래스에 대한 동작 설명
 * @performance 메모리: ? KB, 동작시간: ? ms
 */
public class Main {
    static int N, totalStep;
    static int[] step, link, next, v;
    static boolean[] leaf;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        step = new int[N + 1];
        Arrays.fill(step, -1);
        link = new int[N + 1];
        next = new int[N << 1];
        v = new int[N << 1];
        leaf = new boolean[N + 1];
        for (int i = 1; i < N ; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            next[(i << 1)] = link[a];
            link[a] = (i << 1);
            v[(i << 1)] = b;

            next[(i << 1) + 1] = link[b];
            link[b] = (i << 1) + 1;
            v[(i << 1) + 1] = a;
        }

        dfs();
        for (int i = 1 ; i <= N ; i++) {
            if(leaf[i]) totalStep += step[i];
        }
        System.out.print((totalStep & 1) == 1 ? "Yes" : "No");
    }

    private static void dfs() {
        Queue<Integer> q = new ArrayDeque<>();
        leaf[1] = true;
        step[1] = 0;
        q.add(1);
        while (!q.isEmpty()) {
            int curV = q.poll();
            for (int e = link[curV]; e > 0; e = next[e]) {
                int nextV = v[e];
                if (step[nextV] >= 0) continue;
                leaf[curV] = false;
                leaf[nextV] = true;
                step[nextV] = step[curV] + 1;
                q.add(nextV);
            }
        }
    }
}