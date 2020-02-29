import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

class Main {
	public static void main(String args[]) throws IOException {
		Main prog = new Main();
		prog.Begin();
	}

	void Begin() throws IOException {
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		IntegerScanner sc = new IntegerScanner(System.in);

		int nextInt;
		while ((nextInt = sc.nextInt()) != 0) {
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			int[] courses = new int[5];
			for (int i = 0; i < nextInt; i++) {
				courses[0] = sc.nextInt();
				courses[1] = sc.nextInt();
				courses[2] = sc.nextInt();
				courses[3] = sc.nextInt();
				courses[4] = sc.nextInt();
				Arrays.sort(courses);
				String newCombination = convertToString(courses);

				Integer oldCount = map.get(newCombination);
				if (oldCount == null) {
					map.put(newCombination, 1);
				} else {
					map.put(newCombination, oldCount + 1);
				}
			}

			int mostPopular = 0;
			int totalCount = 0;
			Iterator it = map.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry mapElement = (Map.Entry) it.next();
				int count = ((int) mapElement.getValue());
				if (count > mostPopular) {
					totalCount = count;
					mostPopular = count;
				} else if (count == mostPopular) {
					totalCount += count;
				}
			}
			pw.println(totalCount);
		}
		pw.close();
	}

	public String convertToString(int[] courses) {
		String s = "";
		for (int i = 0; i < courses.length; i++)
			s += courses[i];
		return s;
	}
}

class IntegerScanner {
	BufferedInputStream bis;

	IntegerScanner(InputStream is) {
		bis = new BufferedInputStream(is, 1000000);
	}

	public int nextInt() {
		int result = 0;
		try {
			int cur = bis.read();
			if (cur == -1)
				return -1;

			while ((cur < 48 || cur > 57) && cur != 45) {
				cur = bis.read();
			}

			boolean negate = false;
			if (cur == 45) {
				negate = true;
				cur = bis.read();
			}

			while (cur >= 48 && cur <= 57) {
				result = result * 10 + (cur - 48);
				cur = bis.read();
			}

			if (negate) {
				return -result;
			}
			return result;
		} catch (IOException ioe) {
			return -1;
		}
	}
}
