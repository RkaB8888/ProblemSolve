import java.io.*;
import java.util.*;

/**
 * @author python98
 * @description 이 클래스에 대한 동작 설명
 * @performance 메모리: ? KB, 동작시간: ? ms
 */
public class Main {
    static int N, R, M, K;
    static int[] goal;
    static int[][][] ways;
    static boolean[][] map;
    static Map<String, Integer> cities, trans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 도시의 수
        R = Integer.parseInt(st.nextToken())<<1; // 내일로 티켓 가격

        cities = new HashMap<>(N);
        st = new StringTokenizer(br.readLine());
        int n = 0;
        for (int i = 0; i < N; i++) { // N개의 도시 이름
            String city = st.nextToken();
            if (cities.containsKey(city)) continue;
            cities.put(city, n++);
        }
        N = n;

        M = Integer.parseInt(br.readLine());
        goal = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) { // 여행 목적지
            goal[i] = cities.get(st.nextToken());
        }

        K = Integer.parseInt(br.readLine());
        trans = new HashMap<>(10);
        trans.put("Subway", 0);
        trans.put("Bus", 1);
        trans.put("Taxi", 2);
        trans.put("Airplane", 3);
        trans.put("KTX", 4);
        trans.put("S-Train", 5); // >>1
        trans.put("V-Train", 6); // >>1
        trans.put("ITX-Saemaeul", 7); // 0
        trans.put("ITX-Cheongchun", 8); // 0
        trans.put("Mugunghwa", 9); // 0
        ways = new int[N][N][2];
        map = new boolean[N][N];
        for (int i = 0; i < K; i++) { // K개의 교통 수단 종류/시작도시/끝도시/비용
            st = new StringTokenizer(br.readLine());
            int type = trans.get(st.nextToken());
            int sCity = cities.get(st.nextToken());
            int eCity = cities.get(st.nextToken());
            int[] way1 =  ways[sCity][eCity], way2 = ways[eCity][sCity];
            int costN = Integer.parseInt(st.nextToken())<<1; // 내일로X
            int costY = (type < 5 ? costN : (type < 7 ? costN >> 1 : 0)); // 내일로Y
            if(map[sCity][eCity]) {
                way2[0] = way1[0] = Math.min(way1[0], costN);
                way2[1] = way1[1] = Math.min(way1[1], costY);
            } else {
                map[eCity][sCity] = map[sCity][eCity] = true;
                way2[0] = way1[0] = costN;
                way2[1] = way1[1] = costY;
            }

        }
        calc();
        int yes = 0, no = 0;
        for (int i = 1; i < M; i++) {
            no += ways[goal[i - 1]][goal[i]][0];
            yes += ways[goal[i - 1]][goal[i]][1];
//            System.out.println("yes: "+yes+" no: "+no);
        }
        yes += R;
//        System.out.println("[total] yes: " + yes + " no: " + no);
        if (yes < no) System.out.print("Yes");
        else System.out.print("No");
    }

    private static void calc() {
        // 플로이드 워샬로 사전 최소비용 정리
        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                if(i==k) continue;
                int[] wayIK = ways[i][k];
                for (int j = 0; j < N; j++) {
                    if(k==j) continue;
                    if (map[i][k] && map[k][j]) {
                        int[] wayKJ = ways[k][j], wayIJ = ways[i][j], wayJI = ways[j][i];
                        if(map[i][j]) {
                            wayJI[0] = wayIJ[0] = Math.min(wayIJ[0], wayIK[0] + wayKJ[0]);
                            wayJI[1] = wayIJ[1] = Math.min(wayIJ[1], wayIK[1] + wayKJ[1]);

                        } else {
                            map[j][i] = map[i][j] = true;
                            wayJI[0] = wayIJ[0] = wayIK[0] + wayKJ[0];
                            wayJI[1] = wayIJ[1] = wayIK[1] + wayKJ[1];
                        }
                    }
                }
            }
        }
    }
}