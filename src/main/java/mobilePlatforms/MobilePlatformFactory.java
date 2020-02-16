package mobilePlatforms;

import enums.MobilePlatform;

public class MobilePlatformFactory {

    public static synchronized AbstractPlatform getMobilePlatform(MobilePlatform platform) {
        switch (platform) {
            case ANDROID:
                return new AndroidPlatform(MobilePlatform.ANDROID);
            case IOS:
                return new IosPlatform(MobilePlatform.IOS);
            default:
                throw new RuntimeException(platform + " platform is not supported!");
        }
    }
}
