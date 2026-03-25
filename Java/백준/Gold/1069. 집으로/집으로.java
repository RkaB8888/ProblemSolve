import java.io.*;
import java.util.*;

/**
 * @description Geometry
 * @performance 메모리: ? KB, 동작시간: ? ms
 * @author python98
 */
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		double X = Double.parseDouble(st.nextToken());
		double Y = Double.parseDouble(st.nextToken());
		double D = Double.parseDouble(st.nextToken());
		double T = Double.parseDouble(st.nextToken());
		double R = Math.sqrt(X*X+Y*Y);
		double jumps = Math.floor(R/D);
		double restR = R - (jumps * D);
		double onlyW = R;
		double JnWS = jumps * T + restR;
		double JnWO = (jumps+1) * T + (jumps + 1) * D - R;
		double onlyJ = jumps == 0.0 ? 2*T : (jumps+1) * T;
		double result = onlyW;
		if(result > JnWS) result = JnWS;
		if(result > JnWO) result = JnWO;
		if(result > onlyJ) result = onlyJ;
		System.out.print(result);
	}
}