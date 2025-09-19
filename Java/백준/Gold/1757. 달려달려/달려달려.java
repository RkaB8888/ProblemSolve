import java.io.*;
import java.util.*;

/**
 * @author python98
 * @description ?
 * @performance 메모리: ? KB, 동작시간: ? ms
 */
public class Main {
    static int N, M;
    static int[] D, dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = new int[N+1];
        dist = new int[N+1];
        Arrays.fill(dist,-1);
        D[0] = 0;
        for (int i = 1; i <= N; i++) {
            D[i] = D[i-1]+Integer.parseInt(br.readLine());
        }
        dist[0] = 0;
        for(int i = 0 ; i < N ; i++) {
            if(dist[i]==-1) continue;
            int restTime = Math.min(M,(N-i)>>1);
            for(int j = 1 ; j <= restTime ; j++) {
                dist[i+(j<<1)] = Math.max(dist[i+(j<<1)],dist[i]+(D[i+j]-D[i]));
            }
            dist[i+1] = Math.max(dist[i], dist[i+1]);
        }
        System.out.print(dist[N]);
    }
}