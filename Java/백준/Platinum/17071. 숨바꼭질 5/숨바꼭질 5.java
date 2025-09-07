import java.io.*;
import java.util.*;

/**
 * @author python98
 * @description 이 클래스에 대한 동작 설명
 * @performance 메모리: ? KB, 동작시간: ? ms
 */
public class Main {

    static final int LIMIT = 500000;

    static int N, K;
    static int[][] reachTime;

    static class TimePose {
        int time;
        int pose;

        TimePose(int time, int pose) {
            this.time = time;
            this.pose = pose;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        reachTime = new int[2][LIMIT + 1];
        Arrays.fill(reachTime[0], Integer.MAX_VALUE);
        Arrays.fill(reachTime[1], Integer.MAX_VALUE);

        Queue<TimePose> q = new ArrayDeque<>();
        reachTime[0][N] = 0;
        q.add(new TimePose(0, N));
        while (!q.isEmpty()) {
            TimePose now = q.poll();
            int nextTime = now.time + 1;
            int nextPose = now.pose * 2;
            if (nextPose <= LIMIT && reachTime[nextTime & 1][nextPose] > nextTime) {
                reachTime[nextTime & 1][nextPose] = nextTime;
                q.add(new TimePose(now.time + 1, nextPose));
            }
            nextPose = now.pose + 1;
            if (nextPose <= LIMIT && reachTime[nextTime & 1][nextPose] > nextTime) {
                reachTime[nextTime & 1][nextPose] = nextTime;
                q.add(new TimePose(now.time + 1, nextPose));
            }
            nextPose = now.pose - 1;
            if (nextPose >= 0 && reachTime[nextTime & 1][nextPose] > nextTime) {
                reachTime[nextTime & 1][nextPose] = nextTime;
                q.add(new TimePose(now.time + 1, nextPose));
            }
        }

        int time = 0;
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