package cucumber;

import apiEngine.EndPoints;
import dataProvider.ConfigReader;

public class TestContext {

    private final EndPoints endPoints;
    private final ScenarioContext scenarioContext;

    public TestContext() {
        endPoints = new EndPoints(ConfigReader.getInstance().getBaseUrl());
        scenarioContext = new ScenarioContext();
//        scenarioContext.setContext(Context.USER_ID, ConfigReader.getInstance().getUserID());
    }

    public EndPoints getEndPoints() {
        return endPoints;
    }

    public ScenarioContext getScenarioContext() {
        return scenarioContext;
    }
}
