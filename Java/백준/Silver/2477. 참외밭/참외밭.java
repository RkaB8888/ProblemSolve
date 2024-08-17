

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int num = Integer.parseInt(br.readLine());
		int length[][] = new int[6][2];
		for (int i = 0; i < 6; i++) {
			String[] s = br.readLine().split(" ");
			length[i][0] = Integer.parseInt(s[0]);
			length[i][1] = Integer.parseInt(s[1]);
		}
		int maxHori = 0, maxHoriIndex = 0;
		int maxVer = 0, maxVerIndex = 0;
		for (int i = 0; i < 6; i++) {
			switch (length[i][0]) {
			case 1:// 동
			case 2:// 서
				if (maxHori < length[i][1]) {
					maxHori = length[i][1];
					maxHoriIndex = i;
				}
				break;
			case 3:// 남
			case 4:// 북
				if (maxVer < length[i][1]) {
					maxVer = length[i][1];
					maxVerIndex = i;
				}
				break;
			default:
				break;
			}
		}
		int subHori, subVer;
		if (maxHoriIndex == 0) {
			subVer = Math.abs(length[1][1] - length[5][1]);
		} else if (maxHoriIndex == 5) {
			subVer = Math.abs(length[0][1] - length[4][1]);
		} else {
			subVer = Math.abs(length[maxHoriIndex - 1][1] - length[maxHoriIndex + 1][1]);
		}
		if (maxVerIndex == 0) {
			subHori = Math.abs(length[1][1] - length[5][1]);
		} else if (maxVerIndex == 5) {
			subHori = Math.abs(length[0][1] - length[4][1]);
		} else {
			subHori = Math.abs(length[maxVerIndex - 1][1] - length[maxVerIndex + 1][1]);
		}

		System.out.println(((maxVer * maxHori) - (subHori * subVer)) * num);
	}

}
