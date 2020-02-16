package pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import mobilePlatforms.AbstractPlatform;

public class FacebookHomePage extends AbstractPage {

    public FacebookHomePage(AbstractPlatform abstractPlatform) {
        super(abstractPlatform);
    }

    @Override
    public boolean verifyPage()
    {
        return platform.isElementDisplayed(feedButton);
    }

    public void publishMessageOnTheFeed(String message){
        platform.clickOnElement(feedButton);
        platform.clickOnElement(feedEditText);
        platform.setText(feedEditText,message);
        platform.clickOnElement(postButton);
    }

    public void clickOnLinkFromFeed(String link){
        String locator = getModifiedLocator(link);
        MobileElement linkElement = (MobileElement) platform.getDriver().findElementByXPath(locator);
        platform.clickOnElement(linkElement);
    }

    private String getModifiedLocator(String link) {
        return "//android.view.View[@text='XX']".replace("XX",link);
    }
    @AndroidFindBy(xpath = "//android.widget.Button[@resource-id='u_0_t']")
    private MobileElement feedButton;
    @AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='uniqid_1']")
    private MobileElement feedEditText;
    @AndroidFindBy(xpath = "//android.widget.Button[@text='Post']")
    private MobileElement postButton;

}
