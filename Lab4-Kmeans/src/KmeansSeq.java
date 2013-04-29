import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

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

	public void initAssign(int k) {
		int size = dataSet.size();

		Integer[] initCentroids = new Integer[k];
		Set<Integer> helper = new HashSet<Integer>();
		while (helper.size() < k) {
			helper.add((int) (Math.random() * size));
		}
		helper.toArray(initCentroids);
		for (int i = 0; i < initCentroids.length; i++) {
			dataSet.get(initCentroids[i]).isInitCentroid = true;

		}

		for (Point p : dataSet) {
			if (!p.isInitCentroid) {
				double min = Double.MAX_VALUE;
				for (int i = 0; i < initCentroids.length; i++) {
					Point centroid = dataSet.get(initCentroids[i]);
					double distance = EuclideanDistance(p, centroid);
					if (distance < min) {
						min = distance;
						p.belonged = centroid;
					}
				}
			}
		}

	}

	private double EuclideanDistance(Point p1, Point p2) {
		return Math.sqrt(Math.pow((p1.x - p2.x), 2)
				+ Math.pow((p1.y - p2.y), 2));
	}

	public int Assign(int k) {
		initAssign(k);
		int times = 0;
		ArrayList<Point> initCentroidList = new ArrayList<Point>();
		ArrayList<Point> preCentroidList = new ArrayList<Point>();
		ArrayList<Point> curCentroidList = new ArrayList<Point>();
		// get the 2nd centroid
		for (Point centroid : dataSet) {
			Point newCentroid = new Point();
			newCentroid.x += centroid.x;
			newCentroid.y += centroid.y;
			if (centroid.isInitCentroid) {
				int n = 1;
				for (Point p : dataSet) {
					if (!p.isInitCentroid) {
						if (p.belonged.equals(centroid)) {
							n++;
							newCentroid.x += p.x;
							newCentroid.y += p.y;
						}
					}

				}
				newCentroid.x = newCentroid.x / n;
				newCentroid.y = newCentroid.y / n;
				initCentroidList.add(newCentroid);

			}

		}
		preCentroidList = initCentroidList;
		while (!isSame(preCentroidList, curCentroidList)) {
			if (times != 0) {
				preCentroidList = curCentroidList;
				curCentroidList = new ArrayList<Point>();
			}
			for (Point p : dataSet) {
				double min = Double.MAX_VALUE;
				for (Point centroid : preCentroidList) {
					double distance = EuclideanDistance(p, centroid);
					if (distance < min) {
						min = distance;
						p.belonged = centroid;
					}
				}
			}
			for (Point centroid : preCentroidList) {
				Point newCentroid = new Point();
				newCentroid.x += centroid.x;
				newCentroid.y += centroid.y;
				int n = 1;
				for (Point p : dataSet) {
					if (p.belonged.equals(centroid)) {
						n++;
						newCentroid.x += p.x;
						newCentroid.y += p.y;
					}

					newCentroid.x = newCentroid.x / n;
					newCentroid.y = newCentroid.y / n;
					curCentroidList.add(newCentroid);
				}
			}
			times++;
		}
		System.out.print(curCentroidList);
		return times;
	}

	private boolean isSame(ArrayList<Point> preCentroidList,
			ArrayList<Point> curCentroidList) {
		if (preCentroidList.size() == 0 || curCentroidList.size() == 0)
			return false;
		for (int i = 0; i < preCentroidList.size(); i++) {
			if (!isSimilar(preCentroidList.get(i), curCentroidList.get(i)))
				return false;
		}
		return true;
	}

	private boolean isSimilar(Point p1, Point p2) {
		if (Math.abs(p1.x - p2.x) < 1e-8 && Math.abs(p1.y - p2.y) < 1e-8)
			return true;
		return false;
	}

	public static void main(String[] args) {
		KmeansSeq ks = new KmeansSeq();
		ks.readData("test.txt");
		ks.Assign(2);
		// System.out.println(ks.toString());

	}

}
