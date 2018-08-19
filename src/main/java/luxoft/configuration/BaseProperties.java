package luxoft.configuration;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import luxoft.endpoint.CallCurrentWeatherDataForOneLocationRestClient;
import luxoft.driver.WebDriverManager;
import luxoft.weathercom.pages.BasePage;

import luxoft.weathercom.pages.HomePage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;


@Configuration
public class BaseProperties {

    @Bean
    public RestTemplate restTemplate(AppProperties appProperties) {
        RestTemplate restTemplate = new RestTemplate();
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setConnectTimeout(appProperties.getHttpTimeout());
        requestFactory.setReadTimeout(appProperties.getHttpTimeout());
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
            @Override
            protected boolean hasError(HttpStatus statusCode) {
                return false; // do not raise an exception if status code isn't successful
            }
        });
        restTemplate.setRequestFactory(requestFactory); // force Spring to pick Apache HttpComponents (needed for PATCH requests)
        return restTemplate;
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
        return objectMapper;
    }

    @Bean
    public CallCurrentWeatherDataForOneLocationRestClient callCurrentWeatherDataForOneLocationRestClient
            (RestTemplate restTemplate, AppProperties appProperties){
        return new CallCurrentWeatherDataForOneLocationRestClient(restTemplate,appProperties.getCurrentWeatherDataForOneLocation());
    }

    @Bean
    public WebDriverManager webDriverManager (AppProperties appProperties){
        return new WebDriverManager(appProperties.getDriverConf());
    }

    @Bean
    public BasePage basePage (WebDriverManager webDriverManager){
        return new BasePage(webDriverManager);
    }

    @Bean
    public HomePage homePage (WebDriverManager webDriverManager, BasePage basePage,
                              AppProperties appProperties){
        return new HomePage(webDriverManager,basePage,appProperties.getWeatherCom());
    }



}
