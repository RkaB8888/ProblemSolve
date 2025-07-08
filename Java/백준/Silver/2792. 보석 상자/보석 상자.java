import java.io.*;
import java.util.*;

public class Main {
    // 가능한 최대 질투 = maxJewel, 최소 질투 = minJewel
    // 각 보석의 수 / checkNum 을 sum 했을 때
    // 인원 수 보다 작다면 질투를 내린다.
    // 인원 수 보다 크다면 질투를 올린다.
    static int N, M;
    static int maxJewels, minJewels, result;
    static int[] jewels; // 1울 제외한 색상 수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        jewels = new int[M];

        for(int i = 0 ; i < M ; i++) {
            jewels[i] = Integer.parseInt(br.readLine());
            minJewels = Math.min(minJewels, jewels[i]);
            maxJewels = Math.max(maxJewels, jewels[i]);
        }

        while(minJewels < maxJewels) {
            int half = (minJewels + maxJewels + 1)>>1;
            int checkNum = 0;
            for(int i = 0 ; i < M ; i++) {
                if (jewels[i] <= half) {
                    checkNum++;
                } else {
                    checkNum+=(jewels[i] + half - 1) / half;
                }
            }
            if(checkNum <= N) {
                result = half;
                maxJewels = half - 1;
            } else {
                minJewels = half;
            }
        }
        System.out.print(result);
    }

}