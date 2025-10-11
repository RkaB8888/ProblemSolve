import java.io.*;

/**
 * @author python98
 * @description DFS + 인접 리스트(전방성 배열)
 * @performance 메모리: 35,120 KB, 동작시간: 732 ms
 */
public class Main {
    static int N, sum;
    static int[] deg, link, next, v;

    private static int nextInt() throws IOException {
        int n, c;
        while ((n = System.in.read()) <= 32) ;
        n &= 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }

    public static void main(String[] args) throws IOException {
        N = nextInt();
        deg = new int[N + 1];
        link = new int[N + 1];
        next = new int[N << 1];
        v = new int[N << 1];
        for (int i = 1; i < N; i++) {
            int a = nextInt(), b = nextInt(), idx1 = i << 1, idx2 = idx1 + 1;
            next[idx1] = link[a];
            link[a] = idx1;
            v[idx1] = b;
            next[idx2] = link[b];
            link[b] = idx2;
            v[idx2] = a;
            deg[a]++;
            deg[b]++;
        }

        int[] node = new int[N], parent = new int[N], depth = new int[N];
        int idx = 0;
        node[idx] = 1;
        parent[idx] = 0;
        depth[idx++] = 0;
        while (idx > 0) {
            int curV = node[--idx], parentNode = parent[idx], d = depth[idx];

            if (curV != 1 && deg[curV] == 1) { // leaf
                sum ^= d;          // sum은 1 또는 0만 갖게 됨
                continue;
            }
            for (int e = link[curV]; e != 0; e = next[e]) {
                int nextV = v[e];
                if (nextV == parentNode) continue;
                node[idx] = nextV;
                parent[idx] = curV;
                depth[idx++] = d ^ 1;
            }
        }
        System.out.print((sum == 1) ? "Yes" : "No");
    }
}