package defaultPackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BSUsession {

	public static int[] formatArray(int[] arr) {
		int j = 0;
		int current = arr[0];
		int temp;
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] != current) {
				j++;
				temp = arr[j];
				arr[j] = arr[i];
				arr[i] = temp;
				current = arr[j];
			}
		}
		int[] newArr = new int[j + 1];
		for (int i = 0; i <= j; i++) {
			newArr[i] = arr[i];
		}
		return newArr;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FastScanner fs = new FastScanner(System.in);
		int n = fs.nextInt();
		int[] time = new int[n * 2];
		int[][] exam = new int[n][2];
		for (int i = 0, j = 0; i < n; i++, j += 2) {
			time[j] = fs.nextInt();
			time[j + 1] = fs.nextInt();
			exam[i][0] = time[j];
			exam[i][1] = time[j + 1];
		}
		Arrays.sort(time);
		int[] timeList = formatArray(time);
		int[][] list = new int[n + 1][timeList.length + 1];

		for (int j = 1; j <= n; j++) {
			for (int i = 1; i <= timeList.length; i++) {
				list[j][i] = list[j-1][i];
				if(timeList[i-1]==exam[j-1][0] || timeList[i-1]==exam[j-1][1]) {
					list[j][i] +=1;
				}
			}
		}
		int count = 0;
		int i;
		for(i=1;i<=timeList.length;i++) {
			if(list[n][i] >= 2) {
				count++;
			} else if(list[n][i] == 1) {
				if(list[n-1][i] == 1) {
					count++;
				} else {
					
				}
			}
		}
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
