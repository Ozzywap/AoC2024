import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class HistorianHysteria {

	public static void main(String[] args) {
		ArrayList<Integer> left = new ArrayList<>();
		ArrayList<Integer> right = new ArrayList<>();

		try{
			File file = new File("src/input.txt");
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				String data = scanner.nextLine();
				String[] dataSplit = data.split("   ");
				left.add(Integer.parseInt(dataSplit[0]));
				right.add(Integer.parseInt(dataSplit[1]));
			}
			scanner.close();
		} catch (Exception e){
			System.out.println(e.getMessage());
			System.exit(0);
		}

		System.out.println(part1(left, right));
		System.out.println(part2(left, right));
	}

	public static int part1(ArrayList<Integer> left, ArrayList<Integer> right){
		Collections.sort(left);
		Collections.sort(right);

		int sum = 0;

		for(int i = 0; i < left.size(); i++){
			sum += Math.abs(right.get(i) - left.get(i));
		}

		return sum;
	}

	public static int part2(ArrayList<Integer> left, ArrayList<Integer> right){
		int sum = 0;
		HashMap<Integer, Integer> hashMap = new HashMap<>();
		for(int val: right){
			if (hashMap.containsKey(val)){
				hashMap.put(val, hashMap.get(val) + 1);
			} else{
				hashMap.put(val, 1);
			}
		}
		for (Integer integer : left) {
			sum += integer * hashMap.getOrDefault(integer, 0);
		}
		return sum;
	}
}
