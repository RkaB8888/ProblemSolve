
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int deskNum = Integer.parseInt(st.nextToken());
		long pNum = Long.parseLong(st.nextToken());

		List<Long> timeList = new ArrayList<>();
		for (int i = 0; i < deskNum; i++) {
			timeList.add(Long.parseLong(br.readLine()));
		}
		timeList.sort((a, b) -> Long.compare(a, b));

		long maxTime = timeList.get(deskNum - 1) * pNum;
		long minTime = timeList.get(0);
		long result = maxTime;

		while (minTime <= maxTime) {
			long midTime = (maxTime + minTime) / 2;
			long cnt = 0;
			boolean flag = false;
			for (int i = 0; i < deskNum; i++) {
				cnt += midTime / timeList.get(i);
				if (cnt >= pNum) {
					flag = true;
					break;
				}
			}
			if (flag) {
				maxTime = midTime - 1;
				result = midTime;
			} else {
				minTime = midTime + 1;

			}
			
		}

		System.out.println(result);

	}

}
