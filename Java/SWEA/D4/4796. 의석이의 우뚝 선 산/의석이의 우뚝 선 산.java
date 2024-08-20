/**
 * 메모리:108,832KB, 시간:879ms
 * @author SSAFY
 *
 */

public class Solution {
	
	public static void main(String[] args) throws Exception {
		
		StringBuilder sb = new StringBuilder();
		int T = readInt();
		for(int tc=1; tc<=T; tc++) {
			int N = readInt();//산의 갯수
			int preH = readInt();//이전 산의 높이
			int curH = 0;//현재 산의 높이
			boolean riseFlag = false;//올라가는 중인지
			int result = 0;//출력할 결과 저장
			int riseCnt = 0;//얼마나 올라갔는지
			for(int i = 1 ; i < N ; i++) {
				curH = readInt();
				if(preH<curH) {//값이 상승했다.
					if(!riseFlag) {//하락 구간을 벗어났다.
						riseCnt = 0;//이전 산의 것은 초기화
						riseFlag= true;//상승의 시작
					}
					riseCnt++;
				}
				else {//값이 하락했다.
					if(riseFlag)//꼭대기에서 꺾였다.
						riseFlag = false;
					result+=riseCnt;
				}
				preH=curH;
			}
			
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}
		System.out.println(sb);
	}
	public static int readInt() throws Exception {
		int val = 0;
		int c = System.in.read();
		while(c<=' ') {
			c = System.in.read();
		}
		do {
			val = 10*val+c-48;
		}while((c=System.in.read()) >= 48 && c<=57);
		return val;
	}

}