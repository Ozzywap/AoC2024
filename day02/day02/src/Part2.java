import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Part2 {
	public static void main(String[] args) {
		int count = 0;
		try{
			File file = new File("src/input.txt");
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				String data = scanner.nextLine();
				boolean validRow = false;
				String[] dataSplit = data.split(" ");
				ArrayList<String> lvlArr = new ArrayList<>(Arrays.asList(dataSplit));

				for(int j = 0; j < dataSplit.length; j++){
					ArrayList<String> lvlArrCopy = new ArrayList<>(lvlArr);
					lvlArrCopy.remove(j);
					if(validLevel(lvlArrCopy)){
						validRow = true;
						break;
					}
				}
				if(validRow){
					count++;
				}
			}
			scanner.close();
		} catch (Exception e){
			System.out.println(e.getMessage());
			System.exit(1);
		}
		System.out.println("count: " + count);

	}

	public static boolean validLevel(ArrayList<String> dataSplit){
		boolean validRow = true;
		for(int i = 0; i < dataSplit.size() - 1 ; i++){
			int diff =
				Math.abs(Integer.parseInt(dataSplit.get(i)) - Integer.parseInt(dataSplit.get(i+1)));
			if(!(Integer.parseInt(dataSplit.get(i)) < Integer.parseInt(dataSplit.get(i+1)) &&
				(diff >= 1 && diff <=3))){
				validRow = false;
			}
		}
		if(validRow){
			return true;
		}
		validRow = true;
		for(int i = 0; i < dataSplit.size() - 1 ; i++){
			int diff =
				Math.abs(Integer.parseInt(dataSplit.get(i)) - Integer.parseInt(dataSplit.get(i+1)));
			if(!(Integer.parseInt(dataSplit.get(i)) > Integer.parseInt(dataSplit.get(i+1)) &&
				(diff >= 1 && diff <=3))){
				validRow = false;
			}
		}
		return validRow;
	}
}
