import java.io.*;
import java.util.*;

public class Main {

    // 정답코드와 동일한 기저값 사용: 행 해싱 37, 세로 결합 5831
    static final long BASE_COL = 37;
    static final long BASE_ROW = 5831;
    static final long MOD1 = 1000000007;
    static final long MOD2 = 1000000009;

    static int hp, wp, hm, wm, result;
    static char[][] p, m;
    static long[][] prefix1, prefix2;
    static long[] powC1, powC2;
    static Set<Pair> patternSet0;
    static Set<Pair> patternSet90;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        hp = Integer.parseInt(st.nextToken());
        wp = Integer.parseInt(st.nextToken());
        hm = Integer.parseInt(st.nextToken());
        wm = Integer.parseInt(st.nextToken());

        p = new char[hp][];
        m = new char[hm][];

        for (int i = 0; i < hp; i++) {
            p[i] = br.readLine().toCharArray();
        }
        for (int i = 0; i < hm; i++) {
            m[i] = br.readLine().toCharArray();
        }

        // powC 배열은 메인 행렬의 열 길이와 패턴 크기를 모두 커버하도록 계산
        int maxW = Math.max(wm, Math.max(hp, wp));
        powC1 = new long[maxW + 1];
        powC2 = new long[maxW + 1];
        powC1[0] = 1;
        powC2[0] = 1;
        for (int i = 1; i <= maxW; i++) {
            powC1[i] = (powC1[i - 1] * BASE_COL) % MOD1;
            powC2[i] = (powC2[i - 1] * BASE_COL) % MOD2;
        }

        // 메인 매트릭스 m의 각 행에 대해 prefix 해시 계산 (문자 그대로 사용)
        prefix1 = new long[hm][wm + 1];
        prefix2 = new long[hm][wm + 1];
        for (int i = 0; i < hm; i++) {
            prefix1[i][0] = 0;
            prefix2[i][0] = 0;
            for (int j = 0; j < wm; j++) {
                prefix1[i][j + 1] = (prefix1[i][j] * BASE_COL + m[i][j]) % MOD1;
                prefix2[i][j + 1] = (prefix2[i][j] * BASE_COL + m[i][j]) % MOD2;
            }
        }

        // 패턴 해시 계산 – **역순(아래쪽부터 위로)으로 결합**
        Pair pattern0 = computeFlatHashReverse(p, hp, wp, BASE_ROW, BASE_COL);
//        Pair pattern180 = computeFlatHashReverse(rotate180(p), hp, wp, BASE_ROW, BASE_COL);
        patternSet0 = new HashSet<>();
        patternSet0.add(pattern0);
//        patternSet0.add(pattern180);

//        patternSet90 = new HashSet<>();
//        if (hp != wp) {
//            Pair pattern90 = computeFlatHashReverse(rotate90(p), wp, hp, BASE_ROW, BASE_COL);
//            Pair pattern270 = computeFlatHashReverse(rotate270(p), wp, hp, BASE_ROW, BASE_COL);
//            patternSet90.add(pattern90);
//            patternSet90.add(pattern270);
//        } else {
//            // 정사각형 패턴인 경우에도 90°와 270° 해시를 추가
//            Pair pattern90 = computeFlatHashReverse(rotate90(p), wp, hp, BASE_ROW, BASE_COL);
//            Pair pattern270 = computeFlatHashReverse(rotate270(p), wp, hp, BASE_ROW, BASE_COL);
//            patternSet0.add(pattern90);
//            patternSet0.add(pattern270);
//        }

