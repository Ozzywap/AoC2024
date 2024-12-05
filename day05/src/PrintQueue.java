import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class PrintQueue {

	public static void main(String[] args) {
		String input = "";
		try{
			input = Files.readString(Path.of("src/input.txt"));
		} catch (Exception e){
			System.out.println(e.getMessage());
			System.exit(0);
		}

		String rules = input.split("\n\n")[0];
		String updates = input.split("\n\n")[1];

		ArrayList<String> rulesLst =
			new ArrayList<>(Arrays.asList(rules.split("\n")));
		ArrayList<String> updatesLst =
			new ArrayList<>(Arrays.asList(updates.split("\n")));
		HashMap<String, ArrayList<String>> hashMap = new HashMap<>();

		for (String value : rulesLst) {
			String firstNum = value.split("\\|")[0];
			String secondNum = value.split("\\|")[1];
			if (hashMap.get(firstNum) != null) {
				hashMap.get(firstNum).add(secondNum);
			} else {
				hashMap.put(firstNum, new ArrayList<String>());
				hashMap.get(firstNum).add(secondNum);
			}
		}

		int count = 0;
		ArrayList<String[]> validRows = new ArrayList<>();
		ArrayList<String[]> invalidRows = new ArrayList<>();
		boolean validUpdate = true;
		for (String value : updatesLst) {
			String[] updateRow = value.split(",");
			validUpdate = true;
			for (int j = 0; j < updateRow.length; j++) {
				ArrayList<String> vals = hashMap.get(updateRow[j]);
				for (int k = 0; k < j; k++) {
					if (vals != null) {
						if (vals.contains(updateRow[k])) {
							validUpdate = false;
						}
					}
				}
			}

			if (validUpdate) {
				validRows.add(updateRow);
			} else {
				invalidRows.add(updateRow);
			}
		}

		for(String[] s: validRows){
			count += Integer.parseInt(s[s.length/2]);
		}

		System.out.println("part 1: " + count);

		for (String[] invalidRow : invalidRows) {
			for (int i = 0; i < invalidRow.length; i++) {
				for (int j = 0; j < invalidRow.length; j++) {
					if (i != j) {
						if (hashMap.get(invalidRow[j]) != null && hashMap.get(
							invalidRow[j]).contains(invalidRow[i])) {
							if (i < j) {
								String temp = invalidRow[i];
								invalidRow[i] = invalidRow[j];
								invalidRow[j] = temp;
							}
						}
					}
				}
			}
		}

		count = 0;
		for(String[] s: invalidRows){
			count += Integer.parseInt(s[s.length/2]);
		}
		System.out.println("part 2: " + count);
	}

}
