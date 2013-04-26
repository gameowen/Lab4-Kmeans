import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class KmeansSeq {
	ArrayList<Point> dataSet;
	int k;
	Point[] results;

	public KmeansSeq() {
		dataSet = new ArrayList<Point>();
		k = 1;
		results = new Point[k];
	}

	public String toString() {
		String str = "";
		for (Point p : dataSet)
			str += p.x + "," + p.y + "\n";
		return str;

	}

	public void readData(String filename) {
		try {
			Scanner scanner = new Scanner(new File(filename));
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] point = line.split(",");
				Point p = new Point();
				p.x = Double.parseDouble(point[0]);
				p.y = Double.parseDouble(point[1]);
				dataSet.add(p);

			}
		} catch (FileNotFoundException e) {
			System.out.println("File not exist");
			e.printStackTrace();
		}

	}
	
	
	
	

	public static void main(String[] args) {
		KmeansSeq ks = new KmeansSeq();
		ks.readData("test.txt");
		System.out.println(ks.toString());

	}

}
