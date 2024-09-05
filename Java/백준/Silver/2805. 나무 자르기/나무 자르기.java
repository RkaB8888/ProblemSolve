import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static long[] trees;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        // 입력 받기
        N = Integer.parseInt(st.nextToken());  // 나무의 수
        M = Integer.parseInt(st.nextToken());  // 필요한 나무의 길이
        trees = new long[N];
        
        st = new StringTokenizer(br.readLine());
        long maxTree = 0;  // 가장 큰 나무의 높이
        for (int i = 0; i < N; i++) {
            trees[i] = Long.parseLong(st.nextToken());
            if (trees[i] > maxTree) {
                maxTree = trees[i];  // 나무의 최대 높이를 저장
            }
        }
        
        // 이분 탐색
        long min = 0;
        long max = maxTree;
        long result = 0;  // 절단기의 최적 높이
        
        while (min <= max) {
            long mid = (min + max) / 2;
            long sum = 0;
            
            // 잘린 나무들의 총 길이를 계산
            for (int i = 0; i < N; i++) {
                if (trees[i] > mid) {
                    sum += trees[i] - mid;
                }
            }
            
            // 나무 길이가 M 이상이면, 더 큰 절단기 높이 탐색
            if (sum >= M) {
                result = mid;  // 절단기의 높이 갱신
                min = mid + 1; // 더 큰 값에서 탐색
            } else {
                max = mid - 1; // 나무의 양이 부족하므로 절단기 높이를 줄임
            }
        }
        
        // 결과 출력
        System.out.println(result);
    }
}