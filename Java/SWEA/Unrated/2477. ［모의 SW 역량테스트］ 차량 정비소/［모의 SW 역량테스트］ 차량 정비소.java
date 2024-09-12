import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
	static int N, M, K, A, B, result;
	static int receptionTime[], repairTime[];
	static PriorityQueue<Person> receptionPQ = new PriorityQueue<Person>((a, b) -> {
		if (a.arriveTime == b.arriveTime)
			return a.num - b.num;
		else
			return a.arriveTime - b.arriveTime;
	});
	static PriorityQueue<Person> repairPQ = new PriorityQueue<>((a, b) -> {
		if (a.receptionEndTime == b.receptionEndTime)
			return a.receptionNum - b.receptionNum;
		else
			return a.receptionEndTime - b.receptionEndTime;
	});
	static Person[] persons, receptionDesk, repairDesk;

	static class Person {
		int num, receptionNum, repairNum;
		int arriveTime;
		int receptionEndTime;
		int repairEndTime;

		public Person(int num, int arriveTime) {
			this.num = num;
			this.arriveTime = arriveTime;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			result = 0;
			receptionTime = new int[N + 1];
			repairTime = new int[M + 1];
			receptionDesk = new Person[N + 1];
			repairDesk = new Person[M + 1];
			persons = new Person[K + 1];

			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				receptionTime[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= M; i++) {
				repairTime[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= K; i++) {
				int arriveTime = Integer.parseInt(st.nextToken());
				persons[i] = new Person(i, arriveTime);
//				for (int j = 1; j <= N; j++) {
//					if (arriveTime==0&&receptionDesk[j] == null) {
//						receptionDesk[j] = persons[i];
//						receptionDesk[j].receptionNum = j;
//						receptionDesk[j].receptionEndTime = receptionTime[j];
//						continue a;
//					}
//				}
				receptionPQ.add(persons[i]);
			}

			timeFlow();

			sb.append('#').append(tc).append(' ').append(result==0?-1:result).append('\n');
		}
		System.out.println(sb);
	}

	public static void timeFlow() {
		int curTime = 0;
		int remainRecept = K, remainRepair = K;
		while (remainRecept > 0 || remainRepair > 0) {
			for (int i = 1; remainRepair > 0 && i <= M; i++) {// 수리가 끝난 사람
				if (repairDesk[i] != null && repairDesk[i].repairEndTime == curTime) {
					remainRepair--;
					if (repairDesk[i].receptionNum == A && repairDesk[i].repairNum == B) {
						result += repairDesk[i].num;
					}
					repairDesk[i] = null;
				}
			}
			for (int i = 1; !repairPQ.isEmpty() && i <= M; i++) {// 수리 받을 사람
				if (repairDesk[i] == null) {
					repairDesk[i] = repairPQ.poll();
					repairDesk[i].repairNum = i;
					repairDesk[i].repairEndTime = curTime + repairTime[i];
				}
			}
			for (int i = 1; remainRecept > 0 && i <= N; i++) {// 접수 끝난 사람
				if (receptionDesk[i] != null && receptionDesk[i].receptionEndTime == curTime) {
					remainRecept--;
					repairPQ.add(receptionDesk[i]);
					receptionDesk[i] = null;
				}
			}
			a: while (!receptionPQ.isEmpty()) {
	            Person p = receptionPQ.peek();
	            if (p.arriveTime <= curTime) {
	                for (int i = 1; i <= N; i++) {
	                    if (receptionDesk[i] == null) {
	                        receptionDesk[i] = receptionPQ.poll();
	                        receptionDesk[i].receptionNum = i;
	                        receptionDesk[i].receptionEndTime = curTime + receptionTime[i];
	                        continue a;
	                    }
	                }
	                break;
	            } else {
	                break;
	            }
	        }
			
			
			curTime++;
		}
	}
}