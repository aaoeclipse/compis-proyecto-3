package reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This class work for reading a big String or file in order to
 * pass char by char each value
 */
public class ReaderCustom {
    // allChars contains all the string in which it's going to use to send each char
    private String allChars;
    private int position;
    private int line;

    public ReaderCustom(String allChars){
        this.allChars = allChars;
        this.position = 0;
        this.line = 0;
    }

    public ReaderCustom(File file) {
        this.allChars = "";
        this.position = 0;
        this.line = 0;

        boolean first = true;
        try {
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                if (first) {
                    allChars += myReader.nextLine();
                    first = false;
                }
                else
                    allChars += "\n"+myReader.nextLine();
            }
            myReader.close();
        } catch (FileNotFoundException e) { e.printStackTrace(); }
    }


    public int next(){
        int nextInt = DefaultValues.EOF;
        if (position < allChars.length()){
            nextInt = allChars.charAt(position);
            if (nextInt == DefaultValues.NL)
                line++;
            position++;
            return nextInt;
        }
        return nextInt;
    }

    @Override
    public String toString() {
        return "ReaderCustom{" +
                "allChars='" + allChars + '\'' +
                ", position=" + position +
                ", line=" + line +
                '}';
    }
}
