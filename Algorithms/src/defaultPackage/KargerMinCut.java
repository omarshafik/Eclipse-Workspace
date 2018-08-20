package defaultPackage;

import java.util.Random;
import java.util.Scanner;

public class KargerMinCut {

	public static int[][] fillMatrix() {
		int[][] adjMatrix = new int[201][201];
		Scanner s = new Scanner(System.in);
		int entry;
		char[] line;
		int j = 0;
		String nodeN = "";
		for (int i = 1; i < 200; i++) {
			s.nextInt();
			line = s.nextLine().toCharArray();
			j = 1;
			while (j < line.length) {
				while (line[j] != '\t') {
					nodeN += line[j];
					j++;
				}
				entry = Integer.parseInt(nodeN);
				nodeN = "";
				adjMatrix[i][entry] = 1;
				adjMatrix[entry][i] = 1;
				adjMatrix[0][0]++;
				j++;
			}
		}
		s.close();
		return adjMatrix;
	}

	public static int getMinCut(int[][] matr) {
		int[][] adjMatrix = new int[201][201];
		adjMatrix[0][0] = matr[0][0];
		for (int i = 1; i <= 200; i++) {
			for (int j = i + 1; j <= 200; j++) {
				adjMatrix[i][j] = matr[i][j];
				adjMatrix[j][i] = matr[i][j];
			}
		}
		Random random = new Random();
		int va = 0, vb = 1;
		for (int j = 0; j < 198; j++) {
			va = 0;
			vb = 1;
			while (adjMatrix[va][vb] == 0) {
				va = random.nextInt(200) + 1;
				vb = va;
				while (vb == va) {
					vb = random.nextInt(200) + 1;
				}
			}
			adjMatrix[va][vb] = 0;
			adjMatrix[vb][va] = 0;
			for (int i = 1; i <= 200; i++) {
				if (adjMatrix[vb][i] >= 1) {
					adjMatrix[va][i] += adjMatrix[vb][i];
					adjMatrix[i][va] += adjMatrix[vb][i];
					adjMatrix[i][vb] = 0;
					adjMatrix[vb][i] = 0;
				}
			}
		}
		int count = 0;
		for (int i = 1; i <= 200; i++) {
			for (int j = i + 1; j <= 200; j++) {
				if (adjMatrix[i][j] >= 1) {
					count += adjMatrix[i][j];
				}
			}
		}

		return count;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[][] adjMatrix = fillMatrix();
		int min;
		int result = getMinCut(adjMatrix);
		min = result;
		for (int i = 0; i < 200 * 200; i++) {
			result = getMinCut(adjMatrix);
			if (result < min) {
				min = result;
			}
		}
		System.out.println(min);

	}

}
