import java.io.*;
import java.util.*;

/**
 * @author python98
 * @description Simulation + Circular Buffer (Ring Queue) + Bitmask Mod
 * @performance 메모리: 12,136 KB, 동작시간: 120 ms
 */
/*
추가 인덱스로 벨트 이동(mov)을 계산. (mov+idx)%2N == 2N-1이면 내리는 곳
각 로봇이 존재하는 인덱스(idx)를 저장하는 배열. (idx+mov)%2N+1의 벨트 내구도 확인 시 괜찮으면 인덱스 갱신
올리는 위치(mov%2N) 내구도 확인 및 로봇 올림. 벨트[move%2N]--
벨트 내구도 깎을 때마다 0인지 확인하며 0이라면 cnt++
cnt가 K라면 단계 종료
 */
public class Main {
    static int N, N2, limit, K, raiseIdx, downIdx, roTop, roBot, cnt;
    static int[] beltD, robot;
    static boolean[] beltO;

    private static int nextInt() throws IOException {
        int n = 0, s= 1, c = System.in.read();
        while(c<=32) c = System.in.read();
        if(c=='-') {
            s = -1;
            c = System.in.read();
        }
        while(c>32) {
            n = (n<<3) + (n<<1) + (c&15);
            c = System.in.read();
        }
        return n*s;
    }

    public static void main(String[] args) throws IOException {
        N = nextInt();
        N2 = N << 1;
        K = nextInt();
        raiseIdx = 0;
        downIdx = N - 1;
        beltD = new int[N2];
        beltO = new boolean[N2];
        limit = 1;
        while (limit < N2) limit <<= 1;
        robot = new int[limit--];
        for (int i = 0; i < N2; i++) {
            beltD[i] = nextInt();
        }
        int step = 0;
        while (cnt < K) {
            step++;
            raiseIdx--;
            if (raiseIdx < 0) raiseIdx += N2;
            downIdx--;
            if (downIdx < 0) downIdx += N2;
            if (beltO[downIdx]) {
                beltO[downIdx] = false;
                roBot++;
                roBot &= limit;
            }

            int R = roBot;
            while (R != roTop) {
                int nIdx = robot[R] + 1;
                if (nIdx >= N2) nIdx -= N2;
                if (beltO[nIdx] || beltD[nIdx] <= 0) {
                    R++;
                    R&=limit;
                    continue;
                }
                beltO[robot[R]] = false;
                beltD[nIdx]--;
                if (beltD[nIdx] == 0) cnt++;
                if (nIdx == downIdx) {
                    roBot++;
                    roBot &= limit;
                } else {
                    beltO[nIdx] = true;
                    robot[R] = nIdx;
                }
                R++; // 다음 로봇 갱신
                R&=limit;
            }

            if (!beltO[raiseIdx] && beltD[raiseIdx] > 0) {
                beltO[raiseIdx] = true;
                beltD[raiseIdx]--;
                robot[roTop++] = raiseIdx;
                roTop &= limit;
                if (beltD[raiseIdx] == 0) cnt++;
            }
        }
        System.out.print(step);
    }
}