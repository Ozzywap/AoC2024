import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MullItOver {
	public static void main(String[] args) {
		String input = "";
		try{
			input = Files.readString(Path.of("src/input.txt"));
		} catch (Exception e){
			System.out.println(e.getMessage());
			System.exit(0);
		}
		part1(input);
		part2(input);
	}

	public static void part1(String input){
		ArrayList<String> allMatches = new ArrayList<>();
		Matcher m = Pattern.compile("mul\\([0-9]*,[0-9]*\\)").matcher(input);
		while (m.find()) {
			allMatches.add(m.group());
		}
		System.out.println(mulPart1(allMatches));
	}

	public static void part2(String input){
		ArrayList<String> allMatches = new ArrayList<>();
		Matcher m = Pattern.compile("mul\\([0-9]*,[0-9]*\\)|don't\\(\\)"
				+ "|do\\(\\)")
			.matcher(input);
		while (m.find()) {
			allMatches.add(m.group());
		}
		System.out.println(mulPart2(allMatches));
	}

	public static int mulPart1(ArrayList<String> arr){
		ArrayList<String> allMatches = new ArrayList<>();
		int count = 0;
		for(String val: arr){
			Matcher m = Pattern.compile("[0-9]*,[0-9]*")
				.matcher(val);
			while (m.find()) {
				allMatches.add(m.group());
			}
		}
		for(String val: allMatches){
			String[] t = val.split(",");
			count += Integer.parseInt(t[0]) * Integer.parseInt(t[1]);
		}

		return count;
	}

	public static int mulPart2(ArrayList<String> arr){
		ArrayList<String> allMatches = new ArrayList<>();
		int count = 0;
		for(String val: arr){
			Matcher m = Pattern.compile("[0-9]*,[0-9]*|don't\\(\\)|do\\(\\)")
				.matcher(val);
			while (m.find()) {
				allMatches.add(m.group());
			}
		}

		boolean enabled = true;
		for(String val: allMatches){
			if(val.equals("don't()")){
				enabled = false;
			} else if (val.equals("do()")) {
				enabled = true;
			} else if (enabled){
				String[] t = val.split(",");
				count += Integer.parseInt(t[0]) * Integer.parseInt(t[1]);
			}
		}

		return count;
	}

}
