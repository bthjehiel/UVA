import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashSet;

class Main {
	public static void main(String args[]) throws FileNotFoundException // entry point from OS
	{
		Main TexQuotes = new Main();
		TexQuotes.Begin();
	}

	void Begin() throws FileNotFoundException {
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		IntegerScanner sc = new IntegerScanner(System.in);

		while (true) {
			int total = 0;
			int jackCDs = sc.nextInt();
			int jillCDs = sc.nextInt();
			if (jackCDs == jillCDs && jillCDs == 0)
				break;
			HashSet<Integer> jackCollection = new HashSet<Integer>();
			for (int i = 0; i < jackCDs; i++) {
				jackCollection.add(sc.nextInt());
			}
			for (int i = 0; i < jillCDs; i++) {
				if (jackCollection.contains(sc.nextInt())) {
					total++;
				}
			}
			pw.println(total);
		}
		pw.close();
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
