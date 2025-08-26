import java.io.*;
import java.util.*;

/**
 * @author python98
 * @description BFS + Queue(primitive int[]) + Path Reconstruction
 * @performance 메모리: 29712 KB, 동작시간: 192 ms
 */
public class Main {

    static final int MAX = 100000;
    static int N, K;
    static int[] parent;
    static int[] visitedSec;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        if (N >= K) {
            StringBuilder sb = new StringBuilder();
            sb.append(N - K).append('\n');
            for (int x = N; x >= K; --x) sb.append(x).append(' ');
            System.out.print(sb);
            return;
        }

        parent = new int[MAX+1];
        Arrays.fill(parent, -1);
        parent[N] = N;

        bfs();

        Deque<Integer> path = new ArrayDeque<>();
        int steps = 0, cur = K;
        while(cur!=N) {
            path.push(cur);
            cur = parent[cur];
            steps++;
        }
        path.push(N);

        StringBuilder sb = new StringBuilder();
        sb.append(steps).append('\n');
        while(!path.isEmpty()) sb.append(path.pop()).append(' ');
        System.out.print(sb);
    }

    private static void bfs() {
        int[] q = new int[MAX+5];
        int h = 0, t = 0;
        q[t++] = N;

        while(h<t) {
            int cur = q[h++];

            if(cur>K)  {
                int next = cur-1;
                if(parent[next]==-1) {
                    parent[next] = cur;
                    if(next==K) return;
                    q[t++] = next;
                }
                continue;
            }

            int next = cur << 1;
            if (next <= MAX && parent[next] == -1) {
                parent[next] = cur;
                if (next == K) return;
                q[t++] = next;
            }

            next = cur + 1;
            if(next <= MAX && parent[next] == -1) {
                parent[next] = cur;
                if(next == K) return;
                q[t++] = next;
            }

            next = cur - 1;
            if(next >= 0 && parent[next] == -1) {
                parent[next] = cur;
                if(next == K) return;
                q[t++] = next;
            }
        }
    }
}
