import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 수학 메모리 11,632 KB 시간 68 ms
 */
public class Main {
    static double start, end, distance, halfDistance;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder result = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            start = Integer.parseInt(st.nextToken());
            end = Integer.parseInt(st.nextToken());

            distance = end - start;
            halfDistance = distance / 2;

            int minMoves = calculateMoves();
            result.append(minMoves).append('\n');
        }
        System.out.print(result);
    }

    private static int calculateMoves() {
        double maxNP = Math.round((-1 + Math.sqrt(1 + 8 * halfDistance)) / 2);
        double sumToHalfDistance = maxNP * (maxNP + 1);

        if (sumToHalfDistance + maxNP + 1 < distance) return (int)maxNP * 2 + 2;
        if (sumToHalfDistance < distance && distance <= sumToHalfDistance + maxNP + 1) return (int)maxNP * 2 + 1;
        if (sumToHalfDistance - maxNP < distance && distance <= sumToHalfDistance) return (int)maxNP * 2;
        return (int)maxNP * 2 - 1;
    }
}