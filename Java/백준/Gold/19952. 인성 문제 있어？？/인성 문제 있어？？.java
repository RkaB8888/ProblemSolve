import java.io.*;
import java.util.*;

/**
 * @author python98
 * @description ?
 * @performance 메모리: ? KB, 동작시간: ? ms
 */

/*
각 격자에서 남은 힘이 더 높으면 갱신하는 BFS
PQ를 사용한다면 남은 힘이 더 큰 경우를 먼저 꺼내 쓰는 방식이 이득
 */
public class Main {
    static final int BIT = (1<<16)-1;
    static final int[][] DIR = {{-1,0},{1,0},{0,-1},{0,1}};
    static int T, H, W, O, F, xS, yS, xE, yE;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            O = Integer.parseInt(st.nextToken());
            F = Integer.parseInt(st.nextToken());
            xS = Integer.parseInt(st.nextToken())-1;
            yS = Integer.parseInt(st.nextToken())-1;
            xE = Integer.parseInt(st.nextToken())-1;
            yE = Integer.parseInt(st.nextToken())-1;

            map = new int[H][W];
            for(int i = 0 ; i < O ; i++) {
                st = new StringTokenizer(br.readLine());
                map[Integer.parseInt(st.nextToken())-1][Integer.parseInt(st.nextToken())-1] = Integer.parseInt(st.nextToken());
            }
            if(bfs()) {
                sb.append("잘했어!!\n");
            } else {
                sb.append("인성 문제있어??\n");
            }
        }
        System.out.print(sb);
    }
    private static boolean bfs(){
        int[][] mapCopy = new int[H][W];
        mapCopy[xS][yS] = F;
        Queue<int[]> q = new ArrayDeque<>(10000);
        q.add(new int[]{F,(xS<<16)+yS});
        while(!q.isEmpty()) {
            int[] c = q.poll();
            int cF = c[0];
            int cX = c[1]>>16, cY = c[1]&BIT;
            if(cF<mapCopy[cX][cY]) continue;
            for(int[] d : DIR) {
                int nX = cX+d[0], nY = cY+d[1];
                if(nX<0||nY<0||nX>=H||nY>=W) continue;
                int rF = map[nX][nY] - map[cX][cY];
                if(cF-rF<0) continue;
                if(nX==xE && nY==yE) return true;
                int nF = cF - 1;
                if(mapCopy[nX][nY]<nF) {
                    mapCopy[nX][nY] = nF;
                    q.add(new int[] {nF,(nX<<16)+nY});
                }
            }
        }
        return false;
    }

}