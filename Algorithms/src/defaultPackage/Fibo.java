package defaultPackage;

import java.util.Scanner;

public class Fibo {

	public static long getFib(int n) {
		long[] fib_n = new long[2];
		long fib = 1;
		fib_n[0] = 0;
		fib_n[1] = 1;
		if (n < 2) {
			return fib_n[n];
		}
		for (int i = 2; i <= n; i++) {
			fib = fib_n[0] + fib_n[1];
			fib_n[0] = fib_n[1];
			fib_n[1] = fib;
		}
		return fib;
	}

	public static int getPisano(int m) {
		int pisano = 0, flag = 0;
		int fib_2 = 0, fib_1 = 1, fib = 1;
		while (flag < 2) {
			fib = (fib_2 + fib_1) % m;
			fib_2 = fib_1;
			fib_1 = fib;
			if (fib == 0) {
				flag++;
			} else if (fib == 1 && flag == 1) {
				flag++;
			} else {
				flag = 0;
			}
			pisano++;
		}
		return pisano;
	}

	public static int FibLastDigit(long n) {
		int m = (int) n % 60;
		int[] fib = new int[m+1];
		fib[0] = 0;
		fib[1] = 1;
		int sum = 1;
		if (n < 2) {
			return fib[m];
		}
		for (int i = 2; i <= m; i++) {
			fib[i] = (fib[i-2] + fib[i-1]) % 10;
			sum = (sum + fib[i]) % 10;
		}
		return sum;
	}

	public static long FibModu(long n, int m) {
		long pisano = getPisano(m);
		long[] fib = new long[3];
		fib[0] = 0;
		fib[1] = 1;
		long f = n % pisano;
		if (f < 2) {
			return fib[(int) f];
		}
		for (int i = 2; i <= f; i++) {
			fib[2] = (fib[0] + fib[1]) % m;
			fib[0] = fib[1];
			fib[1] = fib[2];
		}
		return fib[2];
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println(FibLastDigit(sc.nextLong()));
		sc.close();
	}

}
