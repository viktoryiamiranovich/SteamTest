package framework;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public abstract class BaseTest extends BaseEntity {


    @BeforeTest (description = "opening browser")
    public void driverStart(){
        setup();
    }

    @AfterTest
    public void testCompletion(){
        tearDown();
    }
}
