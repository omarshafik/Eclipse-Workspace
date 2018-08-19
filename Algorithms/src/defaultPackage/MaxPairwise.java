package defaultPackage;

import java.util.Scanner;

public class MaxPairwise {

	private static void getMaxPairwiseProduct() {
		System.out.println("Enter data");
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int[] data = new int[n];
		int max = 0;
		int secMax = 0;
		for (int i = 0; i < n; i++) {
			data[i] = scan.nextInt();
			if (data[i] > max) {
				secMax = max;
				max = data[i];
			}
		}
		scan.close();
		System.out.println(max * secMax);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		getMaxPairwiseProduct();
	}

}
