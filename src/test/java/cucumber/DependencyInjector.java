package cucumber;

import io.cucumber.java.Before;
import org.picocontainer.DefaultPicoContainer;
import org.picocontainer.MutablePicoContainer;

public class DependencyInjector {
    private static MutablePicoContainer container;

    static {
        container = new DefaultPicoContainer();
        container.addComponent(TestContext.class);
    }

    @Before
    public void setUp() {
        container.addComponent(TestContext.class, new TestContext());
    }

    public static TestContext getTestContext() {
        return container.getComponent(TestContext.class);
    }
}
