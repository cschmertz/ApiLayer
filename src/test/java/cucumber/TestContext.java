package cucumber;

import apiEngine.EndPoints;
import dataProvider.ConfigReader;
import enums.Context;

public class TestContext {

    private static TestContext instance;
    private EndPoints endPoints;
    private ScenarioContext scenarioContext;

    private TestContext() {
        String baseUrl = ConfigReader.getInstance().getBaseUrl();
        endPoints = new EndPoints(baseUrl);
        scenarioContext = new ScenarioContext();
    }

    public static synchronized TestContext getInstance() {
        if (instance == null) {
            instance = new TestContext();
        }
        return instance;
    }

    public EndPoints getEndPoints() {
        return endPoints;
    }

    public ScenarioContext getScenarioContext() {
        return scenarioContext;
    }
}
