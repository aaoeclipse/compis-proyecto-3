import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {
    public static void main(String[] args) {
        Result readerTest = JUnitCore.runClasses(TestReader.class);
        for (Failure failure : readerTest.getFailures()) { System.out.println(failure.toString()); }
        System.out.println(readerTest.wasSuccessful());
    }
}