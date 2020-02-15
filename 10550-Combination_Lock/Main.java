import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
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
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        IntegerScanner sc = new IntegerScanner(System.in);
        int start = sc.nextInt();
        
        while(start != -1) {
        	int first = sc.nextInt();
        	int second = sc.nextInt();
        	int third = sc.nextInt();
        	if(start == first && first == second && second == third && third == 0)
        		break;
        	int total = 1080 + ((start - first + 40) % 40 + (second - first + 40) % 40 + (second - third + 40) % 40)*9;
            pw.println(total);
            start = sc.nextInt();
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
