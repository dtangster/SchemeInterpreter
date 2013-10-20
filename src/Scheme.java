import frontend.Scanner;
import frontend.Source;

import java.io.IOException;

public class Scheme {
    public static void main(String [] args) {
        // Do no error checking. Assume the filename is correct and syntax is correct
        Source source = new Source(args[0]);
        Scanner scanner = new Scanner(source);

        try {
            source.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
