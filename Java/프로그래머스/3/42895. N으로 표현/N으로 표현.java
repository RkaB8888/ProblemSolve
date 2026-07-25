import java.util.*;
import java.io.*;
// N은 최대 8번까지 사용 가능
// 1번으로 가능한 수, 2번으로 가능한 수 ... 를 DP로 접근한다?
// 1~32000 모든 수가 N을 몇번 사용해서 가능한지 저장한다면?
class Solution {
    public int solution(int N, int number) {
        if(N==number) return 1;
        Set<Integer>[] list = new HashSet[9];
        for(int i = 1, j = N ; i <= 8 ; i++, j=j*10+N) {
            list[i] = new HashSet<Integer>();
            list[i].add(j);
        }
        for(int i = 1 ; i <= 8 ; i++) {
            for(int j = 1 ; j < i ; j++) { // j 연산 i-j의 값을 list[i]에 넣는다.
                for(int a : list[j]) {
                    for(int b : list[i-j]) {
                        list[i].add(a+b);
                        list[i].add(a-b);
                        list[i].add(a*b);
                        if(b!=0) list[i].add(a/b);
                        
                    }
                }
                
            }
            if(list[i].contains(number)) return i;
        }
        return -1;
    }
}