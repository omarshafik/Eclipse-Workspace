package defaultPackage;

import java.util.Scanner;

public class Brackets {
	
	public static void check(char[] line) {
		int[] stack = new int[line.length];
		int i = -1;
		int j = -1;
		char match = '0';
		boolean fail = false;
		for (char c : line) {
			j++;
			if (c == '[' || c == '{' || c == '(' || c == '<') {
				i++;
				stack[i] = j;
				match = ((line[stack[i]] == '[') ? ']' : ((line[stack[i]] == '{') ? '}' : ((line[stack[i]] == '<') ? '>' : ')')));
			} else if (c == ']' || c == '}' || c == ')' || c == '>') {
				if (c == match) {
					i--;
					if (i != -1)
						match = ((line[stack[i]] == '[') ? ']' : ((line[stack[i]] == '{') ? '}' : ((line[stack[i]] == '<') ? '>' : ')')));
					else
						match = '0';
				} else {
					fail = true;
					i = line.length;
					break;
				}
			}
		}
		if (i == -1) {
			System.out.println("YES");
		} else {
			System.out.println("NO");
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		for(int i = 0; i < n; i++) {
			check(sc.next().toCharArray());
		}
		sc.close();
	}

}
