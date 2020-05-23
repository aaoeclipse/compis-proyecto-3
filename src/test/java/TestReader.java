import junit.framework.TestCase;
import reader.DefaultValues;
import reader.ReaderCustom;

import java.io.File;

public class TestReader extends TestCase {
    ReaderCustom reader;
    String[] testString;

    protected void setUp(){
        testString = new String[]{"AB", "A B", "A\n"};
    }

    public void testNormal(){
        int[] returnedReader = new int[2];
        reader = new ReaderCustom(testString[0]);
        assertEquals(65, reader.next());
        assertEquals(66, reader.next());
    }

    public void testSpace(){
        int[] returnedReader = new int[3];
        reader = new ReaderCustom(testString[1]);
        assertEquals(65, reader.next());

        assertEquals(reader.next(), DefaultValues.BLANK);
        assertEquals(66, reader.next());
    }

    public void testNewLine(){
        int[] returnedReader = new int[2];
        reader = new ReaderCustom(testString[2]);
        assertEquals(65, reader.next());
        assertEquals(DefaultValues.NL, reader.next());
    }

    public void testEndOfFile(){
        int[] returnedReader = new int[2];
        reader = new ReaderCustom(testString[0]);
        assertEquals(reader.next(), 65);
        assertEquals(reader.next(), 66);
        assertEquals(reader.next(), DefaultValues.EOF);
    }

    public void testReaderWithFile(){
        reader = new ReaderCustom(new File("src/main/resources/testFile.txt"));
        assertEquals(97, reader.next());
        assertEquals(97, reader.next());
        assertEquals(DefaultValues.NL, reader.next());
        assertEquals(98, reader.next());
        assertEquals(DefaultValues.BLANK, reader.next());
        assertEquals(99, reader.next());
        assertEquals(DefaultValues.EOF, reader.next());
    }
}
