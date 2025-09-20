import java.io.*;
import java.util.*;

/**
 * @author python98
 * @description ?
 * @performance 메모리: ? KB, 동작시간: ? ms
 */
public class Main {
    static int N;
    static int[] result;
    static int[][] days;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        result = new int[N + 1];
        days = new int[N + 1][10];
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            for (int j = 0; j < m; j++) {
                int num = Integer.parseInt(st.nextToken());
                days[i][num] = 1;
                days[i][0]++;
            }
        }

        int[] q = new int[1000];
        int top = 0;
        for (int i = 1; i <= N; i++) {
            if (days[i][0] == 1) {
                q[top++] = i;
            }
        }

        while (top > 0) {
            int day = q[--top];
            if(days[day][0]==0) {
                System.out.print(-1); // 선택할 수 없음
                return;
            }
            for (int i = 1; i < 10; i++) { // 무조건 1이 하나만 있음
                if (days[day][i] == 1) {
                    days[day][i] = 2; // 선택 한 것을 표시
                    days[day][0] = 0; // 선택할 필요 없음 표시
                    if (day > 1) { // 이전 날이 있다면
                        if (days[day - 1][i] == 1) { // 전 날의 종류가 존재하며
                            if (days[day - 1][0] == 1) { // 그것만 선택할 수 있는 경우
                                System.out.print(-1); // 전날과 충돌
                                return;
                            } else if (days[day - 1][0] == 2) { // 해당 종류를 제외한 한가지만 남았따면
                                q[top++] = day - 1; // 선택지가 정해졌으니 큐에 추가
                            }
                            days[day - 1][i] = 0;
                            days[day - 1][0]--;
                        } else if (days[day - 1][i] == 2) { // 이미 선택한 경우
                            System.out.print(-1); // 전날과 충돌
                            return;
                        }
                    }
//                    if (day < N) { // 다음 날이 있다면
//                        if (days[day + 1][i] == 1) { // 전 날의 종류가 존재하며
//                            if (days[day + 1][0] == 1) { // 그것만 선택할 수 있는 경우
//                                System.out.print(-1); // 전날과 충돌
//                                return;
//                            } else if (days[day + 1][0] == 2) { // 해당 종류를 제외한 한가지만 남았따면
//                                q[top++] = day + 1; // 선택지가 정해졌으니 큐에 추가
//                            }
//                            days[day + 1][i] = 0;
//                            days[day + 1][0]--;
//                        } else if (days[day + 1][i] == 2) { // 이미 선택한 경우
//                            System.out.print(-1); // 전날과 충돌
//                            return;
//                        }
//                    }
                    break;
                }
            }
        }
        for (int i = 1; i <= N; i++) {
            if (days[i][0] == 0) { // 이미 선택한 경우
                for (int j = 1; j < 10; j++) {
                    if (days[i][j] == 2) {
                        result[i] = j;
                        break;
                    }
                }
            } else { // 선택하지 않은 경우 (아무거나 상관없음)
                for (int j = 1; j < 10; j++) {
                    if(j == result[i-1]) continue; // 이전 거라면 패스
                    if (days[i][j] == 1) {
                        result[i] = j;
                        break;
                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(result[i]).append('\n');
        }
        System.out.print(sb);
    }

}