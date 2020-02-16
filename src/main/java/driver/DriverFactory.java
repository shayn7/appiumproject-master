package driver;

import enums.MobilePlatform;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;

public class DriverFactory {

    public static synchronized AppiumDriver getDriver(String hubUrl, DesiredCapabilities desiredCapabilities, MobilePlatform mobilePlatform) throws MalformedURLException {
        switch(mobilePlatform) {
            case ANDROID:
                return new AndroidDriver(new URL(hubUrl), desiredCapabilities);
            case IOS:
                return new IOSDriver(new URL(hubUrl), desiredCapabilities);
            default:
                throw new RuntimeException("Cannot get the driver");
        }
    }



}
