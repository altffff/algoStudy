import java.util.ArrayList;
import java.util.Scanner;

public class Main {
static int N,ANS_MIN,ANS_MAX,plus,minus,div,mul;
static int[] num; 
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N=sc.nextInt();
		num = new int[N];
		ANS_MIN= 1000000000;
		ANS_MAX=-1000000000;
		
		for(int i=0; i<N; i++){
		num[i]=sc.nextInt();
		}
		
		plus = sc.nextInt();
		minus = sc.nextInt();
		mul = sc.nextInt();
		div = sc.nextInt();
		
		solve(1,plus,minus,mul,div,num[0]); // (num[] 인덱스, +,-,*,/ 사용가능한 남은갯수, 현재까지계산된값)
		
		System.out.println(ANS_MAX);
		System.out.println(ANS_MIN);
	}
	
	private static void solve(int idx, int pl, int mi, int mu, int div, int result) {
		//주어진갯수의 사칙연산이 다 사용되었다면
		if(pl==0 && mi==0 && mu==0 && div==0) {
			//범위를 넘겼을경우 가지치기 한번 하기
			if(result < -1000000000 || 1000000000< result) return;
			//최솟값 최댓값 갱신
			ANS_MIN=result<ANS_MIN?result:ANS_MIN;
			ANS_MAX=result>ANS_MAX?result:ANS_MAX;
			return;
		}
		int tmp = num[idx];
		if(pl>0)  solve(idx+1,pl-1,mi  ,mu  ,div  ,result+tmp);
		if(mi>0)  solve(idx+1,pl  ,mi-1,mu  ,div  ,result-tmp);
		if(mu>0)  solve(idx+1,pl  ,mi  ,mu-1,div  ,result*tmp);
		if(div>0) solve(idx+1,pl  ,mi  ,mu  ,div-1,result/tmp);
	}
}