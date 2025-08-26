import java.io.*;
import java.util.*;

/**
 * @author python98
 * @description ?
 * @performance 메모리: ? KB, 동작시간: ? ms
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

        preNum = new int[200000];
        visitedSec = new int[200000];
        Arrays.fill(preNum, -1);
        Arrays.fill(visitedSec, -1);

        search();

        sb.append(visitedSec[K]).append("\n");

        Deque<Integer> deque = new ArrayDeque<>();
        int cur = K;
        while(cur!=-1) {
            deque.push(cur);
            cur = preNum[cur];
        }

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
            if(cur>0 && visitedSec[cur-1]==-1) {
                visitedSec[cur-1] = visitedSec[cur]+1;
                preNum[cur-1] = cur;
                queue.add(cur-1);
            }
            if(cur < K && cur < 100000) {
                if(visitedSec[cur+1]==-1) {
                    visitedSec[cur+1] = visitedSec[cur]+1;
                    preNum[cur+1] = cur;
                    queue.add(cur+1);
                }
                if(visitedSec[cur*2]==-1) {
                    visitedSec[cur*2] = visitedSec[cur]+1;
                    preNum[cur*2] = cur;
                    queue.add(cur*2);
                }
            }
        }
    }
}
