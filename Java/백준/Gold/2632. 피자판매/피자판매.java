import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author python98
 * 부분집합 DP
 *
 */

public class Main {
    static int size, N, M, n[], m[];
    static long result;
    static long ndp[], mdp[];

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        size = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        n = new int[2 * N];
        m = new int[2 * M];
        ndp = new long[size + 1];
        mdp = new long[size + 1];

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            n[i] = num;
            n[N + i] = num;  // 원형 배열을 2배로 확장
        }
        for (int i = 0; i < M; i++) {
            int num = Integer.parseInt(br.readLine());
            m[i] = num;
            m[M + i] = num;  // 원형 배열을 2배로 확장
        }

        calculatePartialSums(n, ndp, N);
        calculatePartialSums(m, mdp, M);

//        result += (long)ndp[size] + mdp[size];  // 피자 전체를 선택하는 경우
        for (int i = 0; i <= size; i++) {
            result += ndp[i] * mdp[size - i];
//            System.out.println(ndp[i]+" * "+mdp[size - i]+" = "+result);
        }

        System.out.println(result);
    }

    private static void calculatePartialSums(int[] pizza, long[] dp, int len) {
        for (int i = 0; i < len; i++) {
            int sum = 0;
            for (int j = 0; j < len-1; j++) {
                sum += pizza[i + j];
                if (sum > size) break;  // 불필요한 계산 방지
                dp[sum]++;
//                System.out.println(sum+"에 1 추가");
            }
        }
        dp[0] = 1;  // 아무 것도 선택하지 않는 경우
        int sum = 0;
        for(int i = 0 ; i < len ; i++) {
        	sum+=pizza[i];
        	if (sum > size) return;  
        }
        dp[sum]++;
    }
}