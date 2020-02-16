package pages;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import mobilePlatforms.AbstractPlatform;
import org.openqa.selenium.support.PageFactory;

public abstract class AbstractPage {

    protected AbstractPlatform platform;

    public AbstractPage(AbstractPlatform abstractPlatform) {
        platform = abstractPlatform;
        PageFactory.initElements(new AppiumFieldDecorator(abstractPlatform.getDriver()),this);
    }

    public abstract boolean verifyPage();

}
