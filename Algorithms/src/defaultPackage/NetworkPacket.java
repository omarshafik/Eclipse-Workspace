package defaultPackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class NetworkPacket {

	public static void process(int[][] packet, int bufferSize) {
		int[] buffer = new int[packet.length];
		int[] arrival = new int[packet.length];
		int front = 0, back = 0;
		for (int time = 0; front < packet.length; time++) {
			if(back-front > bufferSize) {
				if(packet[back][0] >= time) {
					buffer[back] = 
				}
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FastScanner fs = new FastScanner(System.in);
		int bufferSize = fs.nextInt();
		int n = fs.nextInt();
		int[][] packet = new int[n][2];

		for (int i = 0; i < n; i++) {
			packet[i][0] = fs.nextInt();
			packet[i][1] = fs.nextInt();
		}

		process(packet, bufferSize);

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
