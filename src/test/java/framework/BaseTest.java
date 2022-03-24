package framework;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.apache.log4j.Logger;
import org.testng.annotations.Parameters;

public abstract class BaseTest extends BaseEntity {

    public static final Logger log = Logger.getLogger(BaseTest.class);

    @Parameters({"Language"})
    @BeforeTest
    public void driverStart(String lang){
        log.info("Open browser");
        setup(lang);
    }

    @AfterTest
    public void testCompletion(){
        tearDown();
        log.info("Close browser");
    }
}
