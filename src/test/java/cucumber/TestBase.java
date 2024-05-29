package cucumber;

import apiEngine.RestAssuredConfig;
import org.junit.BeforeClass;

public class TestBase {

    @BeforeClass
    public static void setup() {
        RestAssuredConfig.configureRestAssured();
    }
}
