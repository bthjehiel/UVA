import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;

class Main {
	public static void main(String args[]) throws IOException {
		Main prog = new Main();
		prog.Begin();
	}

	void Begin() throws IOException {
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
//		Scanner sc = new Scanner(System.in);
//		IntegerScanner sc = new IntegerScanner(System.in);
		BufferedReader sc= new BufferedReader(new InputStreamReader(System.in));
//		BufferedReader sc= new BufferedReader(new FileReader(new File("C:\\Users\\Jehiel\\Desktop\\test.txt")));
        
        HashMap<String,Integer> map = generateAllValues();
		String word;
		while ((word=sc.readLine())!=null) {
			pw.println(map.getOrDefault(word, 0));
		}
		pw.close();
	}

	HashMap<String, Integer> generateAllValues() {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		LinkedList<String> queue = new LinkedList<String>();
		
		char oneLetterWord = 'a';
		for(int i=0; i < 26; i++) {
			queue.add("" + oneLetterWord++);
		}
		BFS(map, queue);
		
		return map;
	}
	
	void BFS(HashMap<String, Integer> map, LinkedList<String> queue) {
		int position = 1;
		while(!queue.isEmpty()) {
			String currentWord = queue.removeFirst();
			if(currentWord.length() > 5) {
				break;
			}
			map.put(currentWord, position);
			for(char newChar = (char) (currentWord.charAt(currentWord.length()-1) + 1); newChar <= 'z'; newChar++) {
				String nextWord = currentWord + newChar;
				queue.add(nextWord);
			}
			position++;
		}
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
