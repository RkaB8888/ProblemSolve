import java.io.*;
import java.util.*;

/**
 * @author python98
 * @description DFS (cycle detection) + Visiting/Finished Marking
 * @performance 메모리: 303,456 KB, 동작시간: 1160 ms
 */
public class Main {

    static final int LEN = 100001;

    static int T, N, result;
    static int[] arr, stack;
    static boolean[] vis, fin;

    //-2:이미 그룹됨, -1:아무것도 못됨, 0:확인 안함, 1:검사시작번호, 2:순환시작번호
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        arr = new int[LEN];
        stack = new int[LEN];
        vis = new boolean[LEN];
        fin = new boolean[LEN];

        T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            Arrays.fill(vis, false);
            Arrays.fill(fin, false);
            result = 0;
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            int len = 0;
            for (int i = 1; i <= N; i++) {
                if (fin[i]) continue;
                int last = i;
                //fin이거나 vis일 때 까지 stack에 넣기
                while(!fin[last]&&!vis[last]) {
                    stack[len++] = last;
                    vis[last] = true;
                    last = arr[last];
                }
                if(fin[last]) { // stack은 버려지는 숫자
                    while(len > 0){
                        fin[stack[--len]] = true;
                        result++;
                    }
                } else { // 그룹되는 경우
                    while(len >0){
                        int now = stack[--len];
                        fin[now] = true;
                        if(now == last) {
                            break;
                        }
                    }
                    while(len > 0) {
                        fin[stack[--len]] = true;
                        result++;
                    }
                }
            }
            sb.append(result).append("\n");
        }
        System.out.print(sb);
    }

}
