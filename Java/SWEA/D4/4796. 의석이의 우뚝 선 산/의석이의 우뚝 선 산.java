import java.io.IOException;
import java.util.Scanner;

/**
 * 메모리:108,832KB, 시간:879ms
 * @author SSAFY
 *
 */

public class Solution {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner sc = new Scanner(System.in);
		
		StringBuilder sb = new StringBuilder();
		int T = sc.nextInt();
		for(int tc=1; tc<=T; tc++) {
			int N = sc.nextInt();//산의 갯수
			int preH = sc.nextInt();//이전 산의 높이
			int curH = 0;//현재 산의 높이
			boolean riseFlag = false;//올라가는 중인지
			int result = 0;//출력할 결과 저장
			int riseCnt = 0;//얼마나 올라갔는지
			for(int i = 1 ; i < N ; i++) {
				curH = sc.nextInt();
				if(preH<curH) {//값이 상승했다.
					if(riseFlag) {//꾸준히 상승하는 구간이다.
						riseCnt++;
					}
					else {//하락 구간을 벗어났다.
//						riseCnt+=2; 이전의 하락 마지막 포인트까지 고려했으나
//						꼭대기를 포함 안시키는 점을 고려해서 1만 추가
						riseCnt = 0;//이전 산의 것은 초기화
						riseCnt++;
						riseFlag= true;//상승의 시작
					}
				}
				else {//값이 하락했다.
					if(riseFlag) {//꼭대기에서 꺾였다.
						riseFlag = false;
						result+=riseCnt;
					}
					else {//계속 하락하는 구간
						result+=riseCnt;
					}
				}
				preH=curH;
			}
			
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}
		System.out.println(sb);
		sc.close();
	}

}