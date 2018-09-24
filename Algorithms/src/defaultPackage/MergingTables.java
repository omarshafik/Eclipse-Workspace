package defaultPackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MergingTables {

	public class DataBase {

		public class Table {

			private int size;

			public int size() {
				return size;
			}

			public void size(int size) {
				this.size = size;
				if (size > maxSize) {
					maxSize = size;
				}
			}

			private int link;

			public void link(int link) {
				if (this.link != link) {
					this.link = link;
				}
			}

			public Table(int size, int link) {
				this.size = size;
				if (size > maxSize) {
					maxSize = size;
				}
				this.link = link;
				totalSize += size;
			}

		}

		int totalSize;
		int maxSize;
		Table[] table;

		DataBase(int size) {
			table = new Table[size];
		}

		public void merge(int destination, int source) {
			if (totalSize == maxSize) {
				return;
			}
			destination--;
			source--;
			int dest = destination, src = source;
			while (table[src].link != src) {
				src = table[src].link;
			}
			while (table[dest].link != dest) {
				dest = table[dest].link;
			}
			if (dest != src) {
				table[dest].size(table[dest].size + table[src].size);
				table[src].size(0);
				int temp;
				while (table[source].link != dest) {
					temp = table[source].link;
					table[source].link(dest);
					source = temp;
				}
				while (table[destination].link != dest) {
					temp = table[destination].link;
					table[destination].link(dest);
					destination = temp;
				}
			}
		}

	}

	public static void merge(DataBase db, int[][] query) {
		for (int i = 0; i < query.length; i++) {
			db.merge(query[i][0], query[i][1]);
			System.out.println(db.maxSize);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FastScanner fs = new FastScanner(System.in);

		int dbSize = fs.nextInt();
		int qNu = fs.nextInt();
		int[][] query = new int[qNu][2];
		MergingTables mt = new MergingTables();
		DataBase db = mt.new DataBase(dbSize);

		for (int i = 0; i < dbSize; i++) {
			db.table[i] = db.new Table(fs.nextInt(), i);
		}

		for (int i = 0; i < qNu; i++) {
			query[i][0] = fs.nextInt();
			query[i][1] = fs.nextInt();
		}

		merge(db, query);

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
