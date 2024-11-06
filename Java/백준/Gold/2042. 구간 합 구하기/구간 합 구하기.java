import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 세그먼트 트리 메모리 124,044 KB 시간 648 ms
 */
public class Main {
    static int N, M, K;
    static long[] seg;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        seg = new long[4 * N];
        for (int i = 0; i < N; i++) {
            updateSeg(i, Long.parseLong(br.readLine()), 0, 0, N - 1);
        }

        for (int i = 0, iEnd = M + K; i < iEnd; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());
            if (a == 1) {
                updateSeg(b - 1, c, 0, 0, N - 1);
            } else {
                sb.append(findSeg(b - 1, (int)c - 1, 0, 0, N - 1)).append('\n');
            }
        }
        System.out.print(sb);
    }

    // Update segment tree for a specific index with new value
    private static long updateSeg(int targetIdx, long val, int curIdx, int segStr, int segEnd) {
        if (targetIdx < segStr || targetIdx > segEnd) return seg[curIdx];
        if (segStr == segEnd) {
            return seg[curIdx] = val;
        }
        int mid = (segStr + segEnd) >> 1;
        return seg[curIdx] = updateSeg(targetIdx, val, curIdx * 2 + 1, segStr, mid)
                + updateSeg(targetIdx, val, curIdx * 2 + 2, mid + 1, segEnd);
    }
    
    // Find the sum in the range [targetStr, targetEnd]
    private static long findSeg(int targetStr, int targetEnd, int curIdx, int segStr, int segEnd) {
        if (targetEnd < segStr || targetStr > segEnd) return 0;
        if (segStr >= targetStr && targetEnd >= segEnd) return seg[curIdx];
        
        int mid = (segStr + segEnd) >> 1;
        return findSeg(targetStr, targetEnd, curIdx * 2 + 1, segStr, mid)
                + findSeg(targetStr, targetEnd, curIdx * 2 + 2, mid + 1, segEnd);
    }
}