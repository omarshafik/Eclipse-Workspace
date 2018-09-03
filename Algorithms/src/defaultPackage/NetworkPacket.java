package defaultPackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class NetworkPacket {

	public static void process(int[][] packet, int bufferSize) {
		int[] buffer = new int[bufferSize];
		int[] arrival = new int[packet.length];
		if (packet.length > 0) {
			int processCount = 0, i = 0;
			int front = 0, back = 0, currentSize = 0;
			int finish_time = 0;
			for (int time = 0; processCount < packet.length; time++) {
				if (finish_time == time && currentSize > 0) {
					currentSize--;
					processCount++;
				}
				while ((i < packet.length && packet[i][0] <= time && currentSize < bufferSize)
						|| (time >= finish_time && currentSize > 0)) {
					if (i < packet.length && packet[i][0] <= time && currentSize < bufferSize) {
						buffer[back] = i;
						i++;
						back++;
						currentSize++;
						if (back == bufferSize) {
							back = 0;
						}
					}
					if (time >= finish_time && currentSize > 0) {
						arrival[buffer[front]] = time;
						finish_time = time + packet[buffer[front]][1];
						if (finish_time == time && currentSize > 0) {
							currentSize--;
							processCount++;
						}
						front++;
						if (front == bufferSize) {
							front = 0;
						}
					}
				}

				while (i < packet.length && packet[i][0] <= time) {
					arrival[i] = -1;
					i++;
					processCount++;
				}
			}
			for (int j = 0; j < arrival.length; j++) {
				System.out.println(arrival[j]);
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
