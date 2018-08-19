package defaultPackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BinarySearch {

	public static int bSearch(int[] array, int element) {
		int pos = -1;
		int low = 0, high = array.length - 1, middle;
		while (low < high) {
			middle = ((high - low) / 2) + low;
			if (array[middle] == element) {
				low = middle;
				high = middle;
			} else if (array[middle] < element) {
				low = middle + 1;
			} else if (array[middle] > element) {
				high = middle - 1;
			}
		}
		if (array[low] == element) {
			pos = low;
		}
		return pos;
	}

	public static int bSearchRec(int[] array, int element, int low, int high) {
		if(high >= low) {
			int middle = ((high - low) / 2) + low;
			if(array[middle] == element) {
				return middle;
			}
			else if(array[middle] < element) {
				return bSearchRec(array, element, middle + 1, high);
			}
			return bSearchRec(array, element, low, middle - 1);
		}
		return -1;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FastScanner sc = new FastScanner(System.in);
		int n = sc.nextInt(), pos;
		StringBuilder sb = new StringBuilder();
		int[] array = new int[n];
		for (int i = 0; i < n; i++) {
			array[i] = sc.nextInt();
		}
		int i = sc.nextInt();
		int[] element = new int[i];
		for (int j = 0; j < i; j++) {
			element[j] = sc.nextInt();
		}
		for (int j = 0; j < i; j++) {
			pos = bSearchRec(array, element[j], 0, n-1);
			sb.append(pos).append(" ");
		}
		System.out.println(sb);
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