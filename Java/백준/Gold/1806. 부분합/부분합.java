import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int minLength = Integer.MAX_VALUE;
        int left = 0, right = 0, currentSum = 0;

        while (right < N) {
            currentSum += arr[right++];

            while (currentSum >= S) {
                minLength = Math.min(minLength, right - left);
                currentSum -= arr[left++];
            }
        }

        System.out.println(minLength == Integer.MAX_VALUE ? 0 : minLength);
    }
}