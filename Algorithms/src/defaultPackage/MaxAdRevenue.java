package defaultPackage;

import java.util.Arrays;
import java.util.Scanner;

public class MaxAdRevenue {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int[] profitPerClick = new int[n];
		int[] avgClicks = new int[n];
		for (int i = 0; i < n; i++) {
			profitPerClick[i] = scanner.nextInt();
		}
		for (int i = 0; i < n; i++) {
			avgClicks[i] = scanner.nextInt();
		}
		scanner.close();
		Arrays.sort(profitPerClick);
		Arrays.sort(avgClicks);
		int maxProducts = 0;
		for (int i = 0; i < n; i++) {
			maxProducts += profitPerClick[i] * avgClicks[i];
		}
		System.out.println(maxProducts);
	}

}
