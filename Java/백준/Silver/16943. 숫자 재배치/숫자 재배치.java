import java.io.*;
import java.util.*;

/**
 * @author python98
 * @description dfs + 넥퍼
 * @performance 메모리: 12,632 KB, 동작시간: 108 ms
 */
public class Main {
    static char[] aChars, bChars, result;
    static int aLen, bLen;
    static int[] counts;
    static boolean flag;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        String aStr = st.nextToken();
        String bStr = st.nextToken();

        aLen = aStr.length();
        aChars = aStr.toCharArray();
        bLen = bStr.length();
        bChars = bStr.toCharArray();

        if (aLen < bLen) {
            Arrays.sort(aChars);
            reverse(aChars, 0, aLen - 1);
            System.out.print(new String(aChars));
            return;
        }
        if (aLen > bLen) {
            System.out.print(-1);
            return;
        }
        counts = new int[10];
        for (char c : aChars) {
            counts[c & 15]++;
        }

        result = new char[aLen];
        dfs(0, true);

        if(!flag || result[0]==48) {
            System.out.print(-1);
            return;
        }

        String resultStr = new String(result);
        if(resultStr.equals(bStr)) {
            if(!nextPer(result)) {
                System.out.print(-1);
            } else {
                System.out.print(new String(result));
            }
        } else {
            System.out.print(resultStr);
        }
    }

    private static void dfs(int k, boolean isTight) {
        if (flag) return;
        if (k == aLen) {
            flag = true;
            return;
        }

        int limit = isTight ? (bChars[k] & 15) : 9; // b의 자릿수 보다 작아야 함.
        for (int d = limit; d >= 0; d--) {
            if (counts[d] > 0) {
                result[k] = (char) (d + 48);
                counts[d]--;
                dfs(k + 1, isTight && (d == limit));

                if(flag) return;
                counts[d]++;
            }
        }
    }

    private static boolean nextPer(char[] arr) {
        int i = arr.length - 1;
        while (i > 0 && arr[i - 1] <= arr[i]) i--;
        if (i == 0) return false;

        int j = arr.length - 1;
        while (arr[i - 1] <= arr[j]) j--;

        swap(arr, i - 1, j);
        reverse(arr, i, arr.length - 1);
        return arr[0] != '0';
    }

    private static void swap(char[] arr, int a, int b) {
        char temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    private static void reverse(char[] arr, int start, int end) {
        while (start < end) {
            swap(arr, start++, end--);
        }
    }
}