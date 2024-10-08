import java.io.*;
import java.util.*;

public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String A = br.readLine().trim(), B = br.readLine().trim();
        int a = Integer.parseInt(A), b = Integer.parseInt(B);
        int c = Integer.parseInt(br.readLine());
        System.out.println(a+b-c);
        int AB = Integer.parseInt(A+B);
        System.out.print(AB-c);
    }
}