package runner;

import enums.MobilePlatform;
import mobilePlatforms.AbstractPlatform;
import mobilePlatforms.MobilePlatformFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class Hooks {

    protected AbstractPlatform platform;

    @BeforeMethod
    public void setup() {
        platform = MobilePlatformFactory.getMobilePlatform(MobilePlatform.ANDROID);
    }

    @AfterMethod
    public void tearDown() {
        platform.tearDown();
    }
}
