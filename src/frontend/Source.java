package frontend;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Source {
    private BufferedReader reader;
    private String line;
    private int lineIndex;

    public Source(String sourceFile) {
        try {
            reader = new BufferedReader(new FileReader(sourceFile));
            lineIndex = 0;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public char currentChar() throws IOException {
        // Need to read the next line?
        if (lineIndex > line.length()) {
            readLine();
            return nextChar();
        }

        // Return the character at the current position.
        else {
            return line.charAt(lineIndex);
        }
    }

    public char nextChar() throws IOException {
        lineIndex++;
        return currentChar();
    }

    private boolean readLine() throws IOException {
        line = reader.readLine();  // Null when at the end of the source
        lineIndex = -1;

        if (line != null) {
            lineIndex++;
            return true;
        }

        return false;
    }

    public void close() throws IOException {
        if (reader != null) {
            try {
                reader.close();
            }
            catch (IOException e) {
                e.printStackTrace();
                throw e;
            }
        }
    }
}
