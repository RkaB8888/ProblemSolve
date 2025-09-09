import java.io.*;
import java.util.*;

/**
 * @author python98
 * @description 아호코라식
 * @performance 메모리: ? KB, 동작시간: ? ms
 */
public class Main {

    static int N, Q;

    static class Aho {
        List<int[]> next = new ArrayList<>();
        int[] link;
        boolean[] out;
        List<Integer> ends = new ArrayList<>();

        Aho() {
            addNode();
        }

        public int addNode() {
            int[] child = new int[26];
            Arrays.fill(child, -1);
            next.add(child);
            return next.size() - 1;
        }

        public void insert(String s) {
            int idx = 0;
            for (int i = 0; i < s.length(); i++) {
                int c = s.charAt(i) - 'a';
                if (next.get(idx)[c] == -1) {
                    next.get(idx)[c] = addNode();
                }
                idx = next.get(idx)[c];
            }
            ends.add(idx);
        }

        public void build() {
            int n = next.size();
            link = new int[n];
            out = new boolean[n];
            for (int u : ends) out[u] = true;

            Queue<Integer> q = new ArrayDeque<>();

            for (int i = 0; i < 26; i++) {
                int v = next.get(0)[i];
                if (v != -1) {
                    link[v] = 0; // 처음부터 틀리면 0으로 가는 것
                    q.add(v);
                } else {
                    next.get(0)[i] = 0; // 바로 0으로 가게 만듦
                }
            }

            while (!q.isEmpty()) {
                int u = q.poll();
                for (int i = 0; i < 26; i++) {
                    int v = next.get(u)[i];
                    if (v != -1) {
                        link[v] = next.get(link[u])[i]; // 이전의 확인할 곳에서 i 다음 노드
                        out[v] |= out[link[v]]; // 매칭 전파
                        q.add(v);
                    } else {
                        next.get(u)[i] = next.get(link[u])[i]; // 막힌 경우 이전으로 돌아가기
                    }
                }
            }
        }

        public String search(String text) {
            int u = 0;
            for (int i = 0; i < text.length(); i++) {
                int c = text.charAt(i) - 'a';
                u = next.get(u)[c];
                if (out[u]) {
                    return "YES";
                }
            }
            return "NO";
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Aho aho = new Aho();
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            aho.insert(br.readLine());
        }
        aho.build();
        Q = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            sb.append(aho.search(br.readLine())).append('\n');
        }

        System.out.print(sb);
    }
}