package cucumber;

import apiEngine.EndPoints;
import dataProvider.ConfigReader;
import enums.Context;

public class TestContext {

    private final EndPoints endPoints;
    private final ScenarioContext scenarioContext;

    public TestContext() {
        endPoints = new EndPoints(ConfigReader.getInstance().getBaseUrl());
        scenarioContext = new ScenarioContext();
        scenarioContext.setContext(Context.EMAIL, ConfigReader.getInstance().getEmail());
        scenarioContext.setContext(Context.PASSWORD, ConfigReader.getInstance().getPassword());
    }

    public EndPoints getEndPoints() {
        return endPoints;
    }

    public ScenarioContext getScenarioContext() {
        return scenarioContext;
    }
}
