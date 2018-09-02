package defaultPackage;

import java.util.Scanner;

public class Brackets {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);
		char[] line = s.nextLine().toCharArray();
		s.close();
		int[] stack = new int[line.length];
		int i = -1;
		int j = -1;
		char match = '0';
		boolean fail = false;
		for (char c : line) {
			j++;
			if (c == '[' || c == '{' || c == '(') {
				i++;
				stack[i] = j;
				match = ((line[stack[i]] == '[') ? ']' : ((line[stack[i]] == '{') ? '}' : ')'));
			} else if (c == ']' || c == '}' || c == ')') {
				if (c == match) {
					i--;
					if (i != -1)
						match = ((line[stack[i]] == '[') ? ']' : ((line[stack[i]] == '{') ? '}' : ')'));
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
			System.out.println("Success");
		} else if (j == line.length - 1) {
			if (fail == false)
				System.out.println(stack[i] + 1);
			else
				System.out.println(j + 1);
		} else {
			System.out.println(j + 1);
		}

	}

}
