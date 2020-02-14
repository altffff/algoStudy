import java.util.Arrays;
import java.util.Scanner;

public class Main{
	static int[] rotate;
	static int[][] arr;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		arr = new int[5][8]; // 톱니바퀴정보
		rotate = new int[5]; // 회전할지말지

		for (int i = 1; i <=4; i++) {
			String s = sc.nextLine();
			for (int j = 0; j < 8; j++) {
				arr[i][j] = s.charAt(j)-48;
			}
		}

		int K = sc.nextInt();
	//	System.out.println("초기모양");
	//	for(int[] a :arr )System.out.println(Arrays.toString(a));
	//	System.out.print("회전"+Arrays.toString(rotate));
	//	System.out.println();
		for (int k = 0; k < K; k++) {
			int num = sc.nextInt();
			rotate[num] = sc.nextInt(); // 1or-1;
		//	System.out.println("넘"+num);
			switch (num) {
			case 1:
				chk(2, arr, rotate);
				if (rotate[2] != 0) {
					chk(3, arr, rotate);
					if (rotate[3] != 0)
						chk(4, arr, rotate);
				}
				break;
			case 2:
				chk(1, arr, rotate);
				chk(3, arr, rotate);
				if (rotate[3] != 0)
					chk(4, arr, rotate);
				break;
			case 3:
				chk(2, arr, rotate);
				if (rotate[2] != 0)
			//		System.out.println("2번은회전함");
					chk(1, arr, rotate);
				chk(4, arr, rotate);
				break;
			case 4:
				chk(3, arr, rotate);
				if (rotate[3] != 0) {
					chk(2, arr, rotate);
					if (rotate[2] != 0)
						chk(1, arr, rotate);
				}
				break;
			}
		//	System.out.print("회전내역"+Arrays.toString(rotate));
			rotation();
			for(int i=0; i<rotate.length; i++){
				rotate[i]=0;
			}
			
		//	for(int[] a :arr )System.out.println(Arrays.toString(a));
		//	System.out.println();
		}
		
		//for(int a :rotate )System.out.print("회전여부"+ a+" ");
		int ans = (arr[1][0]*1) + (arr[2][0]*2) + (arr[3][0]*4) + (arr[4][0]*8);
		System.out.println(ans);
		sc.close();
	}

	private static void rotation() {
		for(int i=1; i<arr.length; i++){
			int[] tmp = new int[8];
			for(int j=0; j<8; j++){
				tmp[j]=arr[i][j];
			}
			if(rotate[i]==1){
				//시계방향회전
				for(int k=0; k<8; k++){
					arr[i][k]=tmp[(k+7)%8];
				}
			}else if (rotate[i]==-1){
				//반시계회전
				for(int k=0; k<8; k++){
					arr[i][k]=tmp[(k+1)%8];
				}
			}
			
		}
		
	}

	private static void chk(int n, int[][] wheel, int[] rotation) {
		switch (n) {
		case 1:
			if (rotate[2] != 0 && arr[1][2]!=arr[2][6]) {
				rotate[1] = -rotate[2]; //반대방향으로 회전을 뜻함
			}
			break;
		case 2:
			if ((rotate[1] != 0 && arr[1][2] != arr[2][6])
					|| (rotate[3] != 0 && arr[3][6] != arr[2][2])) {
			//	System.out.println("2번인접극"+arr[2][2]);
			//	System.out.println("3번인접극"+arr[3][6]);
				rotate[2]= rotate[1]!=0? -rotate[1]: -rotate[3];
			}
			break;
		case 3:
			if ((rotate[2] != 0 && arr[2][2] != arr[3][6])
					|| (rotate[4] != 0 && arr[4][6] != arr[3][2])) {
				rotate[3]= rotate[2]!=0? -rotate[2]: -rotate[4];
			}

			break;
		case 4:
			if (rotate[3] != 0 && arr[3][2]!=arr[4][6]) {
				rotate[4] = -rotate[3]; //반대방향으로 회전을 뜻함
			}
			break;

		}

	}

}
