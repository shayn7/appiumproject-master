package pages;

import mobilePlatforms.AbstractPlatform;

public enum Page {

    FacebookHomePage() {
        @Override
        public AbstractPage getPage(AbstractPlatform abstractPlatform)
        {
            return new FacebookHomePage(abstractPlatform);
        }
    },
    AppsFlyerPage() {
        @Override
        public AbstractPage getPage(AbstractPlatform abstractPlatform)
        {
            return new AppsFlyerPage(abstractPlatform);
        }
    },
    FacebookLoginPage() {
        @Override
        public AbstractPage getPage(AbstractPlatform abstractPlatform) {
            return new FacebookLoginPage(abstractPlatform);
        }
    };

    public abstract AbstractPage getPage(AbstractPlatform abstractPlatform);
}
