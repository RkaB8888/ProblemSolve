import java.io.*;
import java.util.*;

/**
 * @author python98
 * @description 플로이드-워셜 + 인접행렬
 * @performance 메모리: 20,164 KB, 동작시간: 244 ms
 */
public class Main {
    static final int INF = 1_000_000_000;

    static int N, R, M, K;
    static int[] goal;
    static int[][] normal, kPass;
    static Map<String, Integer> cities;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 도시의 수
        R = Integer.parseInt(st.nextToken()) << 1; // 내일로 티켓 가격

        cities = new HashMap<>(N);
        st = new StringTokenizer(br.readLine());
        int n = 0;
        for (int i = 0; i < N; i++) { // N개의 도시 이름
            String city = st.nextToken();
            if (!cities.containsKey(city)) cities.put(city, n++);
        }
        N = n;

        M = Integer.parseInt(br.readLine());
        goal = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) goal[i] = cities.get(st.nextToken());


        normal = new int[N][N];
        kPass = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(normal[i], INF);
            Arrays.fill(kPass[i], INF);
            normal[i][i] = kPass[i][i] = 0;
        }

        K = Integer.parseInt(br.readLine());
        for (int i = 0; i < K; i++) { // K개의 교통 수단 종류/시작도시/끝도시/비용
            st = new StringTokenizer(br.readLine());
            String type = st.nextToken();
            int sCity = cities.get(st.nextToken());
            int eCity = cities.get(st.nextToken());
            int no = Integer.parseInt(st.nextToken()) << 1;
            int yes = discount(type, no);

            if (no < normal[sCity][eCity]) normal[sCity][eCity] = normal[eCity][sCity] = no;
            if (yes < kPass[sCity][eCity]) kPass[sCity][eCity] = kPass[eCity][sCity] = yes;

        }

        // 플로이드 워샬로 사전 최소비용 정리
        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                if (i == k) continue;
                int nik = normal[i][k];
                int pik = kPass[i][k];
                if (nik == INF) continue;

                for (int j = 0; j < N; j++) {
                    if (k == j) continue;
                    int nkj = normal[k][j];
                    int pkj = kPass[k][j];
                    if (nkj == INF) continue;
                    int nij = nik + nkj, pij = pik + pkj;
                    if (nij < normal[i][j]) normal[i][j] = nij;
                    if (pij < kPass[i][j]) kPass[i][j] = pij;
                }
            }
        }

        int yesSum = 0, noSum = 0;
        for (int i = 1; i < M; i++) {
            int a = goal[i - 1], b = goal[i];
            noSum += normal[a][b];
            yesSum += kPass[a][b];
        }
        yesSum += R;
        System.out.print(yesSum < noSum ? "Yes" : "No");
    }

    private static int discount(String type, int cost) {
        switch (type) {
            case "S-Train":
            case "V-Train":
                return cost >> 1;
            case "ITX-Saemaeul":
            case "ITX-Cheongchun":
            case "Mugunghwa":
                return 0;
            case "Subway":
            case "Bus":
            case "Taxi":
            case "Airplane":
            case "KTX":
            default:
                return cost;
        }
    }
}