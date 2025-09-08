import java.io.*;
import java.util.*;

/**
 * @author python98
 * @description Binary Search + Rolling Hash (Double Mod) + HashSet
 * @performance 메모리: 226,568 KB, 동작시간: 744 ms
 */
public class Main {

    static final int BASE = 37;
    static final int MOD1 = 1_000_000_007;
    static final int MOD2 = 1_000_000_009;

    static int L, result;
    static char[] s;
    static long[] pow1;
    static long[] pow2;

    private static long pack(long a, long b) {
        return (a << 32) ^ b;
    }

    private static int val(int i) {
        return s[i] - 'a' + 1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        L = Integer.parseInt(br.readLine());
        s = br.readLine().toCharArray();

        pow1 = new long[L];
        pow2 = new long[L];
        pow1[0] = 1;
        pow2[0] = 1;

        for (int i = 1; i < L; i++) {
            pow1[i] = (pow1[i - 1] * BASE) % MOD1;
            pow2[i] = (pow2[i - 1] * BASE) % MOD2;
        }

        int low = 0, high = L - 1;
        result = 0;
        LongHashSet set = new LongHashSet(L);
        while (low <= high) {
            int mid = (low + high) >>> 1;
            if (exist(mid, set)) {
                result = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        System.out.print(result);
    }

    private static boolean exist(int len, LongHashSet set) {
        if (len == 0) return true;
        set.clear();
        
        long hash1 = 0, hash2 = 0;
        for (int i = 0; i < len; i++) {
            hash1 = (hash1 * BASE + val(i)) % MOD1;
            hash2 = (hash2 * BASE + val(i)) % MOD2;
        }
        set.add(pack(hash1, hash2));

        for (int i = len, j = 0; i < L; i++, j++) {
            hash1 -= (pow1[len - 1] * val(j)) % MOD1;
            if (hash1 < 0) hash1 += MOD1;
            hash1 = (hash1 * BASE + val(i)) % MOD1;

            hash2 -= (pow2[len - 1] * val(j)) % MOD2;
            if (hash2 < 0) hash2 += MOD2;
            hash2 = (hash2 * BASE + val(i)) % MOD2;

            long packed = pack(hash1, hash2);
            if (set.contains(packed)) {
                return true;
            }
            set.add(packed);
        }
        return false;
    }

    static class LongHashSet {
        static long[] table;
        static int size;
        static int thresh;
        static int mask;

        LongHashSet(int cap) {
            int n = 1;
            while (n < cap) n <<= 1;
            table = new long[n];
            size = 0;
            thresh = (int) (n * 0.75);
            mask = n - 1;
        }

        private void resize() {
            long[] old = table;
            int n = old.length << 1;
            long[] newTable = new long[n];
            int newMask = n - 1;
            for (long v : old) {
                if (v != 0) {
                    int idx = (int) (v ^ (v >>> 33)) & newMask;
                    while (newTable[idx] != 0) {
                        idx = (idx + 1) & newMask;
                    }
                    newTable[idx] = v;
                }
            }
            table = newTable;
            mask = newMask;
            thresh = (int) (n * 0.75);
        }

        private boolean add(long key) {
            if (size >= thresh) resize();
            long k = key + 1;
            int idx = (int) (k ^ (k >>> 33)) & mask;
            while (true) {
                if (table[idx] == 0) {
                    size++;
                    table[idx] = k;
                    return true;
                }
                if (table[idx] == k) return false;
                idx = (idx + 1) & mask;
            }
        }

        private boolean contains(long key){
            long k = key+1;
            int idx = (int)(k^(k>>>33))&mask;
            while(true){
                long cur = table[idx];
                if(cur==0) return false;
                if(cur==k) return true;
                idx = (idx+1)&mask;
            }
        }

        private void clear(){
            Arrays.fill(table, 0);
            size = 0;
        }
    }
}