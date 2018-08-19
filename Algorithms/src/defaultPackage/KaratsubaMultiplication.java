package defaultPackage;

import java.math.BigInteger;

public class KaratsubaMultiplication {

	public static long mul(long n1, long n2) {
		// base level(each number is 4digits at max)
		long l1 = (long) (Math.log10(n1) + 1), l2 = (long) (Math.log10(n2) + 1);
		if (l1 <= 4 && l2 <= 4) {
			long a = (long) (n1 / 100);
			long b = n1 - a * 100;
			long c = (long) (n2 / 100);
			long d = n2 - c * 100;
			return a * c * 10000 + b * d + ((a + b) * (c + d) - a * c - b * d) * 100;
		} else {
			long a = (long) (n1 / (Math.pow(10, (int) l1 / 2 + l1 % 2)));
			long b = n1 - a * (long) (Math.pow(10, (int) l1 / 2 + l1 % 2));
			long c = (long) (n2 / (Math.pow(10, (int) l2 / 2 + l2 % 2)));
			long d = (long) (n2 - c * (Math.pow(10, (int) l2 / 2 + l2 % 2)));
			long ac = mul(a, c);
			long bd = mul(b, d);
			long e = mul((a + b), (c + d));
			return (long) (ac * (Math.pow(10, (Math.log10(b) + 1) + (Math.log10(b) + 1))) + bd
					+ (e - ac - bd) * (Math.pow(10, (l1 / 2 + l2 / 2) / 2)));
		}
	}

	public static String bigMul(String n1, String n2) {
		int l1 = n1.length();
		int l2 = n2.length();
		if (l1 < 5 && l2 < 5) {
			return Integer.toString((int) mul(Integer.parseInt(n1), Integer.parseInt(n2)));
		} else {
			int lx = ((l1 > l2) ? l2/2 + l2%2 : l1/2 + l1%2);
			int ly = ((l1 > l2) ? l2 - lx : l1 - lx);
			char[] na = new char[l1-ly];
			char[] nb = new char[ly];
			char[] nc = new char[l2-ly];
			char[] nd = new char[ly];
			n1.getChars(0, l1-ly, na, 0);
			n1.getChars(l1-ly, l1, nb, 0);
			n2.getChars(0, l2-ly, nc, 0);
			n2.getChars(l2-ly, l2, nd, 0);
			BigInteger a = new BigInteger(new String(na));
			BigInteger b = new BigInteger(new String(nb));
			BigInteger c = new BigInteger(new String(nc));
			BigInteger d = new BigInteger(new String(nd));
			BigInteger e = new BigInteger(bigMul((a.add(b)).toString(), (c.add(d)).toString()));
			BigInteger ac = new BigInteger(bigMul(new String(na), new String(nc)));
			BigInteger bd = new BigInteger(bigMul(new String(nb), new String(nd)));
			e = e.subtract(ac).subtract(bd);
			for (int i = 0; i < ly; i++) {
				e = new BigInteger(e.toString() + "0");
			}
			for (int i = 0; i < nb.length + nd.length; i++) {
				ac = new BigInteger(ac.toString() + "0");
			}
			return e.add(ac).add(bd).toString();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// long result = mul(457, 457);
		// System.out.println(mul(85487549, 77895265));
		System.out.println(bigMul("2718281828459045235360287471352662497757247093699959574966967627", "3141592653589793238462643383279502884197169399375105820974944592"));

	}

}
