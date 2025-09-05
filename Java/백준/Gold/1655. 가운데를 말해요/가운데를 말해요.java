import java.io.*;
import java.util.*;

/**
 * @author python98
 * @description ?
 * @performance 메모리: ? KB, 동작시간: ? ms
 */
public class Main {

    static int N;
    static PriorityQueue<Integer> lpq, rpq;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        lpq = new PriorityQueue<>((a, b) -> b - a);
        rpq = new PriorityQueue<>();

        int num = Integer.parseInt(br.readLine());
        lpq.add(num);
        sb.append(num).append("\n");

        if (N != 1) {
            num = Integer.parseInt(br.readLine());
            lpq.add(num);
            rpq.add(lpq.poll());
            sb.append(lpq.peek()).append("\n");

            for (int i = 3; i <= N; i++) {
                num = Integer.parseInt(br.readLine());
                int rPeek = rpq.peek();
                if (num > rPeek) {
                    rpq.add(num);
                } else {
                    lpq.add(num);
                }

                if(lpq.size() < rpq.size()) lpq.add(rpq.poll());
                else if(lpq.size() -1 > rpq.size()) rpq.add(lpq.poll());

                sb.append(lpq.peek()).append("\n");
            }
        }
        System.out.print(sb);
    }
}
