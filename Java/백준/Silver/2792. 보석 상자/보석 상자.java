import java.io.*;
import java.util.*;

public class Main {
    // 가능한 최대 질투 = maxJewel, 최소 질투 = 1
    // 각 보석의 수 / checkNum 을 sum 했을 때
    // 인원 수 보다 작다면 질투를 내린다.
    // 인원 수 보다 크다면 질투를 올린다.
    static int N, M;
    static int maxJewels, result;
    static int[] jewels;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        jewels = new int[M];

        maxJewels = 1;
        for(int i = 0 ; i < M ; i++) {
            jewels[i] = Integer.parseInt(br.readLine());
            maxJewels = Math.max(maxJewels, jewels[i]);
        }

        int minJewels = 1;
        while (minJewels <= maxJewels) {
            int mid = (minJewels + maxJewels) >> 1;
            int checkNum = 0;
            for (int jewel : jewels) {
                checkNum+=(jewel + mid - 1) / mid;
            }
            if(checkNum <= N) {
                result = mid;
                maxJewels = mid - 1;
            } else {
                minJewels = mid + 1;
            }
        }
        System.out.print(result);
    }

}