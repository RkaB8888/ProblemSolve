import java.io.*;
import java.util.*;

/**
 * @author python98
 * @description ?
 * @performance 메모리: ? KB, 동작시간: ? ms
 */
public class Main {

    // 작은 것부터 가득 채운 뒤 갯수 가능한지 체크
    // 그 다음으로 큰 것으로 교환 가능한지 체크 -> 가세높 조절 및 이전 크기 갯수 차감
    // 정육면체 갯수 각각 적당한지 체크 -> 반복


    static int l;
    static int w;
    static int h;
    static int N;
    static int result;
    static int[] box;
    static long[] requireBox;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        l = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        N = Integer.parseInt(br.readLine());
        box = new int[N];
        requireBox = new long[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken());
            box[idx] = Integer.parseInt(st.nextToken());
        }

        requireBox[0] = l;
        requireBox[0] *= w;
        requireBox[0] *= h;
        for(int i = 1 ; i < N ; i++) {
            l /= 2;
            w /= 2;
            h /= 2;
            requireBox[i] = l;
            requireBox[i] *= w;
            requireBox[i] *= h;
            long require = requireBox[i-1] - requireBox[i] * 8;
        }
        long cnt = 0;
        for(int i = N-1 ; i >= 0 ; i--) {
            long tmp = Math.min(requireBox[i]-cnt,box[i]);
            result += tmp;
            cnt = (cnt + tmp) * 8;
        }

        System.out.print(cnt/8 == requireBox[0] ? result : -1);
    }

}