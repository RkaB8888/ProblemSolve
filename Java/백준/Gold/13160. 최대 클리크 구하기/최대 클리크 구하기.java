import java.io.*;
import java.util.*;

/**
 * @author python98
 * @description ?
 * @performance 메모리: ? KB, 동작시간: ? ms
 */
/*
동시에 겹치는 구간의 최대 갯수 찾기
 */
public class Main {
    static int N;
    static int[][] edges;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        edges = new int[N][2];
        int[][] times = new int[2*N][2];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            edges[i][0] = Integer.parseInt(st.nextToken());
            edges[i][1] = Integer.parseInt(st.nextToken());
            times[i<<1][0] = edges[i][0]-1;
            times[i<<1][1] = 1;
            times[(i<<1)+1][0] = edges[i][1];
            times[(i<<1)+1][1] = -1;
        }
        Arrays.sort(times,(a, b)->a[0]-b[0]);
        int cnt = 0, maxCnt = 0, t = 0;
        for(int[] time : times) {
            cnt+=time[1];
            if(cnt>maxCnt) {
                t = time[0]+1;
                maxCnt = cnt;
            }
        }
        sb.append(maxCnt).append('\n');
        for(int i = 0 ; i < N ; i++) {
            if(edges[i][0]<=t && t<=edges[i][1]) {
                sb.append(i+1).append(' ');
            }
        }
        System.out.print(sb);
    }
}