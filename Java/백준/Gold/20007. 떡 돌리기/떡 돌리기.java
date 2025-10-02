import java.io.*;
import java.util.*;

/**
 * @author python98
 * @description SPFA (Ring-Buffer Queue) + Greedy Grouping (Ascending by 2*dist)
 * @performance 메모리: 40,248 KB, 동작시간: 324 ms
 */
public class Main {
    static final int MAX = 10000001;
    static int N, M, X, Y, digit;
    static int[] minDist;
    static int[] next, nextNode;
    static int[] link;
    static int[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        digit = 1;
        while(digit<N) digit<<=1;
        digit--;
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());
        minDist = new int[N];
        next = new int[2 * M];
        Arrays.fill(next, -1);
        nextNode = new int[2 * M];
        Arrays.fill(nextNode, -1);
        link = new int[N];
        Arrays.fill(link, -1);
        dist = new int[2 * M];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            nextNode[2 * i] = b;
            next[2 * i] = link[a];
            link[a] = 2 * i;
            dist[2 * i] = c;
            nextNode[2 * i + 1] = a;
            next[2 * i + 1] = link[b];
            link[b] = 2 * i + 1;
            dist[2 * i + 1] = c;
        }
        bfs();

        int[] rounds = new int[N-1];
        int idx = 0;
        for (int i = 0; i < N; i++) {
            if (i == Y) continue;
            if (minDist[i] == MAX) { // 도달 불가
                System.out.print(-1);
                return;
            }
            int r = minDist[i] * 2;
            if (r > X) { // 하루에도 못 갔다 오는 집
                System.out.print(-1);
                return;
            }
            rounds[idx++] = r;
        }

        Arrays.sort(rounds);

        int days = 1, sum = 0;
        for (int i = 0; i < idx; i++) {
            if (sum + rounds[i] > X) {
                days++;
                sum = 0;
            }
            sum += rounds[i];
        }
        System.out.print(days);
    }

    private static void bfs() {
        Arrays.fill(minDist, MAX);

        boolean[] inQ = new boolean[N];
        int[] q = new int[digit+1];
        int b = 0, t = 0;

        minDist[Y] = 0;
        q[t++] = Y;
        inQ[Y] = true;

        while (b!=t) {
            int u = q[b++];
            b&=digit;
            inQ[u] = false;
            int d = minDist[u];

            int idx = link[u];
            while (idx != -1) {
                int v = nextNode[idx];
                int nd = d + dist[idx];
                if (minDist[v] > nd) {
                    minDist[v] = nd;
                    if (!inQ[v]) {
                        q[t++] = v;
                        t&=digit;
                        inQ[v] = true;
                    }
                }
                idx = next[idx];
            }
        }
    }
}