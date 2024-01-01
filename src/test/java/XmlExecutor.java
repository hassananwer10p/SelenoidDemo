import org.testng.TestNG;
import java.util.ArrayList;
import java.util.List;

public class XmlExecutor {

    public static void main(String[] args) {
        TestNG runner = new TestNG();
        List<String> suitefiles = new ArrayList<String>();
        suitefiles.add("testng.xml");
        runner.setTestSuites(suitefiles);
        runner.run();
    }

}
