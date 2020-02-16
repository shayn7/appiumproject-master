package pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import mobilePlatforms.AbstractPlatform;

public class AppsFlyerPage extends AbstractPage {
    public AppsFlyerPage(AbstractPlatform abstractPlatform) {
        super(abstractPlatform);
    }

    @Override
    public boolean verifyPage() {
        return platform.isElementDisplayed(message);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Welcome to the Second Activity!']")
    private MobileElement message;
}
