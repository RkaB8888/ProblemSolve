import java.io.*;
import java.util.*;

/**
 * @author python98
 * @description ?
 * @performance 메모리: ? KB, 동작시간: ? ms
 */
public class Main {

    static int[][] map = new int[5][5];
    //-1 : 장애물, 0 : 빈공간, 1 : 사과

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for(int i = 0; i < 5; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 5; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        System.out.print(howManyEatApple(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),3)>=2?1:0);
    }

    private static int howManyEatApple(int i, int j, int move) {
        if(move < 0 || i < 0 || j < 0 || i >= 5 || j >= 5 || map[i][j]==-1) return 0;

        int apple = map[i][j];
        map[i][j] = -1;
        int l = howManyEatApple(i,j-1, move-1);
        int r = howManyEatApple(i,j+1, move-1);
        int u = howManyEatApple(i-1,j, move-1);
        int d = howManyEatApple(i+1,j, move-1);
        map[i][j] = apple;
        return apple + Math.max(Math.max(l,r),Math.max(u,d));
    }
}
