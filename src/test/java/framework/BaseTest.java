package framework;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.apache.log4j.Logger;

public abstract class BaseTest extends BaseEntity {

    public static final Logger log = Logger.getLogger(BaseTest.class);

    @BeforeTest
    public void driverStart(){
        log.info("Open browser");
        setup();
    }

    @AfterTest
    public void testCompletion(){
        tearDown();
        log.info("Close browser");
    }
}
