import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

class Main
{
    public static void main (String args[]) throws FileNotFoundException  // entry point from OS
    {
        Main TexQuotes = new Main();  
        TexQuotes.Begin();        
    }

    void Begin() throws FileNotFoundException
    {
		BufferedInputStream bis = new BufferedInputStream(System.in, 1000000);
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		boolean isOpen = true;
		int readByte;
		try {
			readByte = bis.read();
			while(readByte != -1) {
				char ch = (char) readByte;
				if(ch == '"') {
					if(isOpen) {
			            pw.print("``");
					}else{
			            pw.print("''");
					}
					isOpen = !isOpen;
				}else {
		            pw.print(ch);
				}
				readByte = bis.read();
			}
		} catch (IOException e) {
		}
        pw.close();
	}
}
