import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DNAGenerator {

	public static String generateDNA(int length, int number) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < number; i++) {
			for (int j = 0; j < length; j++) {
				int bases = (int) (Math.random() * 4);
				if (bases == 0) {
					sb.append("A");
				} else if (bases == 1) {
					sb.append("C");
				} else if (bases == 2) {
					sb.append("G");
				} else if (bases == 3) {
					sb.append("T");
				}
			}
			sb.append(",\n");
		}
//		System.out.print(sb.toString());
		return sb.toString();
	}

	public static void writeToFile(String filename, String data) {
		try {
			File file = new File(filename);

			FileWriter write;

			write = new FileWriter(file, true);

			BufferedWriter bufferedWriter = new BufferedWriter(write);

			bufferedWriter.write(data);

			bufferedWriter.flush();

			write.close();

			bufferedWriter.close();
			System.out.println("Wirte Successfully!");
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		String data = generateDNA(Integer.valueOf(args[0]), Integer.valueOf(args[1]));
		writeToFile(args[2],data);
	}
}
