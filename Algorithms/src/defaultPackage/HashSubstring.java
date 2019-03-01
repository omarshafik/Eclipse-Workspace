package defaultPackage;

import java.io.*;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.Random;

public class HashSubstring {

	private static FastScanner in;
	private static PrintWriter out;
	private static int p = 1000003;
	private static int x = new Random().nextInt(p);

	public static void main(String[] args) throws IOException {
		in = new FastScanner();
		out = new PrintWriter(new BufferedOutputStream(System.out));
		printOccurrences(getOccurrences(readInput()));
		out.close();
	}

	private static Data readInput() throws IOException {
		String pattern = in.next();
		String text = in.next();

		return new Data(pattern, text);
	}

	private static void printOccurrences(Vector<Integer> ans) throws IOException {
		for (Integer cur : ans) {
			out.print(cur);
			out.print(" ");
		}
	}

	private static int polyHash(String s) {
		long hash = 0;
		for (int i = s.length() - 1; i >= 0; --i)
			hash = ((((((hash * x % p) + p) % p) + s.charAt(i))	 % p) + p) % p;
		return (int) (hash);
	}

	private static long[] precomputeHashes(Data input) {
		long[] H = new long[input.text.length() - input.pattern.length() + 1];
		String s = input.text.substring(input.text.length() - input.pattern.length());
		H[H.length - 1] = polyHash(s);
		long y = 1;
		for (int i = 1; i <= input.pattern.length(); i++) {
			y = (((y * x) % p) + p) % p;
		}
		for (int i = H.length - 2; i >= 0; i--) {
			long a = (((x * H[i + 1]) % p) + p) % p;
			long b = ((input.text.charAt(i) % p) + p) % p;
			long c = (((y * input.text.charAt(i + input.pattern.length())) % p) + p) % p;
			long d = (((b - c) % p) + p) % p;
			H[i] = (((a + d) % p) + p) % p;
		}
		return H;
	}

	private static Vector<Integer> getOccurrences(Data input) {
		int pHash = polyHash(input.pattern);
		Vector<Integer> result = new Vector<Integer>();
		long[] H = precomputeHashes(input);
		for (int i = 0; i < H.length; i++) {
			if (pHash != H[i])
				continue;
			if (input.text.substring(i, i + input.pattern.length()).equals(input.pattern)) {
				result.addElement(i);
			}
		}

		return result;
	}

	static class Data {
		String pattern;
		String text;

		public Data(String pattern, String text) {
			this.pattern = pattern;
			this.text = text;
		}
	}

	static class FastScanner {
		private BufferedReader reader;
		private StringTokenizer tokenizer;

		public FastScanner() {
			reader = new BufferedReader(new InputStreamReader(System.in));
			tokenizer = null;
		}

		public String next() throws IOException {
			while (tokenizer == null || !tokenizer.hasMoreTokens()) {
				tokenizer = new StringTokenizer(reader.readLine());
			}
			return tokenizer.nextToken();
		}

		public int nextInt() throws IOException {
			return Integer.parseInt(next());
		}
	}
}
