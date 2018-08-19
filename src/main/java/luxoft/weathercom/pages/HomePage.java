package luxoft.weathercom.pages;

import luxoft.configuration.AppProperties;
import luxoft.driver.WebDriverManager;
import luxoft.utils.WeatherConditionInfo;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HomePage {

    private WebDriver driver;
    private WebDriverManager driverManager;
    private BasePage basePage;
    private AppProperties.WeatherCom weatherCom;

    private By searchInputField = By.xpath("//input[starts-with(@class, 'theme__inputElement')]");
    private By currentTemperatureValue = By.cssSelector(".today_nowcard-temp span");
    private By weatherConditionValue = By.cssSelector(".today_nowcard-phrase");
    private By maxTempForThisDayValue = By.xpath("//span[@class='deg-hilo-nowcard'][1]");
    private By minTempForThisDayValue = By.xpath("//span[@class='deg-hilo-nowcard'][2]");
    private By listOfLocations = By.xpath("//div[starts-with(@class, 'styles__inner') and .//div[text()='Search results']]//li/a");
    private By windSpeedValue = By.xpath("//div[starts-with(@class,'today_nowcard-sidecar')]//tr[./th[text()='Wind']]/td");
    private By humidityValue = By.xpath("//div[starts-with(@class,'today_nowcard-sidecar')]//tr[./th[text()='Humidity']]/td/span/span");
    private By pressureValue = By.xpath("//div[starts-with(@class,'today_nowcard-sidecar')]//tr[./th[text()='Pressure']]/td/span");
    private By visibilityValue = By.xpath("//div[starts-with(@class,'today_nowcard-sidecar')]//tr[./th[text()='Visibility']]/td/span");

    private final WeatherConditionInfo weatherConditionInfo = new WeatherConditionInfo();

    public HomePage(WebDriverManager webDriverManager, BasePage basePage,
                    AppProperties.WeatherCom weatherCom) {
        this.basePage=basePage;
        this.driverManager = webDriverManager;
        this.weatherCom = weatherCom;
    }

    public void openWeatherCom (){
        driver = driverManager.getInstance();
        driver.get(weatherCom.getUrl());
    }

    public void clouseWeatherCom(){
        driverManager.close();
    }

    public HomePage selectLocationByName(final String location) {
        inputLocation(location);
        basePage.waitForElement(getListOfLocations().get(0));
        getListOfLocations().get(0).click();
        return this;
    }

    public HomePage inputLocation(final String location) {
        basePage.waitForElement(getSearchInputField());
        getSearchInputField().sendKeys(location);
        return this;
    }

    public HomePage saveMainTemperature() {
        final String currentTemp = getMainForTemperature().getText();
        weatherConditionInfo.setCurrentTemp(Integer.parseInt(currentTemp.substring(0, currentTemp.length() - 1)));
        return this;
    }

    public HomePage saveMinTemp() {
        final String minTemp = getMinTempForThisDayValue().getText();
        weatherConditionInfo.setMinTemp(Integer.parseInt(minTemp.substring(0, minTemp.length() - 1)));
        return this;
    }

    public HomePage saveMaxTemp() {
        final String maxTemp = getMaxTempForThisDayValue().getText();
        if (StringUtils.isNumeric(maxTemp)) {
            weatherConditionInfo.setMaxTemp(Integer.parseInt(getMaxTempForThisDayValue().getText()));
        }
        return this;
    }

    public HomePage saveWeatherConditionValue() {
        basePage.waitForElement(getWeatherCondition());
        weatherConditionInfo.setWeatherCondition(getWeatherCondition().getText());
        return this;
    }

    public HomePage saveWindSpeed() {
        weatherConditionInfo.setWindSpeed(Integer.parseInt(getWindSpeedValue().getText().split(" ")[1]));
        return this;
    }

    public HomePage saveHumidity() {
        final String humidity = getHumidityValue().getText();
        weatherConditionInfo.setHumidity(Integer.parseInt(humidity.substring(0, humidity.indexOf("%"))));
        return this;
    }

    public HomePage savePressure() {
        weatherConditionInfo.setPressure(Double.parseDouble(getPressureValue().getText().split(" ")[0]));
        return this;
    }

    public HomePage saveVisibility() {
        weatherConditionInfo.setVisibility(Double.parseDouble(getVisibilityValue().getText().split(" ")[0]));
        return this;
    }

    public WeatherConditionInfo getWeatherConditionInfo() {
        return weatherConditionInfo;
    }

    public WeatherConditionInfo getWeatherInfoForCity(final String city) {
        return selectLocationByName(city)
                .saveWeatherConditionValue()
                .saveMainTemperature()
                .saveMaxTemp()
                .saveMinTemp()
                .saveWindSpeed()
                .saveHumidity()
                .savePressure()
                .saveVisibility()
                .getWeatherConditionInfo();
    }

    private WebElement getCurrentTemperatureValue() {
        return driver.findElement(currentTemperatureValue);
    }

    private WebElement getWeatherConditionValue() {
        return driver.findElement(weatherConditionValue);
    }

    private WebElement getWindSpeedValue() {
        return driver.findElement(windSpeedValue);
    }

    private WebElement getHumidityValue() {
        return driver.findElement(humidityValue);
    }

    private WebElement getPressureValue() {
        return driver.findElement(pressureValue);
    }

    private WebElement getVisibilityValue() {
        return driver.findElement(visibilityValue);
    }

    private WebElement getMainForTemperature() {
        return driver.findElement(currentTemperatureValue);
    }

    private WebElement getWeatherCondition() {
        return driver.findElement(weatherConditionValue);
    }

    private WebElement getMaxTempForThisDayValue() {
        return driver.findElement(maxTempForThisDayValue);
    }

    private WebElement getMinTempForThisDayValue() {
        return driver.findElement(minTempForThisDayValue);
    }

    private WebElement getSearchInputField() {
        return driver.findElement(searchInputField);
    }

    private List<WebElement> getListOfLocations() {
        return driver.findElements(listOfLocations);
    }
}