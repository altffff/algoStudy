import java.util.Scanner;

public class Main{
    static int TC,Ans,M;
    static int[] snacks;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TC = sc.nextInt();

        for(int test_case=1; test_case<=TC; test_case++){
            Ans = -1;
            int N = sc.nextInt(); //과자봉지수
            M = sc.nextInt(); //무게제한
            snacks = new int[N];

            for(int n=0; n<N; n++){
                snacks[n]=sc.nextInt();
            }
            //조합두개고르기. m그램 가지치기.
            for(int i=0; i<snacks.length-1; i++){
                solve(i);
            }
            System.out.println("#"+test_case+" "+Ans);
        }
    }

    private static void solve(int i) {
        int snack=snacks[i];
        if(M <= snack) return;
        for(int j=i+1; j<snacks.length; j++){
            int sum = snack+snacks[j];
            if(sum <= M) Ans = Ans <= sum? sum:Ans;
        }
    }
}