import java.io.*;
import java.util.*;

/**
 * @author python98
 * @description 이 클래스에 대한 동작 설명
 * @performance 메모리: ? KB, 동작시간: ? ms
 */
/*
1의 자릿수부터 시작해서 처음으로 커지는 자릿수 찾기
해당 자리보다 뒷 자리에서 해당 자릿수보다 작으면서 제일 큰 수와 교환
뒷 자리의 수들을 뒤집어줌
 */
public class Main {
    static int B, C;
    static char[] A;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = st.nextToken().toCharArray();
        B = Integer.parseInt(st.nextToken());
        Arrays.sort(A);
        reverse(A, 0, A.length - 1);
        while (check()) {
            int idx = A.length-1;
            while(idx>0&&A[idx-1]<=A[idx]) idx--;
            if(idx==0) {
                C=-1;
                break;
            }
            idx--;
            int target = A.length-1;
            while(A[idx]<=A[target]) target--;
            swap(idx,target);
            if(A[0]=='0') {
                C=-1;
                break;
            }
            reverse(A,idx+1,A.length-1);
        }
        System.out.print(C);
    }

    private static boolean check() {
        C = 0;
        for (int a : A) {
            C = (C << 3) + (C << 1) + (a & 15);
        }
//        System.out.println(B+", "+C);
        return B <= C;
    }

    private static void swap(int a,int b){
        char temp = A[a];
        A[a] = A[b];
        A[b] = temp;
    }

    private static void reverse(char[] c, int start, int end) {
        for (int s = start, e = end; s < e; s++, e--) {
            char temp = c[s];
            c[s] = c[e];
            c[e] = temp;
        }
    }
}