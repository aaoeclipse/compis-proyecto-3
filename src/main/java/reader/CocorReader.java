package reader;

import controler.LexicalAnalyzer;

import java.io.File;

public class CocorReader {
    ReaderCustom reader;
    String[] block;
    LexicalAnalyzer lex;

    public CocorReader(String fileName){
        File file = new File(fileName);
        reader = new ReaderCustom(file);
        this.block = new String[]{"COMPILER", "CHARACTERS", "KEYWORDS", "TOKENS", "PRODUCTIONS"};
    }





}
