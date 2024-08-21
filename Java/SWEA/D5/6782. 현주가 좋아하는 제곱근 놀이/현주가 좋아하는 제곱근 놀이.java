/**
 * 메모리:29,772KB, 시간:290ms
 * @author SSAFY
 *
 */
public class Solution {
    static double N;
    static long result;
    public static void main(String[] args) throws Exception {
         
        StringBuilder sb = new StringBuilder();
        int T = readInt();
        for(int tc=1; tc<=T; tc++) {
            N = readDouble();
            result = 0;
            process();
            sb.append("#").append(tc).append(" ").append(result).append("\n");
        }
        System.out.println(sb);
    }
    public static void process() {
    	double tempCeil;
    	double tempPow;
        while(N>2) {
            tempCeil = Math.ceil(Math.sqrt(N));
            tempPow = Math.pow(tempCeil, 2);
            result+=tempPow-N+1;
            N=tempCeil;
        }
    }
    public static int readInt() throws Exception{
    	int val = 0;
    	int c = System.in.read();
    	while(c<=' ') {
    		c = System.in.read();
    	}
    	do {
    		val = 10*val+c-48;
    	}while((c=System.in.read()) >= 48 && c <= 57);
    	return val;
    }
    public static double readDouble() throws Exception{
    	double val = 0;
    	int c = System.in.read();
    	while(c<=' ') {
    		c = System.in.read();
    	}
    	do {
    		val = 10*val+c-48;
    	}while((c=System.in.read()) >= 48 && c <= 57);
    	return val;
    }
 
}