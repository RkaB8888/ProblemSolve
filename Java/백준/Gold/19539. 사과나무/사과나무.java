public class Main {

	public static void main(String[] args) throws Exception {
		int N = readInt();
		int[] cnt = {0,0};
		for (int i = 0; i < N; i++) {
			int temp = readInt();
			cnt[0]+=temp&1;
			cnt[1]+=temp>>1;
		}
		if(cnt[1]<cnt[0]) System.out.println("NO");
		else if(cnt[1]==cnt[0]) System.out.println("YES");
		else {
			if((cnt[1]-cnt[0])%3!=0) System.out.println("NO");
			else System.out.println("YES");
		}
	}
	public static int readInt() throws Exception {
	    int val = 0;
	    int c = System.in.read();
	    while (c <= ' ') {  // 공백 문자 건너뛰기
	        c = System.in.read();
	    }
	    do {
	        val = 10 * val + c - 48;  // 아스키 값으로 숫자 변환
	    } while ((c = System.in.read()) >= 48 && c <= 57);

	    return val;  // 양수 반환
	}


}
