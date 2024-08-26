import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
/**
 * 메모리:293,976kb, 시간:976ms
 */
public class Main {
	static int N;//들어올 문자열 갯수
	static char[][] inputString;//들어온 문자열을 문자로 쪼개서 저장
	static boolean[] isCounting;//해당 알파벳이 들어온적 있는지 확인
	static int charCnt;//알파벳 종류가 얼마나 있는지 카운팅
	static IndexValuePair[] priorChar;//각 알파벳의 가치를 저장
	static int[] num;
	static class IndexValuePair{
		int idx;
		double val;
		public IndexValuePair(int idx, double val) {
			super();
			this.idx = idx;
			this.val = val;
		}
		
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		inputString = new char[N][];
		isCounting = new boolean[26];
		charCnt = 0;
		priorChar = new IndexValuePair[26];
		num = new int[26];
		List<IndexValuePair> list = new ArrayList<>();
		for(int i = 0 ; i < N ; i++) {
			inputString[i] = br.readLine().toCharArray();
			int len = inputString[i].length;
			for(int j = 0 ; j < len ; j++) {
				int temp = inputString[i][j]-'A';
				if(priorChar[temp]==null) {
					IndexValuePair tempI = new IndexValuePair(temp,Math.pow(10, len-j));
					priorChar[temp] = tempI;
					list.add(tempI);
					charCnt++;
				}
				else {
					priorChar[temp].val+=Math.pow(10, len-j);
				}
			}
		}
		
		list.sort((a,b)->(int)(b.val-a.val));
		int n = 9;
		for(IndexValuePair i : list) {
			num[i.idx] = n--;
		}
		int result = 0;
		for(int i = 0 ; i < N ; i++) {
			int tempNum = 0;
			for(char c : inputString[i]) {
				tempNum = tempNum*10+num[c-'A'];
			}
			result+=tempNum;
		}
		System.out.println(result);
	}
	

}