import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static final int inf = Integer.MAX_VALUE;
    static int N, K;
    static long result;
    static int[][] jewel;
    static int[] bag;
    static int[][] seg;
    static int segSize;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        jewel = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            jewel[i][0] = Integer.parseInt(st.nextToken());
            jewel[i][1] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(jewel, (a, b) -> a[0] - b[0]);

        bag = new int[K];
        for (int i = 0; i < K; i++) {
            bag[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(bag);

        segSize = 1;
        while (segSize < N) segSize *= 2;
        segSize *= 2;
        seg = new int[segSize][3];
        setSeg(0, 0, N - 1);

        for (int i = 0; i < K; i++) {
            int len = bSearch(bag[i], 0, N);
            int[] getJewel = findSeg(0, 0, len, 0, N - 1);
            if (getJewel[1] > 0) {
                result += getJewel[1];
                updateSeg(0, 0, N - 1, getJewel[2]);
            }
        }

        System.out.print(result);
    }

    private static void updateSeg(int idx, int segStr, int segEnd, int target) {
        if (segStr == segEnd) {
            seg[idx] = new int[] { 0, 0, -1 };
            return;
        }

        int mid = (segStr + segEnd) >> 1;
        if (target <= mid) {
            updateSeg(2 * idx + 1, segStr, mid, target);
        } else {
            updateSeg(2 * idx + 2, mid + 1, segEnd, target);
        }

        int[] left = seg[2 * idx + 1];
        int[] right = seg[2 * idx + 2];
        if (left[1] > right[1]) {
            seg[idx] = left;
        } else if (left[1] < right[1]) {
            seg[idx] = right;
        } else {
            seg[idx] = left[0] > right[0] ? left : right;
        }
    }

    private static int[] setSeg(int idx, int str, int end) {
        if (str == end) {
            return seg[idx] = new int[] { jewel[str][0], jewel[str][1], str };
        }

        int mid = (str + end) >> 1;
        int[] left = setSeg(2 * idx + 1, str, mid);
        int[] right = setSeg(2 * idx + 2, mid + 1, end);

        if (left[1] > right[1]) {
            return seg[idx] = left;
        } else if (left[1] < right[1]) {
            return seg[idx] = right;
        } else {
            return seg[idx] = (left[0] > right[0]) ? left : right;
        }
    }

    private static int bSearch(int w, int str, int end) {
        while (str < end) {
            int mid = (str + end) >> 1;
            if (jewel[mid][0] <= w) {
                str = mid + 1;
            } else {
                end = mid;
            }
        }
        return str - 1;
    }

    private static int[] findSeg(int idx, int qStr, int qEnd, int segStr, int segEnd) {
        if (qEnd < segStr || qStr > segEnd) {
            return new int[] { 0, 0, -1 };
        }
        if (qStr <= segStr && qEnd >= segEnd) {
            return seg[idx];
        }

        int mid = (segStr + segEnd) >> 1;
        int[] left = findSeg(2 * idx + 1, qStr, qEnd, segStr, mid);
        int[] right = findSeg(2 * idx + 2, qStr, qEnd, mid + 1, segEnd);

        if (left[1] > right[1]) {
            return left;
        } else if (left[1] < right[1]) {
            return right;
        } else {
            return (left[0] > right[0]) ? left : right;
        }
    }
}