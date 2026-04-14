import java.io.*;
import java.util.*;

/**
 * @author python98
 * @description 전이 관계 전파 + BitSet 행렬 + row/col 카운트 + 원형 Queue
 * @performance 메모리: 12,192 KB, 동작시간: 72 ms
 */
public class Main {
    static int N, M, digit;
    static int[] indeg, link, next, v, rowCnt, colCnt;
    static BitSet[] left;

    private static int nextInt() throws IOException {
        int n = 0, s = 1, c;
        while ((c = System.in.read()) <= 32) ;
        if (c == '-') s = -1;
        else n = c & 15;
        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return n * s;
    }

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        N = nextInt();
        digit = 1;
        while (digit < N) digit <<= 1;
        M = nextInt();
        indeg = new int[N];
        left = new BitSet[N];
        for (int i = 0; i < N; i++) left[i] = new BitSet(N);
        rowCnt = new int[N];
        colCnt = new int[N];

        // 진출 차수를 찾기 위한 인접 리스트
        link = new int[N];
        Arrays.fill(link, -1);
        next = new int[M];
        v = new int[M];

        for (int i = 0; i < M; i++) {
            int l = nextInt() - 1;
            int r = nextInt() - 1;
            if (!left[r].get(l)) {
                left[r].set(l);
                rowCnt[r]++;
                colCnt[l]++;

                next[i] = link[l];
                link[l] = i;
                v[i] = r;

                indeg[r]++;
            }
        }
        int[] q = new int[digit--];
        int b = 0, t = 0;
        for (int i = 0; i < N; i++) {
            if (indeg[i] == 0) {
                q[t++] = i;
                t &= digit;
            }
        }
        while (b != t) {
            int cur = q[b++];
            b &= digit;
            BitSet rowC = left[cur];
            for (int e = link[cur]; e >= 0; e = next[e]) {
                int i = v[e];
                BitSet rowI = left[i];
                BitSet delta = (BitSet) rowC.clone();
                delta.andNot(rowI); // rowC에서 rowI가 1인 부분을 지운다.
                if (!delta.isEmpty()) {
                    rowI.or(rowC);
                    int add = delta.cardinality();
                    rowCnt[i] += add;
                }
                for (int j = delta.nextSetBit(0); j >= 0; j = delta.nextSetBit(j + 1)) {
                    colCnt[j]++;
                }
                if (--indeg[i] == 0) {
                    q[t++] = i;
                    t &= digit;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            sb.append(N - colCnt[i] - rowCnt[i] - 1).append('\n');
        }
        System.out.print(sb);
    }

}