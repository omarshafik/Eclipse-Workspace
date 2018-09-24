package defaultPackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.StringTokenizer;

public class MajorityElement {

	public static int getMajElement(int[] set, int low, int high) {
		if ((high - low) < 3) {
			int element = -1;
			for (int i = low; i < high; i++) {
				if (set[i] == set[high]) {
					element = set[i];
					i = high + 1;
				}
			}
			return element;
		} else {
			int element;
			int middle = ((high - low) / 2) + low;
			int a = getMajElement(set, middle + 1, high);
			int b = getMajElement(set, low, middle);
			if (a == b) {
				return a;
			} else if (a == -1) {
				return b;
			} else if (b == -1) {
				return a;
			} else if (a != -1 && b != -1) {
				int counterA = 0, counterB = 0;
				for (int i = 0; i < set.length; i++) {
					if (set[i] == a) {
						counterA++;
					} else if (set[i] == b) {
						counterB++;
					}
				}
				if (counterA > counterB) {
					element = a;
				} else {
					element = b;
				}
				return element;
			} else {
				return -1;
			}
		}
	}

	public static void stressTest() {
		Random rand = new Random();
		int n = rand.nextInt(15);
		int[] set = new int[n];
		for (int i = 0; i < n; i++) {
			set[i] = rand.nextInt(3);
		}
		int element = getMajElement(set, 0, n - 1);
		System.out.println(element);
	}

	public static void main(String[] args) {
		// // TODO Auto-generated method stub
		FastScanner sc = new FastScanner(System.in);
		int n = sc.nextInt();
		int[] voter = new int[n];
		for (int i = 0; i < n; i++) {
			voter[i] = sc.nextInt();
		}
		int low = 0;
		int high = n - 1;
		int element = getMajElement(voter, low, high);
		if (element >= 0) {
			int counter = 0;
			for (int i = 0; i < n; i++) {
				if (voter[i] == element) {
					counter++;
				}
			}
			if (counter > n / 2) {
				System.out.println("1");
			} else {
				System.out.println("0");
			}
		} else {
			System.out.println("0");
		}
		// while (true) {
		// stressTest();
		// }

	}
	
	   static class FastScanner {
	        BufferedReader br;
	        StringTokenizer st;

	        FastScanner(InputStream stream) {
	            try {
	                br = new BufferedReader(new InputStreamReader(stream));
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }

	        String next() {
	            while (st == null || !st.hasMoreTokens()) {
	                try {
	                    st = new StringTokenizer(br.readLine());
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	            return st.nextToken();
	        }

	        int nextInt() {
	            return Integer.parseInt(next());
	        }
	    }

}
