import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	//전화하면 L초동안 안내멘트 -> 끝나면 5초간 무음
	//이때 고객이 요청시 바로 연결 ->
	//요청 안하면 다시 안내멘트
	//고객은 D초마다 1번씩 1초간 연결 요청
	//N번 재생된 후 반드시 연결
	static int N;//번 재생된 후 반드시 연결
	static int L;//안내멘트 초
	static int D;//초마다 1번씩 1초간 요청상태
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		int DTime = D;
		int LTime = 0;
		int NTime = 0;
		int resultTime = 0;
		while(NTime<N) {
			if(LTime<=DTime) {
				LTime+=L+5;
				NTime++;	
			}
			else if(DTime+1<=LTime-5) {
				DTime+=D;
			}
			else {
				break;
			}
		}
		while(DTime+1<=LTime-5) {
			DTime+=D;
		}
		if(DTime>=LTime-5) {
			resultTime = DTime;
		}
		else {
			resultTime = LTime-5;
		}
		System.out.println(resultTime);
	}

}