package cucumber;

import enums.Context;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ScenarioContext {
    private Map<String, Object> scenarioContext;

    public ScenarioContext() {
        scenarioContext = new ConcurrentHashMap<>();
    }

    public <T> void setContext(Context key, T value) {
        scenarioContext.put(key.toString(), value);
    }

    public <T> T getContext(Context key) {
        return (T) scenarioContext.get(key.toString());
    }

    public boolean containsKey(Context key) {
        return scenarioContext.containsKey(key.toString());
    }
}
