package runner;

import org.testng.annotations.Test;
import pages.FacebookHomePage;
import pages.FacebookLoginPage;
import utils.Helpers;

public class TestRunner extends Hooks{

    @Test
    public void test(){
        platform.openApp();
        platform.navigateTo("https://www.facebook.com");
        platform.iShouldBeOnPage("FacebookLoginPage");
        platform.getPageAs(FacebookLoginPage.class).login("uemuptkcvb_1579771708@tfbnw.net","Aa123456!");
        platform.iShouldBeOnPage("FacebookHomePage");
        platform.getPageAs(FacebookHomePage.class).publishMessageOnTheFeed("https://afonelink.onelink.me/X9ni/d1dee455");
        platform.getPageAs(FacebookHomePage.class).clickOnLinkFromFeed("https://afonelink.onelink.me/X9ni/d1dee455");
        Helpers helpers = new Helpers(platform);
        helpers.installOrOpenAppOnGooglePlayStore();
        platform.iShouldBeOnPage("AppsFlyerPage");
    }
}
