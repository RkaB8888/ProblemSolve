
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int theOtherSide[] = { 5, 3, 4, 1, 2, 0 };
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int num = Integer.parseInt(br.readLine());
		int dice[][] = new int[num][6];// 주사위 숫자 위치 받음
		int whitchNum[][] = new int[num][7];// 주사위 숫자가 위치한 인덱스
		
		for (int i = 0; i < num; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 6; j++) {
				dice[i][j] = Integer.parseInt(st.nextToken());
				whitchNum[i][dice[i][j]] = j; 
			}
		}
		int sum = 0, temp = 0;
		for (int i = 0; i < 6; i++) {
			temp += plusNum(dice[0],i);// 더함
			int nextNum = dice[0][theOtherSide[i]];// 반대편 숫자 확인
			for (int j = 1; j < num; j++) {
				int curIdx = whitchNum[j][nextNum];// 다음 주사위의 반대편 숫자가 위치한 곳 확인
				temp += plusNum(dice[j],curIdx);// 더함
				nextNum = dice[j][theOtherSide[curIdx]];// 반대편 숫자 확인
			}
			if (sum < temp)
				sum = temp;
			temp = 0;
		}
		System.out.println(sum);
	}

	public static int plusNum(int[] dice,int i) {
		if (dice[i]==6||dice[theOtherSide[i]]==6) {
			if(dice[i]==5||dice[theOtherSide[i]]==5) {
				return 4;
			}
			else return 5;
		}
		else return 6;
	}
}