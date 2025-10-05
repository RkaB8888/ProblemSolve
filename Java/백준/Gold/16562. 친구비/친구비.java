import java.io.*;
import java.util.*;

/**
 * @author python98
 * @description Union-Find + Path Compression + 최소 대표 비용
 * @performance 메모리: 16,988 KB, 동작시간: 152 ms
 */
public class Main {
    static int N, M, K, money;
    static int[] price;
    static boolean[] check;

    static class UnionFind {
        int[] group;

        public UnionFind(int N) {
            this.group = new int[N];
        }

        public void setUnion(int a, int b) {
            int gnA = findUnion(a), gnB = findUnion(b);
            if (gnA == gnB) return;
            if (price[gnA] > price[gnB]) group[gnA] = gnB;
            else group[gnB] = gnA;
        }

        public int findUnion(int a) {
            if (group[a] == a || group[a] == 0) return a;
            return group[a] = findUnion(group[a]);
        }
    }

    private static int nextInt() throws IOException {
        int c, n;
        while ((n = System.in.read()) <= 32) ;
        n &= 15;
        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return n;
    }

    public static void main(String[] args) throws IOException {
        N = nextInt();
        M = nextInt();
        K = nextInt();
        price = new int[N + 1];
        check = new boolean[N + 1];
        for (int i = 1; i <= N; i++) {
            price[i] = nextInt();
        }
        UnionFind uf = new UnionFind(N + 1);
        for (int i = 0; i < M; i++) {
            uf.setUnion(nextInt(), nextInt());
        }
        for (int i = 1; i <= N; i++) {
            int gn = uf.findUnion(i);
            if (check[gn]) continue;
            check[gn] = true;
            money += price[gn];
            if (money > K) {
                System.out.print("Oh no");
                return;
            }
        }
        System.out.print(money);
    }
}