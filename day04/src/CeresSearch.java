import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class CeresSearch {

	public static void main(String[] args) {
		ArrayList<String> lst = new ArrayList<>();
		try{
			File file = new File("src/input.txt");
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				String data = scanner.nextLine();
				lst.add(data);
			}
			scanner.close();
		} catch (Exception e){
			System.out.println(e.getMessage());
			System.exit(0);
		}

		System.out.println(findXmas(lst) + findXmas(transpose(lst)) + findXmasInDiagonal(lst) + findXmasInDiagonal(
			reverse(lst)));
		part2(lst);

	}

	public static void part2(ArrayList<String> lst){
		int counter = 0;
		for(int i = 0; i < lst.size() - 2; i++){
			for(int j = 0; j < lst.get(i).length() - 2; j++){
				ArrayList<String> arr = new ArrayList<>();
				arr.add(lst.get(i).substring(j, j+3));
				arr.add(lst.get(i+1).substring(j, j+3));
				arr.add(lst.get(i+2).substring(j, j+3));
				counter += part2Solver(arr);
			}
		}
		System.out.println(counter);
	}

	public static int part2Solver(ArrayList<String> s){
		int count = 0;
		boolean firstMas =
			(s.get(0).charAt(0) == 'M' && s.get(1).charAt(1) == 'A' && s.get(2).charAt(2) == 'S')
				|| (s.get(0).charAt(0) == 'S' &&  s.get(1).charAt(1) == 'A' && s.get(2).charAt(2) == 'M');
		boolean secondMas =
			(s.get(0).charAt(2) == 'M' && s.get(1).charAt(1) == 'A' && s.get(2).charAt(0) == 'S')
				|| (s.get(0).charAt(2) == 'S' &&  s.get(1).charAt(1) == 'A' && s.get(2).charAt(0) == 'M');
		if(firstMas && secondMas){
			count++;
		}
		return count;
	}

	public static ArrayList<String> reverse(ArrayList<String> lst){
		for(int i = 0; i < lst.size(); i++){
			String r = new StringBuilder(lst.get(i)).reverse().toString();
			lst.set(lst.indexOf(lst.get(i)), r);
		}
		return lst;
	}

	public static int findXmasInDiagonal(ArrayList<String> lst){
		int count = 0;
		StringBuilder s = new StringBuilder();
		for(int k = 0; k < lst.size(); k++){
			for(int j = 0; j < lst.size(); j++){
				s = new StringBuilder("");
				for(int i = 0; i < 4; i++){
					if(i+j < lst.size() && k + i < lst.size()){
						char c = lst.get(k+i).charAt(i+j);
						s.append(c);
						if(s.length() >= 4){
							break;
						}
					}
				}
				if(s.length() >= 4){
					count += findXmasInLine(s.toString());
				}
			}
		}
		return count;
	}

	public static int findXmasInLine(String s){
		int count = 0;
		for(int j = 0; j < s.length() - 3; j++){
			if(s.substring(j, j+4).equals("XMAS") || s.substring(j,
				j+4).equals("SAMX")){
				count++;
			}
		}
		return count;
	}

	public static int findXmas(ArrayList<String> lst){
		int count = 0;
		for(int i = 0; i < lst.size(); i++){
			for(int j = 0; j < lst.get(i).length() - 3; j++){
				if(lst.get(i).substring(j, j+4).equals("XMAS") || lst.get(i).substring(j, j+4).equals("SAMX")){
					count++;
				}
			}
		}
		return count;
	}

	public static ArrayList<String> transpose(ArrayList<String> arr) {
		ArrayList<String> returnArr = new ArrayList<>();
		char[][] a = new char[arr.size()][arr.get(0).length()];
		char[][] b = new char[arr.get(0).length()][arr.size()];
		for(int i = 0; i < arr.size(); i++){
			a[i] = arr.get(i).toCharArray();
		}

//		prettyPrintMatrix(a);

		for(int i = 0; i < a.length; i++){
			for(int j = 0; j < a[i].length; j++){
				b[j][i] = a[i][j];
			}
		}

//		System.out.println();
//		prettyPrintMatrix(b);

		for (char[] chars : b) {
			StringBuilder temp = new StringBuilder();
			for (char aChar : chars) {
				temp.append(aChar);
			}

			returnArr.add(temp.toString());
		}

		return returnArr;
	}

	public static void prettyPrintMatrix(char[][] a){
		for (char[] chars : a) {
			for (char aChar : chars) {
				System.out.printf("%4c", aChar);
			}
			System.out.println();
		}
	}
}
