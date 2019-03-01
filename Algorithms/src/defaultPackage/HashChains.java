package defaultPackage;

import java.io.*;
import java.util.StringTokenizer;
import java.util.Vector;

public class HashChains {

	private FastScanner in;
	private PrintWriter out;
	// store all strings in one list
	private Vector<Vector<String>> elems;
	// for hash function
	private int bucketCount;
	private int prime = 1000000007;
	private int multiplier = 263;

	public static void main(String[] args) throws IOException {
		new HashChains().processQueries();
	}

	private int hashFunc(String s) {
		long hash = 0;
		for (int i = s.length() - 1; i >= 0; --i)
			hash = (((hash * multiplier + s.charAt(i)) % prime) + prime) % prime;
		return (int) hash % bucketCount;
	}

	private Query readQuery() throws IOException {
		String type = in.next();
		if (!type.equals("check")) {
			String s = in.next();
			return new Query(type, s);
		} else {
			int ind = in.nextInt();
			return new Query(type, ind);
		}
	}

	private void processQuery(Query query) {
		switch (query.type) {
		case "add":
			boolean found = false;
			for (int i = 0; i < elems.elementAt(hashFunc(query.s)).size(); i++) {
				if (elems.elementAt(hashFunc(query.s)).elementAt(i).equals(query.s)) {
					found = true;
				}
			}
			if (!found) {
				elems.elementAt(hashFunc(query.s)).add(query.s);
			}
			break;
		case "del":
			for (int i = 0; i < elems.elementAt(hashFunc(query.s)).size(); i++) {
				if (elems.elementAt(hashFunc(query.s)).elementAt(i).equals(query.s)) {
					elems.elementAt(hashFunc(query.s)).remove(i);
				}
			}
			break;
		case "find":
			found = false;
			for (int i = 0; i < elems.elementAt(hashFunc(query.s)).size(); i++) {
				if (elems.elementAt(hashFunc(query.s)).elementAt(i).equals(query.s)) {
					System.out.println("yes");
					found = true;
				}
			}
			if (!found) {
				System.out.println("no");
			}
			break;
		case "check":
			if (!elems.elementAt(query.ind).isEmpty()) {
				for (int i = elems.elementAt(query.ind).size() - 1; i >= 0; i--) {
					System.out.print(elems.elementAt(query.ind).elementAt(i) + " ");
				}
				System.out.println();
			} else
				System.out.println();
			break;
		default:
			throw new RuntimeException("Unknown query: " + query.type);
		}
	}

	public void processQueries() throws IOException {
		in = new FastScanner();
		out = new PrintWriter(new BufferedOutputStream(System.out));
		bucketCount = in.nextInt();
		elems = new Vector<Vector<String>>(bucketCount);
		for (int i = 0; i < bucketCount; i++) {
			elems.add(new Vector<String>());
		}
		int queryCount = in.nextInt();
		for (int i = 0; i < queryCount; ++i) {
			processQuery(readQuery());
		}
		out.close();
	}

	static class Query {
		String type;
		String s;
		int ind;

		public Query(String type, String s) {
			this.type = type;
			this.s = s;
		}

		public Query(String type, int ind) {
			this.type = type;
			this.ind = ind;
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