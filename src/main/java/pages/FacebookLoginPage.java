package pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import mobilePlatforms.AbstractPlatform;

public class FacebookLoginPage extends AbstractPage {

    public FacebookLoginPage(AbstractPlatform abstractPlatform) {
        super(abstractPlatform);
    }

    @Override
    public boolean verifyPage() {
        return platform.isElementDisplayed(title,10);
    }


    public void login(String user, String pass) {
        platform.setText(username,user);
        platform.setText(password,pass);
        platform.clickOnElement(loginButton);
        handleConfigurations();
    }

    private void handleConfigurations() {
        if(platform.isElementDisplayed(notNowButton)) platform.clickOnElement(notNowButton);
        if(platform.isElementDisplayed(allowNotificationsButton)) platform.clickOnElement(allowNotificationsButton);
        if(platform.isElementDisplayed(notNowButton)) platform.clickOnElement(notNowButton);
    }


    @AndroidFindBy(xpath = "//android.view.View[@text='facebook']")
    private MobileElement title;
    @AndroidFindBy(xpath = "//android.view.View[@resource-id='u_0_5']")
    private MobileElement loginButton;
    @AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='m_login_email']")
    private MobileElement username;
    @AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='m_login_password']")
    private MobileElement password;
    @AndroidFindBy(xpath = "//android.widget.Button[@text='Not Now']")
    private MobileElement notNowButton;
    @AndroidFindBy(xpath = "//android.widget.Button[@text='ALLOW']")
    private MobileElement allowNotificationsButton;
}
