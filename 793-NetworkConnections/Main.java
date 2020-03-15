import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Set;

class Main {
	public static void main(String args[]) throws IOException {
		Main prog = new Main();
		prog.Begin();
	}

	void Begin() throws IOException {
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		BufferedReader sc= new BufferedReader(new InputStreamReader(System.in));
		int numCases = Integer.parseInt(sc.readLine());
		sc.readLine();
		for(int i=0; i < numCases; i++) {
			if(i>0)
				pw.println();
			UFDS ufds = new UFDS();
			int numComp = Integer.parseInt(sc.readLine());
			String nextLine;
			int numSuccess = 0, numFail = 0;
			while(true) {
				nextLine = sc.readLine();
				if(nextLine == null || nextLine.isEmpty()) {
					pw.println(numSuccess + "," + numFail);
					break;
				}
				String[] words = nextLine.split(" ");
				int Ci = Integer.parseInt(words[1]), Cj = Integer.parseInt(words[2]);
				if(words[0].equals("c")) {
					ufds.connect(Ci, Cj);
				}else {
					boolean isConnected = ufds.isSameSet(Ci, Cj);
					if(isConnected)
						numSuccess++;
					else
						numFail++;
				}
			}
		}
		pw.close();
	}

}

class UFDS{
	HashMap<Integer, Integer> parentMap = new HashMap<Integer, Integer>();
	HashMap<Integer, Integer> rankMap = new HashMap<Integer, Integer>();
	
	
	public UFDS() {
	}
	
	public void connect(int Ci, int Cj) {
		if(!isSameSet(Ci, Cj)) {
			int x = findSet(Ci), y = findSet(Cj);
			int rankX = rank(x), rankY = rank(y);
			if(rankX > rankY)
				parentMap.put(y, x);
			else {
				parentMap.put(x, y);
				if(rankX == rankY)
					rankMap.put(y, rankY+1);
			}
		}
	}
	
	public boolean isSameSet(int Ci, int Cj) {
		return findSet(Ci) == findSet(Cj);
	}
	
	private int findSet(int Ci) {
		if(parent(Ci) == Ci)
			return Ci;
		else {
			int set = findSet(parent(Ci));
			parentMap.put(Ci, set);
			return set;
		}
	}
	
	private int parent(int Ci) {
		Integer parent = parentMap.get(Ci);
		if(parent == null)
			return Ci;
		return parent;
	}
	
	private int rank(int Ci) {
		Integer rank = rankMap.get(Ci);
		if(rank == null)
			return 0;
		return rank;
	}
}
