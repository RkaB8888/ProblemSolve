import java.io.*;
import java.util.*;

/**
 * @author python98
 * @description BFS + Parity (Even/Odd) + ArrayDeque
 * @performance 메모리: 42,268 KB, 동작시간: 188 ms
 */
public class Main {

    static final int LIMIT = 500000;

    static int N, K;
    static int[][] reachTime;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        if (N == K) {
            System.out.print(0);
            return;
        }

        reachTime = new int[2][LIMIT + 1];
        Arrays.fill(reachTime[0], Integer.MAX_VALUE);
        Arrays.fill(reachTime[1], Integer.MAX_VALUE);

        int time = 0, kk = K;
        while (kk <= LIMIT) {
            time++;
            kk += time;
        }

        Queue<Integer> q = new ArrayDeque<>();
        reachTime[0][N] = 0;
        q.add(N);

        int t = 0;
        while (!q.isEmpty()) {
            t++;
            if (t >= time) break;
            int len = q.size();
            while (--len >= 0) {
                int pose = q.poll();
                int nextPose = pose * 2;
                if (nextPose <= LIMIT && reachTime[t & 1][nextPose] > t) {
                    reachTime[t & 1][nextPose] = t;
                    q.add(nextPose);
                }
                nextPose = pose + 1;
                if (nextPose <= LIMIT && reachTime[t & 1][nextPose] > t) {
                    reachTime[t & 1][nextPose] = t;
                    q.add(nextPose);
                }
                nextPose = pose - 1;
                if (nextPose >= 0 && reachTime[t & 1][nextPose] > t) {
                    reachTime[t & 1][nextPose] = t;
                    q.add(nextPose);
                }
            }
        }

        time = 0;
        while (K <= LIMIT) {
            if (reachTime[time & 1][K] <= time) {
                System.out.print(time);
                return;
            }
            time++;
            K += time;
        }
        System.out.print(-1);
    }
}