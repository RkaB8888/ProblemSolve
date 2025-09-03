import java.io.*;
import java.util.*;

/**
 * @author python98
 * @description ?
 * @performance 메모리: ? KB, 동작시간: ? ms
 */
public class Main {

    static int T, N, result;
    static int[] arr, gn;
    //-2:이미 그룹됨, -1:아무것도 못됨, 0:확인 안함, 1:검사시작번호, 2:순환시작번호
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            arr = new int[N + 1];
            gn = new int[N + 1];
            result = 0;
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            for(int i = 1; i <= N; i++){
                if(gn[i]!=0) continue;
                gn[i] = 1;
                gn[i] = check(arr[i]);
                if(gn[i]==-1) result++;
            }
            sb.append(result).append("\n");
//            System.out.print(result+":");
//            System.out.println(Arrays.toString(gn));
        }
        System.out.print(sb);
    }

    private static int check(int i){
        if(gn[i]<0) return -1; // 이미 그룹화 된 곳
        gn[i]++; // 지나간 곳 표시
        if(gn[i]==2) { // 만약 두 번 째 지나간 곳이면
            return gn[i] = -2; // 이미 그룹 됨 표시
        }

        int temp = check(arr[i]);
        if(temp==-1) result++;
        else if(gn[i]==-2) return -1;
        return gn[i] = temp;
    }

}
