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
		BufferedReader sc= new BufferedReader(new InputStreamReader(System.in));
        
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
