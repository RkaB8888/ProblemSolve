import java.io.*;

public class Main {
    static int N;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        if((N&1)==1) {
            System.out.print("Goose");
        } else {
            System.out.print("Duck");
        }
    }
}