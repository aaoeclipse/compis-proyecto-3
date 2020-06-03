package reader;

import dataStructures.LexicalAnalyzer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

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
