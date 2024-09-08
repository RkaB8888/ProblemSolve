import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * 메모리:29,328KB, 시간:237ms 모든 사람이 계단을 선택하는 조홥
 */
public class Solution {
	static int N, map[][], personCnt, curtime, result;
	static Stair stairs[];
	static Person persons[];

	static class Person {
		int x, y, time[] = new int[2], downTime;

		public Person(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static class Stair {
		int x, y, len, tnum, onum;
		List<Person> waitArr = new ArrayList<>();
		Queue<Person> waitQ = new ArrayDeque<>();
		Queue<Person> downQ = new ArrayBlockingQueue<>(3);

		public Stair(int x, int y, int len, int num) {
			this.x = x;
			this.y = y;
			this.len = len;
			this.tnum = num;
			this.onum = num ^ 1;
		}

		public void waitSort() {
			waitArr.sort((a, b) -> {
				if (a.time[tnum] == b.time[tnum]) {
					return b.time[onum] - a.time[onum];
				}
				return a.time[tnum] - b.time[tnum];
			});
			waitQ.clear();
			downQ.clear();
			for (Person p : waitArr) {
				waitQ.add(p);
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			persons = new Person[N * N];
			stairs = new Stair[2];
			personCnt = 0;
			int stairCnt = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					int num = Integer.parseInt(st.nextToken());
					if (num == 0)
						continue;
					else if (num == 1) {
						persons[personCnt++] = new Person(i, j);
					} else {
						stairs[stairCnt] = new Stair(i, j, num, stairCnt);
						stairCnt++;
					}
				}
			}
			persons = Arrays.copyOf(persons, personCnt);
			for (int i = 0; i < personCnt; i++) {
				// 0번 계단 시간
				persons[i].time[0] = Math.abs(stairs[0].x - persons[i].x) + Math.abs(stairs[0].y - persons[i].y);
				// 1번 계단 시간
				persons[i].time[1] = Math.abs(stairs[1].x - persons[i].x) + Math.abs(stairs[1].y - persons[i].y);
			}

			int bitmaskMax = 1 << personCnt;
			int next = 0;
			result = Integer.MAX_VALUE;
			while (next < bitmaskMax) {
				process(next);
				next++;
			}
//			System.out.println("//////////////");
			sb.append('#').append(tc).append(' ').append(result).append('\n');
		}
		System.out.println(sb);
	}

	static public void process(int bit) {
		stairs[0].waitArr.clear();
		stairs[1].waitArr.clear();
		for (int i = 0; i < personCnt; i++) {// 사람들 계단 정하기
			stairs[(bit >> i) & 1].waitArr.add(persons[i]);
		}
		stairs[0].waitSort();
		stairs[1].waitSort();
		curtime = 0;
		boolean flag1 = false, flag2 = false, flag3 = false, flag4 = false;
		while (true) {
			curtime++;
			if(curtime>result) break;
			flag1 = false;
			flag2 = false;
			flag3 = false;
			flag4 = false;
			while (!stairs[0].downQ.isEmpty()) {
				Person p = stairs[0].downQ.peek();
				if (p.downTime > curtime) {
					flag1 = true;
					break;
				}
				stairs[0].downQ.poll();
			}
			while (!stairs[1].downQ.isEmpty()) {
				Person p = stairs[1].downQ.peek();
				if (p.downTime > curtime) {
					flag2 = true;
					break;
				}
				stairs[1].downQ.poll();
			}
			while (!stairs[0].waitQ.isEmpty()) {
				Person p = stairs[0].waitQ.peek();
				if (p.time[0] >= curtime) {
					flag3 = true;
					break;
				}
				if (stairs[0].downQ.offer(p)) {
					p.downTime = curtime + stairs[0].len;
					stairs[0].waitQ.poll();
					flag3 = true;
				} else {
					flag3 = true;
					break;
				}
			}
			while (!stairs[1].waitQ.isEmpty()) {
				Person p = stairs[1].waitQ.peek();
				if (p.time[1] >= curtime) {
					flag4 = true;
					break;
				}
				if (stairs[1].downQ.offer(p)) {
					p.downTime = curtime + stairs[1].len;
					stairs[1].waitQ.poll();
					flag4 = true;
				} else {
					flag4 = true;
					break;
				}
			}
			if (!(flag1 || flag2 || flag3 || flag4)) {
//				System.out.println("bit : "+bit+", curtime : "+curtime);
				result = Math.min(curtime, result);
				break;
			} 
		}
	}
}