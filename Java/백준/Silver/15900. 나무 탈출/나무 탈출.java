import java.io.*;
import java.util.*;

/**
 * @author python98
 * @description BFS + 인접 리스트(전방성 배열) + 배열 큐
 * @performance 메모리: 26,324 KB, 동작시간: 292 ms
 */
public class Main {
    static int N, sum;
    static int[] step, deg, link, next, v;

    private static int nextInt() throws IOException {
        int n, c;
        while ((n = System.in.read()) <= 32) ;
        n &= 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }

    public static void main(String[] args) throws IOException {
        N = nextInt();
        step = new int[N + 1];
        deg = new int[N + 1];
        link = new int[N + 1];
        next = new int[N << 1];
        v = new int[N << 1];
        for (int i = 1; i < N; i++) {
            int a = nextInt(), b = nextInt();
            next[(i << 1)] = link[a];
            link[a] = (i << 1);
            v[(i << 1)] = b;

            next[(i << 1) + 1] = link[b];
            link[b] = (i << 1) + 1;
            v[(i << 1) + 1] = a;

            deg[a]++;
            deg[b]++;
        }

        bfs();
        System.out.print((sum & 1) == 1 ? "Yes" : "No");
    }

    private static void bfs() {
        int[] q = new int[N];
        int b = 0, t = 0;
        Arrays.fill(step, -1);
        step[1] = 0;
        q[t++] = 1;
        while (b < t) {
            int curV = q[b++];
            for (int e = link[curV]; e > 0; e = next[e]) {
                int nextV = v[e];
                if (step[nextV] >= 0) continue;
                step[nextV] = step[curV] + 1;
                q[t++] = nextV;
                if (deg[nextV] == 1) sum += step[nextV];
            }
        }
    }
}