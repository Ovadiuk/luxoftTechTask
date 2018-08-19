package luxoft.driver;

import luxoft.configuration.AppProperties;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.Platform.getCurrent;

public class WebDriverManager {

    private WebDriver driver;
    private AppProperties.DriverConf driverConf;

    public WebDriverManager (AppProperties.DriverConf driverConf){
        this.driverConf = driverConf;
    }

    public WebDriver getInstance() {
        if (driver == null) {
            ChromeOptions options = new ChromeOptions();
            Map<String, Object> prefs = new HashMap<String, Object>();
            prefs.put("profile.default_content_settings.popups", 0);
            options.setExperimentalOption("prefs", prefs);
            options.addArguments("start-maximized");
            setWebDriverExecutable();
            driver = new ChromeDriver(options);
            driver.manage().timeouts().implicitlyWait(driverConf.getImplicitlyWait(), TimeUnit.SECONDS);
        }
        return driver;
    }

    public void close() {
        driver.close();
        driver = null;
    }

    public void setWebDriverExecutable(){
        final String seleniumDriverProperty = "webdriver.chrome.driver";
        final Platform platform = getCurrent();
        final String pathToDriverFolder = System.getProperty("user.dir") + "/drivers/chrome/";
        switch (platform) {
            case WIN8:
            case WIN8_1:
            case WINDOWS:
            case WIN10:
                System.setProperty(seleniumDriverProperty, pathToDriverFolder + "chrome_driver_windows_32.exe");
                break;
            case MAC:
                System.setProperty(seleniumDriverProperty, pathToDriverFolder + "chrome_driver_mac_64");
                break;
            case LINUX:
                System.setProperty(seleniumDriverProperty, pathToDriverFolder + "chrome_driver_linux_64");
        }
    }

    public void executeScript(final String script){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(script);
    }
}
