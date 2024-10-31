import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
/**
 * 이중 해시 누적 해시 메모리 ? KB 시간 ? ms 
 * 
 * @author hslee1659
 */
public class Main {
    static final long MOD1 = 1000000007;
    static final long MOD2 = 1000000009;
    static final int BASE1 = 31;
    static final int BASE2 = 37;

    static long[] powers1, powers2;

    static void calculatePowers(int maxLength) {
        powers1 = new long[maxLength];
        powers2 = new long[maxLength];
        powers1[0] = powers2[0] = 1;
        for (int i = 1; i < maxLength; i++) {
            powers1[i] = (powers1[i-1] * BASE1) % MOD1;
            powers2[i] = (powers2[i-1] * BASE2) % MOD2;
        }
    }

    static class Pair {
        long hash1, hash2;
        Pair(long hash1, long hash2) {
            this.hash1 = hash1;
            this.hash2 = hash2;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return hash1 == pair.hash1 && hash2 == pair.hash2;
        }

        @Override
        public int hashCode() {
            return Objects.hash(hash1, hash2);
        }
    }

    static Pair getHash(String s) {
        long hash1 = 0, hash2 = 0;
        for (int i = 0; i < s.length(); i++) {
            hash1 = (hash1 * BASE1 + (s.charAt(i) - 'a' + 1)) % MOD1;
            hash2 = (hash2 * BASE2 + (s.charAt(i) - 'a' + 1)) % MOD2;
        }
        return new Pair(hash1, hash2);
    }

    static boolean isValidColorName(Set<Pair> colorHashes, Set<Pair> nameHashes, String input) {
        long[] prefixHashes1 = new long[input.length() + 1];
        long[] prefixHashes2 = new long[input.length() + 1];
        prefixHashes1[0] = prefixHashes2[0] = 0;
        for (int i = 0; i < input.length(); i++) {
            prefixHashes1[i + 1] = (prefixHashes1[i] * BASE1 + (input.charAt(i) - 'a' + 1)) % MOD1;
            prefixHashes2[i + 1] = (prefixHashes2[i] * BASE2 + (input.charAt(i) - 'a' + 1)) % MOD2;
        }

        for (int i = 1; i <= input.length(); i++) {
            Pair colorHash = new Pair(prefixHashes1[i], prefixHashes2[i]);
            if (colorHashes.contains(colorHash)) {
                long nameHash1 = (prefixHashes1[input.length()] - (colorHash.hash1 * powers1[input.length() - i]) % MOD1 + MOD1) % MOD1;
                long nameHash2 = (prefixHashes2[input.length()] - (colorHash.hash2 * powers2[input.length() - i]) % MOD2 + MOD2) % MOD2;
                Pair nameHash = new Pair(nameHash1, nameHash2);
                if (nameHashes.contains(nameHash)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int C = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        calculatePowers(2001);  // 최대 문자열 길이 + 1

        Set<Pair> colorHashes = new HashSet<>();
        Set<Pair> nameHashes = new HashSet<>();

        for (int i = 0; i < C; i++) {
            colorHashes.add(getHash(br.readLine()));
        }
        for (int i = 0; i < N; i++) {
            nameHashes.add(getHash(br.readLine()));
        }

        int Q = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < Q; i++) {
            String input = br.readLine();
            sb.append(isValidColorName(colorHashes, nameHashes, input) ? "Yes\n" : "No\n");
        }
        System.out.print(sb);
    }
}