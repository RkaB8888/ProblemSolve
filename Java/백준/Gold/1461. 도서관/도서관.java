import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 
 * @author python98 그리디 ? KB ? ms
 *
 */

public class Main {
	static int N, M, result;
	static List<Integer> minus, plus;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		minus = new ArrayList<>();
		plus = new ArrayList<>();
		for(int i = 0 ; i < N ; i++) {
			int num = Integer.parseInt(st.nextToken());
			if(num<0) {
				minus.add(-1*num);
			} else {
				plus.add(num);
			}
		}
		minus.sort(null);
		plus.sort(null);
		
		int minusEnd = minus.size()-1, plusEnd = plus.size()-1;
		int minusStr = minus.size()%M-1, plusStr = plus.size()%M-1;
		if(plus.isEmpty()) {
			result-=minus.get(minusEnd);
		}
		else if(minus.isEmpty()) {
			result-=plus.get(plusEnd);
		}
		else if(minus.get(minusEnd)>plus.get(plusEnd)) {
			result-=minus.get(minusEnd);
		}
		else {
			result-=plus.get(plusEnd);
		}
		if(minusStr==-1) {
			minusStr+=M;
		}
		if(plusStr==-1) {
			plusStr+=M;
		}
		while(minusStr<minus.size()) {
			result+=2*minus.get(minusStr);
			minusStr+=M;
		}
		while(plusStr<plus.size()) {
			result+=2*plus.get(plusStr);
			plusStr+=M;
		}
		System.out.println(result);
	}

}