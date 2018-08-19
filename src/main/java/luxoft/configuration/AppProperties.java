package luxoft.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties
public class AppProperties {

    private CurrentWeatherDataForOneLocation currentWeatherDataForOneLocation =
            new CurrentWeatherDataForOneLocation();

    private Integer httpTimeout;

    private WeatherCom weatherCom = new WeatherCom();

    private DriverConf driverConf = new DriverConf();

    public CurrentWeatherDataForOneLocation getCurrentWeatherDataForOneLocation() {
        return currentWeatherDataForOneLocation;
    }



    public Integer getHttpTimeout() {
        return httpTimeout;
    }

    public void setHttpTimeout(Integer httpTimeout) {
        this.httpTimeout = httpTimeout;
    }

    public WeatherCom getWeatherCom() {
        return weatherCom;
    }



    public DriverConf getDriverConf() {
        return driverConf;
    }



    public static class CurrentWeatherDataForOneLocation {
       private String url;
       private String appid;

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getUrl() {
           return url;
       }

       public void setUrl(String url) {
           this.url = url;
       }
   }

   public static class WeatherCom {
       private String url;

       public String getUrl() {
           return url;
       }

       public void setUrl(String url) {
           this.url = url;
       }
   }

   public static class DriverConf{
       private Integer implicitlyWait;

       public Integer getImplicitlyWait() {
           return implicitlyWait;
       }

       public void setImplicitlyWait(Integer implicitlyWait) {
           this.implicitlyWait = implicitlyWait;
       }
   }
}
