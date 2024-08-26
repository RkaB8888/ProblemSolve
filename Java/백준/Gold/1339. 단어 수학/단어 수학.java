import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int N;//단어 갯수
	static char[][] inputString;//받은 단어를 글자로 쪼개서 저장
	static int cntChar; // 받은 글자 갯수
	static int[] num; // 각 글자에 할당할 숫자를 저장
	static int result; // 최대 합 저장
	static int minNum; // 숫자 사용의 최저점
	static boolean[] isSelected = new boolean[10];//해당 숫자를 할당했었는지
	static boolean[] check = new boolean[26];//이전에 받았는지 확인
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		inputString = new char[N][];//입력 받은 단어를 쪼개서 저장
		
		for(int i = 0 ; i < N ; i++) {
			inputString[i] = br.readLine().toCharArray();
			for(char c : inputString[i]) {
				if(!check[c-'A']) {
					check[c-'A'] = true;
					cntChar++;
				}
			}
		}
		num = new int[cntChar];
		minNum = 10-cntChar;
		loop(0);
		System.out.println(result);
	}
	
	public static void loop(int cnt) {
		if(cnt==cntChar) {
			int[] numtemp = new int[26];
			for(int i = 0 , j = 0; i < 26 ; i++) {
				if(check[i]) numtemp[i] = num[j++];
			}
			int sum = 0;
			for(int i = 0 ; i < N ; i++) {
				int val = 0;
				for(char c : inputString[i]) {
					val = val*10 + numtemp[c-'A'];
				}
				sum+=val;
			}
			if(sum>result) result = sum;
			return;
		}
		for(int i = 9 ; i >= minNum ; i--) {
			if(isSelected[i]) continue;
			isSelected[i] = true;
			num[cnt] = i;
			loop(cnt+1);
			isSelected[i] = false;
		}
	}

}