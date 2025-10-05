import java.io.*;
import java.util.*;

/**
 * @author python98
 * @description 이 클래스에 대한 동작 설명
 * @performance 메모리: ? KB, 동작시간: ? ms
 */
public class Main {
    static int N, M, K, money;
    static int[] price;
    static boolean[] check;

    static class UnionFind {
        int[] group;

        public UnionFind(int N) {
            this.group = new int[N];
            for (int i = 0; i < N; i++) {
                group[i] = i;
            }
        }

        public void setUnion(int a, int b) {
            int gnA = findUnion(a);
            int gnB = findUnion(b);
            if (price[gnA] > price[gnB]) group[gnA] = gnB;
            else group[gnB] = gnA;
        }

        public int findUnion(int a) {
            if (group[a] == a) return a;
            return group[a] = findUnion(group[a]);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        price = new int[N + 1];
        check = new boolean[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            price[i] = Integer.parseInt(st.nextToken());
        }
        UnionFind uf = new UnionFind(N + 1);
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            uf.setUnion(v, w);
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