package utils;


import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import mobilePlatforms.AbstractPlatform;
import org.openqa.selenium.support.PageFactory;

public class Helpers {

    private AbstractPlatform platform;

    public Helpers(AbstractPlatform abstractPlatform){
        platform = abstractPlatform;
        PageFactory.initElements(new AppiumFieldDecorator(platform.getDriver()),this);
    }
    public void handleDialogs(){
        if(platform.isElementDisplayed(acceptButton)) platform.clickOnElement(acceptButton);
        if(platform.isElementDisplayed(declineSignInButton)) platform.clickOnElement(declineSignInButton);
        if(platform.isElementDisplayed(declineSavingDataButton)) platform.clickOnElement(declineSavingDataButton);
    }

    public void installOrOpenAppOnGooglePlayStore(){
        platform.clickOnElement(playStoreInstallOpenButton,30);
        platform.clickOnElement(playStoreInstallOpenButton,30);
    }




    @AndroidFindBy(id = "com.android.chrome:id/terms_accept")
    private MobileElement acceptButton;
    @AndroidFindBy(id = "com.android.chrome:id/button_secondary")
    private MobileElement declineSavingDataButton;
    @AndroidFindBy(id = "com.android.chrome:id/negative_button")
    private MobileElement declineSignInButton;
    @AndroidFindBy(id = "com.android.vending:id/right_button")
    private MobileElement playStoreInstallOpenButton;
}
