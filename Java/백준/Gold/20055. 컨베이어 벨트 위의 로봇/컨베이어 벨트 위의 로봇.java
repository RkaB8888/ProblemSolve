import java.io.*;
import java.util.*;

/**
 * @author python98
 * @description ?
 * @performance 메모리: ? KB, 동작시간: ? ms
 */
/*
추가 인덱스로 벨트 이동(mov)을 계산. (mov+idx)%2N == 2N-1이면 내리는 곳
각 로봇이 존재하는 인덱스(idx)를 저장하는 배열. (idx+mov)%2N+1의 벨트 내구도 확인 시 괜찮으면 인덱스 갱신
올리는 위치(mov%2N) 내구도 확인 및 로봇 올림. 벨트[move%2N]--
벨트 내구도 깎을 때마다 0인지 확인하며 0이라면 cnt++
cnt가 K라면 단계 종료
 */
public class Main {
    static int N, N2, K, mov, raiseIdx, downIdx, roTop, roBot, cnt;
    static int[] beltD, robot;
    static boolean[] beltO;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        N2 = N << 1;
        K = Integer.parseInt(st.nextToken());
        mov = 0;
        raiseIdx = 0;
        downIdx = N - 1;
        beltD = new int[N2];
        beltO = new boolean[N2];
        robot = new int[200000];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N2; i++) {
            beltD[i] = Integer.parseInt(st.nextToken());
        }
        int step = 1;
        while (true) {
            beltRotate();
            moveRobot();
            raiseRobot();
            if (countDur()) break;
            step++;
        }
        System.out.print(step);
    }

    private static boolean countDur() {
        return cnt >= K;
    }

    private static void raiseRobot() {
        if(beltO[raiseIdx]||beltD[raiseIdx]<=0) return;
        beltO[raiseIdx] = true;
        beltD[raiseIdx]--;
        robot[roTop++] = raiseIdx;
        if(beltD[raiseIdx]==0) cnt++;
    }

    private static void moveRobot() {
        int R = roBot;
        while (R < roTop) {
            int idx = robot[R]; // 로봇의 현재 위치 인덱스 가져오기
            int nIdx = (idx + 1) % N2;
            if (beltO[nIdx] || beltD[nIdx] <= 0) {
                R++;
                continue;
            }
            beltO[idx] = false;
            beltO[nIdx] = true;
            beltD[nIdx]--;
            robot[R] = (robot[R] + 1) % N2;
            if (beltD[nIdx] == 0) cnt++;
            if (nIdx == downIdx) {
                beltO[nIdx] = false;
                roBot++;
            }
            R++; // 다음 로봇
        }
    }

    private static void beltRotate() {
        mov = (mov + 1) % (N2);
        raiseIdx = (raiseIdx + N2 - 1) % N2;
        downIdx = (downIdx + N2 - 1) % N2;
        if(beltO[downIdx]) {
            beltO[downIdx] = false;
            roBot++;
        }
    }
}