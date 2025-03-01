import java.io.*;
import java.util.*;

/**
 * @description 라빈카프
 * @performance 메모리: ? KB, 동작시간: ? ms
 * @author python98
 */
public class Main {
	
	static final long BASE = 5381;
	static final long MOD = 1000000007;
	
	static int hp, wp, hm, wm, result;
	static long patternCode;
	static char[][] p, m;
	static long[] powC;
	static long[] powR;
	static long x;
	static long[][] prefix;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		hp = Integer.parseInt(st.nextToken());
		wp = Integer.parseInt(st.nextToken());
		hm = Integer.parseInt(st.nextToken());
		wm = Integer.parseInt(st.nextToken());
		
		p = new char[hp][];
		m = new char[hm][];
		
		for(int i = 0 ; i < hp ; i++) {
			p[i] = br.readLine().toCharArray();
		}
		for(int i = 0 ; i < hm ; i++) {
			m[i] = br.readLine().toCharArray();
		}
		
		//해당 열에 곱할 값 세팅
		powC = new long[wm+1];
		powC[0] = 1;
		for(int i = 1 ; i <= wm ; i++) {
			powC[i] = (powC[i-1]*BASE)%MOD;
		}
		
		//행 단위의 해시 누적합 전처리
		prefix = new long[hm][wm + 1];
        for (int i = 0; i < hm; i++) {
            prefix[i][0] = 0;
            for (int j = 0; j < wm; j++) {
                prefix[i][j + 1] = (prefix[i][j] * BASE + m[i][j]) % MOD;
            }
        }
        
        patternCode = computeFlatHash(p, hp, wp);
		
		x = powC[wp];
		powR = new long[hp+1];
		powR[0] = 1;
		for(int i = 1 ; i <= hp ; i++) {
			powR[i] = (powR[i-1]*x)%MOD;
		}
		
		result = 0;
		for(int j = 0 ; j <= wm-wp ; j++) {
			long[] rowCombined  = new long[hm+1];
			rowCombined [0] = 0;
			for(int i = 0 ; i < hm ; i++) {
				long rowHash = (prefix[i][j + wp] - prefix[i][j] * powC[wp] % MOD + MOD) % MOD;
				rowCombined [i+1] = (rowCombined[i]*x+rowHash)%MOD;
			}
			for(int i = 0 ; i <= hm-hp ; i++) {
				long candidate = (rowCombined[i+hp]-(rowCombined[i]*powR[hp])%MOD+MOD)%MOD;
				if(patternCode==candidate) {
					result++;
				}
			}
		}
		System.out.print(result);
	}
	
	//2차원 배열의 해시값 계산
	static long computeFlatHash(char[][] M, int rows, int cols) {
		long baseX = powC[cols];
		long hash = 0;
		for(int i = 0 ; i < rows ; i++) {
			long rowHash = 0;
			for(int j = 0 ; j < cols ; j++) {
				rowHash = (rowHash*BASE+M[i][j])%MOD;
			}
			hash = (hash*baseX+rowHash)%MOD;
		}
		return hash;
	}
	
}