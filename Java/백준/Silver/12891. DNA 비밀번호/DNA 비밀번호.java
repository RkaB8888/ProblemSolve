
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 
 * 메모리:21,836KB, 시간:172ms,
 */
public class Main {
	static int sLength;
	static int pLength;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		sLength = Integer.parseInt(st.nextToken());
		pLength = Integer.parseInt(st.nextToken());
		char[] dna = br.readLine().toCharArray();
		st = new StringTokenizer(br.readLine());
		int aCnt = Integer.parseInt(st.nextToken());
		int cCnt = Integer.parseInt(st.nextToken());
		int gCnt = Integer.parseInt(st.nextToken());
		int tCnt = Integer.parseInt(st.nextToken());
		int alphabet[] = new int[26];
		for (int i = 0; i < pLength; i++) {
			alphabet[dna[i] - 'A']++;
		}
		int result = 0;
		boolean aCheck = false, cCheck = false, gCheck = false, tCheck = false;
		if (alphabet[0] >= aCnt)
			aCheck = true;
		if (alphabet['C' - 'A'] >= cCnt)
			cCheck = true;
		if (alphabet['G' - 'A'] >= gCnt)
			gCheck = true;
		if (alphabet['T' - 'A'] >= tCnt)
			tCheck = true;
		if (aCheck && cCheck && gCheck && tCheck) {
			result++;
		}
		for (int i = pLength; i < sLength; i++) {
			alphabet[dna[i] - 'A']++;
			alphabet[dna[i - pLength] - 'A']--;
			if (alphabet[0] >= aCnt)
				aCheck = true;
			else
				aCheck = false;
			if (alphabet['C' - 'A'] >= cCnt)
				cCheck = true;
			else
				cCheck = false;
			if (alphabet['G' - 'A'] >= gCnt)
				gCheck = true;
			else
				gCheck = false;
			if (alphabet['T' - 'A'] >= tCnt)
				tCheck = true;
			else
				tCheck = false;
			if (aCheck && cCheck && gCheck && tCheck) {
				result++;
			}
		}
		System.out.println(result);

	}

}