import java.io.File;
import java.util.Scanner;

public class Part1 {
	public static void main(String[] args) {
		int count = 0;
		try{
			File file = new File("src/input.txt");
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				String data = scanner.nextLine();
				String[] dataSplit = data.split(" ");
				boolean validRow = true;
				for(int i = 0; i < dataSplit.length - 1 ; i++){
					int diff = Math.abs(Integer.parseInt(dataSplit[i]) - Integer.parseInt(dataSplit[i+1]));
					if(!(Integer.parseInt(dataSplit[i]) < Integer.parseInt(dataSplit[i+1]) &&
						(diff >= 1 && diff <=3))){
						validRow = false;
					}
				}
				if(validRow){
					count++;
					continue;
				}
				validRow = true;
				for(int i = 0; i < dataSplit.length - 1 ; i++){
					int diff = Math.abs(Integer.parseInt(dataSplit[i]) - Integer.parseInt(dataSplit[i+1]));
					if(!(Integer.parseInt(dataSplit[i]) > Integer.parseInt(dataSplit[i+1]) &&
						(diff >= 1 && diff <=3))){
						validRow = false;
					}
				}
				if(validRow){
					count++;
				}
			}
			scanner.close();
		} catch (Exception e){
			System.out.println(e.getMessage());
			System.exit(0);
		}
		System.out.println(count);
	}

}