        result = 0;
        // 원본 및 180° (크기: hp×wp) 패턴 검색
        result += searchCandidatesReverse(hp, wp, hm, wm, patternSet0, BASE_ROW, powC1, powC2, prefix1, prefix2);
        // 직사각형인 경우 90°, 270° (크기: wp×hp) 패턴 검색
//        if (hp != wp) {
//            result += searchCandidatesReverse(wp, hp, hm, wm, patternSet90, BASE_ROW, powC1, powC2, prefix1, prefix2);
//        }
        System.out.print(result);
    }

    // computeFlatHashReverse() – 행들을 마지막 행부터 첫 행으로 역순 결합
    static Pair computeFlatHashReverse(char[][] M, int rows, int cols, long rowBase, long colBase) {
        long hash1 = 0, hash2 = 0;
        for (int i = rows - 1; i >= 0; i--) {
            long rowHash1 = 0, rowHash2 = 0;
            for (int j = 0; j < cols; j++) {
                rowHash1 = (rowHash1 * colBase + M[i][j]) % MOD1;
                rowHash2 = (rowHash2 * colBase + M[i][j]) % MOD2;
            }
            hash1 = (hash1 * rowBase + rowHash1) % MOD1;
            hash2 = (hash2 * rowBase + rowHash2) % MOD2;
        }
        return new Pair(hash1, hash2);
    }

    // searchCandidatesReverse() – 본문 m의 후보 해시를 구할 때 각 열 윈도우 내에서 행들을 역순(아래쪽부터 위)으로 결합
    static long searchCandidatesReverse(int subh, int subw, int mainh, int mainw, Set<Pair> patternSet, long rowBase,
                                 long[] powC1, long[] powC2, long[][] prefix1, long[][] prefix2) {
        long cnt = 0;
        long[] powR1 = new long[subh + 1];
        long[] powR2 = new long[subh + 1];
        powR1[0] = 1;
        powR2[0] = 1;
        for (int i = 1; i <= subh; i++) {
            powR1[i] = (powR1[i - 1] * rowBase) % MOD1;
            powR2[i] = (powR2[i - 1] * rowBase) % MOD2;
        }

        // j: 시작 열 인덱스
        for (int j = 0; j <= mainw - subw; j++) {
            // revCombined 배열: 본문의 행들을 **역순**으로 결합할 예정
            long[] revCombined1 = new long[mainh + 1];
            long[] revCombined2 = new long[mainh + 1];
            revCombined1[0] = 0;
            revCombined2[0] = 0;
            // 아래쪽(마지막 행)부터 위쪽으로 결합
            for (int i = mainh - 1; i >= 0; i--) {
                long rowHash1 = (prefix1[i][j + subw] - (prefix1[i][j] * powC1[subw]) % MOD1 + MOD1) % MOD1;
                long rowHash2 = (prefix2[i][j + subw] - (prefix2[i][j] * powC2[subw]) % MOD2 + MOD2) % MOD2;
                int idx = mainh - i;  // revCombined 인덱스 (0부터 시작)
                revCombined1[idx] = (revCombined1[idx - 1] * rowBase + rowHash1) % MOD1;
                revCombined2[idx] = (revCombined2[idx - 1] * rowBase + rowHash2) % MOD2;
            }
            // 이제 revCombined[k]는 본문의 하단부터 k개의 행을 결합한 해시이다.
            // 후보 해시는 연속된 subh 행에 해당하는 구간의 차이로 구한다.
            for (int i = 0; i <= mainh - subh; i++) {
                int start = i;
                int end = i + subh;
                long candidate1 = (revCombined1[end] - (revCombined1[start] * powR1[subh]) % MOD1 + MOD1) % MOD1;
                long candidate2 = (revCombined2[end] - (revCombined2[start] * powR2[subh]) % MOD2 + MOD2) % MOD2;
                Pair candidate = new Pair(candidate1, candidate2);
                if (patternSet.contains(candidate)) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

//    // 회전 함수들은 기존과 동일
//    static char[][] rotate90(char[][] M) {
//        char[][] res = new char[wp][hp];
//        for (int j = 0; j < wp; j++) {
//            for (int i = hp - 1; i >= 0; i--) {
//                res[j][hp - 1 - i] = M[i][j];
//            }
//        }
//        return res;
//    }
//
//    static char[][] rotate180(char[][] M) {
//        char[][] res = new char[hp][wp];
//        for (int i = hp - 1; i >= 0; i--) {
//            for (int j = wp - 1; j >= 0; j--) {
//                res[hp - 1 - i][wp - 1 - j] = M[i][j];
//            }
//        }
//        return res;
//    }
//
//    static char[][] rotate270(char[][] M) {
//        char[][] res = new char[wp][hp];
//        for (int j = wp - 1; j >= 0; j--) {
//            for (int i = 0; i < hp; i++) {
//                res[wp - 1 - j][i] = M[i][j];
//            }
//        }
//        return res;
//    }

    static class Pair {
        long h1, h2;
        public Pair(long h1, long h2) {
            this.h1 = h1;
            this.h2 = h2;
        }
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Pair)) return false;
            Pair p = (Pair) o;
            return h1 == p.h1 && h2 == p.h2;
        }
        @Override
        public int hashCode() {
            return Long.hashCode(h1) * 31 + Long.hashCode(h2);
        }
    }
}