package defaultPackage;

import java.util.Scanner;

public class MoneyChange {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		int input = scanner.nextInt();
		scanner.close();
		int coinsNu = 0;
		int remainder = input;
		if (input >= 10) {
			remainder = remainder % 10;
			coinsNu += (input - remainder) / 10;
			input = remainder;
		}
		if (input >= 5) {
			remainder = remainder % 5;
			coinsNu += (input - remainder) / 5;
			input = remainder;
		}
		coinsNu += remainder;
		System.out.println(coinsNu);
	}

}
