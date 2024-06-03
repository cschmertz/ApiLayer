package cucumber;

import io.cucumber.java.Before;
import org.picocontainer.DefaultPicoContainer;
import org.picocontainer.MutablePicoContainer;

public class DependencyInjector {

    private static DefaultPicoContainer container;

    static {
        container = new DefaultPicoContainer();
        container.addComponent(TestContext.getInstance());
    }

    public static TestContext getTestContext() {
        return container.getComponent(TestContext.class);
    }
}
