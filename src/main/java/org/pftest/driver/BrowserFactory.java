package org.pftest.driver;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.pftest.constants.FrameworkConstants;

import java.util.HashMap;
import java.util.Map;

public enum BrowserFactory {
    CHROME {
        public WebDriver createDriver() {
            return new ChromeDriver(getOptions());
        }

        public ChromeOptions getOptions() {
            ChromeOptions options = getChromeOptions();

            Map<String, Object> prefs = new HashMap<String, Object>();
            prefs.put("profile.default_content_setting_values.notifications", 2);
            prefs.put("credentials_enable_service", false);
            prefs.put("profile.password_manager_enabled", false);
            prefs.put("autofill.profile_enabled", false);
            options.setExperimentalOption("prefs", prefs);

            return options;
        }

        private static ChromeOptions getChromeOptions() {
            ChromeOptions options = new ChromeOptions();

//            options.setBinary(FrameworkConstants.BROWSER_BINARY);
            options.addArguments("--user-data-dir=" + FrameworkConstants.USER_DATA_DIR);
//            options.addArguments("--profile-directory=" + FrameworkConstants.PROFILE_DIRECTORY);
            options.addArguments("--disable-extensions");
            options.addArguments("--disable-infobars");
            options.addArguments("--disable-notifications");
            options.addArguments("--remote-allow-origins=*");

            options.setAcceptInsecureCerts(true);
            return options;
        }
    },
    SAFARI {
        public WebDriver createDriver() {
            return new SafariDriver(getOptions());
        }

        public SafariOptions getOptions() {
            SafariOptions options = new SafariOptions();
            options.setAutomaticInspection(false);

            return options;
        }
    };

    public abstract WebDriver createDriver();

    public abstract MutableCapabilities getOptions();
}
