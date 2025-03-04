import java.io.*;

/**
 * @description 구현
 * @performance 메모리: ? KB, 동작시간: ? ms
 * @author python98
 */
public class Main {
	static final int BASE = 31;
	static final long MOD = 1234567891;
	
	static int L;
	static char[] c;
	static int[] conv;
	static long result;
	static long[] p;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        L = Integer.parseInt(br.readLine());
        c = br.readLine().toCharArray();
        
        conv = new int[L];
        for(int i = 0 ; i < L ; i++) {
        	conv[i] = c[i]-'a' +1; // a:1부터 시작
        }
        
        p = new long[L];
        p[0] = 1;
        for(int i = 1 ; i < L ; i++) {
        	p[i] = (p[i-1] * BASE) % MOD; // 각 idx에 곱해줘야 하는 값 세팅
        }
        
        result = 0;
        for(int i = 0 ; i < L ; i++) {
        	result += (p[i] * conv[i]) % MOD;
        	result%=MOD;
        }
        
        System.out.print(result);
	}
}