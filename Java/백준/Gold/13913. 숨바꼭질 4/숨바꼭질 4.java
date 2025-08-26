import java.io.*;
import java.util.*;

/**
 * @author python98
 * @description BFS + Queue(ArrayDeque) + Path Reconstruction
 * @performance 메모리: 30240 KB, 동작시간: 200 ms
 */
public class Main {

    static int N, K;
    static int[] preNum;
    static int[] visitedSec;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        preNum = new int[100001];
        visitedSec = new int[100001];
        Arrays.fill(preNum, -1);

        search();

        sb.append(visitedSec[K]).append("\n");

        Deque<Integer> deque = new ArrayDeque<>();
        int cur = K;
        while(cur!=N) {
            deque.push(cur);
            if(preNum[cur]==-1) {
                cur--;
            } else {
                cur = preNum[cur];
            }
        }
        deque.push(N);

        while(!deque.isEmpty()) {
            sb.append(deque.pop()).append(' ');
        }
        System.out.print(sb);
    }

    private static void search() {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(N);
        visitedSec[N] = 0;

        while(!queue.isEmpty()) {
            int cur = queue.poll();
            if(cur==K) return;
            if(cur+1<=100000 && preNum[cur+1]==-1) {
                visitedSec[cur+1] = visitedSec[cur]+1;
                preNum[cur+1] = cur;
                queue.add(cur+1);
            }
            if(cur-1>=0 && preNum[cur-1]==-1) {
                visitedSec[cur-1] = visitedSec[cur]+1;
                preNum[cur-1] = cur;
                queue.add(cur-1);
            }
            if(cur*2<=100000 && preNum[cur*2]==-1) {
                visitedSec[cur*2] = visitedSec[cur]+1;
                preNum[cur*2] = cur;
                queue.add(cur*2);
            }
        }
    }
}
