package defaultPackage;

public class GreatestCommonDivisor {
	
	private static int getGCD(int a, int b) {
		int result = 0;
		int A = 0;
		while (0 == result) {
			A = a % b;
			a = b;
			b = A;
			if(0 == b) {
				result = a;
			}
		}
		return result;
	}
	
	private static int getLCM(int a, int b) {
		return (int)((long) a * b / getGCD(a, b));
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(getGCD(28851538, 1183019));
		System.out.println(getLCM(28851538, 1183019));
	}

}
