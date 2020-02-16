package mobilePlatforms;

import driver.DriverFactory;
import enums.MobilePlatform;
import enums.SwipeDirection;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.remote.MobileBrowserType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.AbstractPage;
import pages.Page;
import utils.Helpers;
import utils.PropertiesReader;

import java.io.File;
import java.net.MalformedURLException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.PointOption.point;


public abstract class AbstractPlatform {

    protected MobilePlatform mobilePlatform;
    protected DesiredCapabilities capabilities;
    protected PropertiesReader propertiesReader;
    private AbstractPage actualPage;
    private int secondsToWait = 15;
    private AppiumDriver driver;


    public AbstractPlatform(MobilePlatform mobilePlatform){
        this.mobilePlatform = mobilePlatform;
        capabilities = new DesiredCapabilities();
        propertiesReader = new PropertiesReader();
    }

    public void openApp(){
        initCapabilities();
        addInstalledAppCapabilities();
        try {
            driver = DriverFactory.getDriver(propertiesReader.getUrl(), capabilities, mobilePlatform);
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            Helpers helpers = new Helpers(this);
            helpers.handleDialogs();
        } catch (MalformedURLException e) {
            print(() -> "MalformedURLException caught: " + e);
        }
    }

    public void navigateTo(String url){
        driver.get(url);
    }

    public void setText(MobileElement element, String text) {
        setText(element, text, true);
    }

    public void setText(MobileElement element, String text, boolean hideKeyBoard) {
        boolean isDisplayed = isElementDisplayed(element);
        if (isDisplayed) {
            element.sendKeys(text);
            if (hideKeyBoard)
                getDriver().hideKeyboard();
        }
    }

    public void iShouldBeOnPage(String pageName) {
        actualPage = getCurrentPageAsObject(pageName);
        boolean isExpectedPage = actualPage.verifyPage();
        System.out.println((getMessageBasedOnCondition(() -> isExpectedPage, pageName)));
        Assert.assertTrue(isExpectedPage,"actual page is not " + pageName);
    }

    public <T extends AbstractPage> T getPageAs(Class<T> PageClass) {
        return (T) actualPage;
    }

    public void swipe(SwipeDirection swipeDirection) {
        print(() -> "Swiping " + swipeDirection + "...");
        Dimension size = driver.manage().window().getSize();
        int startX,startY,endY;
        switch (swipeDirection){
            case DOWN:
                startX = size.getWidth() / 2;
                startY = (int) (size.getHeight() * 0.7);
                endY = (int) (size.getHeight() * 0.10);
                performSwipe(startX, startY, endY);
                break;
            case UP:
                break;
        }
    }

    public void verifyThatConditionIsTrue(boolean condition, String errorMessage){
        Assert.assertTrue(condition, errorMessage);
    }


    public void print (Supplier<String> text) {
        System.out.println(text.get());
    }

    public void clickOnElement(MobileElement element) {
        clickOnElement(element, secondsToWait);
    }

    public void clickOnElement(MobileElement element, int waitInSeconds) {
        print(() -> "Trying to click on element " + element);
        boolean isClickable = isElementClickable(element, waitInSeconds);
        if (isClickable) {
            element.click();
            print(() ->"Clicked on element " + element);
        } else
            throw new RuntimeException("Wasn't able to click on element " + element);
    }

    public boolean isElementDisplayed(MobileElement element) {
        return isElementDisplayed(element, secondsToWait);
    }

    public boolean isElementDisplayed(MobileElement element, int waitInSeconds) {
        try {
            waitFor(ExpectedConditions.visibilityOf(element), waitInSeconds);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isElementDisplayed(By locator, int waitInSeconds) {
        try {
            waitFor(ExpectedConditions.visibilityOfElementLocated(locator), waitInSeconds);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isElementClickable(MobileElement element, int waitInSeconds) {
        try {
            waitFor(ExpectedConditions.elementToBeClickable(element), waitInSeconds);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public void sleep(long timeInSeconds) {
        try {
            TimeUnit.SECONDS.sleep(timeInSeconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public AppiumDriver getDriver() {
        return driver;
    }

    public void tearDown() {
            driver.quit();
    }

    private void performSwipe(int startX, int startY, int endY) {
        new TouchAction(driver)
                .press(point(startX, startY))
                .waitAction(waitOptions(Duration.ofSeconds(1)))
                .moveTo(point(startX, endY)).release().perform();
    }

    private String getMessageBasedOnCondition(BooleanSupplier condition, String text){
        if (condition.getAsBoolean())
            return "Actual page is " + text + " as expected";
        else
            return "FAIL!!! - Actual page is not " + text + " as expected";
    }
    private AbstractPage getCurrentPageAsObject(String pageName) {
        Page page = Page.valueOf(pageName);
        return page.getPage(this);
    }

    private void waitFor(ExpectedCondition<WebElement> webElementExpectedCondition, int waitInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, waitInSeconds);
        wait.until(webElementExpectedCondition);
    }

    protected abstract void initCapabilities();
    protected abstract void addInstalledAppCapabilities();

}
