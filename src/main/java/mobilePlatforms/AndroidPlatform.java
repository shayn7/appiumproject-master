package mobilePlatforms;

import enums.MobilePlatform;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;

public class AndroidPlatform extends AbstractPlatform{

    private String packageName = propertiesReader.getPackageName();
    private String activity = propertiesReader.getActivityName();
    private String deviceName = propertiesReader.getDeviceName();
    private String platformVersion = propertiesReader.getPlatformVersion();
    private String automationName = propertiesReader.getAutomationName();
    private boolean noReset = propertiesReader.getNoResetValue();

    public AndroidPlatform(MobilePlatform mobilePlatform) {
        super(mobilePlatform);
    }

    @Override
    protected void initCapabilities() {
        capabilities.setCapability("deviceName", deviceName);
        capabilities.setCapability("platformName", mobilePlatform.toString());
        capabilities.setCapability("platformVersion", platformVersion);
        capabilities.setCapability("automationName", automationName);
        capabilities.setCapability("noReset",noReset);
        capabilities.setCapability("newCommandTimeout", 300);
        capabilities.setCapability("autoWebviewTimeout",5000);

    }

    @Override
    protected void addInstalledAppCapabilities() {
        capabilities.setCapability("appPackage", packageName);
        capabilities.setCapability("appActivity", activity);
    }
}
