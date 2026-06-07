import java.io.*;
import java.util.*;

/**
 * @description 스위핑
 */
 
// lines는 최대 2000개의 문자열로 구성
// 문자열은 2016-09-15 hh:mm:ss.sss 형식 (ex. 2016-09-15 03:10:33.020 0.011s)
// 1초 내에 처리 중인 요청의 최대 갯수 구하기

// 요청 시작 오름차순 정렬 배열에서 최대 2000*2000 순회하며 1초 동안 요청 시작 횟수 카운팅
// 하루는 86,400,000ms
// 끝나는 시간을 1초 늘려서 시작 시간만 확인해도 요청 횟수를 파악할 수 있게 만듦
// -> 3초에 끝난다면 4초에 끝나는 것으로 해서 4초에 시작하는 요청의 경우 포함되게끔
// -> -> 1000(ms)를 더하면
// -> 시작 시간도 1초 내에 포함되기 때문에 시작시간(ms) + 1을 해줘야 1초 내에 처리됐는지 확인 가능
// -> 
class Solution {
    // static class Node{
    //     boolean isEnd;
    //     int ms;
    //     public Node(boolean isEnd, int ms){
    //         this.isEnd = isEnd;
    //         this.ms = ms;
    //     }
    // }
    public int solution(String[] lines) {
        List<int[]> list = new ArrayList<>();
        for(String line : lines) {
            int hour = (line.charAt(11)-'0')*10 + (line.charAt(12)-'0');
            int min = (line.charAt(14)-'0')*10 + (line.charAt(15)-'0');
            int sec = (line.charAt(17)-'0')*10 + (line.charAt(18)-'0');
            int ms = (line.charAt(20)-'0')*100 + (line.charAt(21)-'0')*10 + (line.charAt(22)-'0');

            int endms = ms+(sec+(min+hour*60)*60)*1000;
            int spendms = 0;
            int idx = 24;
            int deCnt = 0;
            boolean under = false; // 소수점 이하인지
            while(line.charAt(idx)!='s') {
                if(line.charAt(idx)=='.') {
                    under = true;
                } else {
                    spendms = spendms*10 + line.charAt(idx)-'0';
                    if(under) {
                        deCnt++;
                    }
                }
                idx++;
            }

            for(int i = deCnt; i < 3 ; i++) {
                spendms*=10;
            }

            int startms = endms-spendms+1;
            int vEndms = endms+999;

            list.add(new int[]{0,startms});
            list.add(new int[]{1,vEndms});
        }
        Collections.sort(list,(a,b)->{
            if(a[1]==b[1]) {
                return Integer.compare(a[0],b[0]);
            } return Integer.compare(a[1],b[1]);
        });

        int answer = 0;
        int cnt = 0;
        for(int[] i : list) {
            if(i[0]==0) {
                cnt++;
            } else {
                cnt--;
            }
            answer = Math.max(answer,cnt);
        }
        
        return answer;
    }
}