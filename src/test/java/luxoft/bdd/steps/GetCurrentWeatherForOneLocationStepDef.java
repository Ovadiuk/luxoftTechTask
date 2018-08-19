package luxoft.bdd.steps;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cucumber.api.DataTable;
import cucumber.api.java8.En;
import luxoft.Application;
import luxoft.endpoint.CallCurrentWeatherDataForOneLocationRestClient;

import luxoft.weathercom.pages.HomePage;
import luxoft.utils.WeatherConditionInfo;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@SpringBootTest
@ContextConfiguration(classes = {Application.class})
public class GetCurrentWeatherForOneLocationStepDef implements En {

    @Autowired
    private CallCurrentWeatherDataForOneLocationRestClient restClient;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private HomePage homePage;

    private WeatherConditionInfo weatherFromUi;
    private WeatherConditionInfo weatherFromEndPoint = new WeatherConditionInfo();

    private ResponseEntity<JsonNode> response;

    public GetCurrentWeatherForOneLocationStepDef(){

        Given("User call method get Weather By City Name \"([^\"]*)\"$",(String cityName)->{
            response = restClient.getWeatherByCityName(cityName);
        });

        Given("User call method get Weather By City Name \"([^\"]*)\" name and Country \"([^\"]*)\"$",(String cityName,String country)->{
            response = restClient.getWeatherByCityNameAndCountryName(cityName,country);
        });

        Given("User call method get Weather By City id \"([^\"]*)\"$",(String cityId)->{
            response = restClient.getWeatherByCityId(cityId);
        });

        Given("User save information about weather from weather.com by City \"([^\"]*)\"$",(String cityName)->{
            homePage.openWeatherCom();
            weatherFromUi = homePage.getWeatherInfoForCity(cityName);
            homePage.clouseWeatherCom();
        });

        Given("User save information from EndPoint response$",()->{
            Assert.assertNotNull(response.getBody());
            weatherFromEndPoint.setCurrentTemp(response.getBody().with("main").get("temp").asInt());
            weatherFromEndPoint.setPressure(response.getBody().with("main").get("pressure").asDouble());
            weatherFromEndPoint.setHumidity(response.getBody().with("main").get("humidity").asInt());
            weatherFromEndPoint.setMinTemp(response.getBody().with("main").get("temp_min").asInt());
            weatherFromEndPoint.setMaxTemp(response.getBody().with("main").get("temp_max").asInt());
            weatherFromEndPoint.setVisibility(response.getBody().get("visibility").asDouble());
            weatherFromEndPoint.setWindSpeed(response.getBody().with("wind").get("speed").asInt());
            weatherFromEndPoint.setWeatherCondition(response.getBody().get("weather").get(0).get("description").asText());
        });

        Then("User compare information from UI and EndPoint$",()-> {
            assertTrue("Information from UI is not Equals to endPoint UI info =\n"
                            + weatherFromUi.toString()+"\nInfro from endPoint =/n"+ weatherFromEndPoint,
                    weatherFromUi.equals(weatherFromEndPoint));
        });

        Then("User receives status code \"(\\d{3})\"$",(Integer expectedStatusCode)-> {
            assertEquals(expectedStatusCode, ((Integer) response.getStatusCodeValue()));
        });

        Then("User search fields in response",(DataTable fieldNames)-> {
            List<String> fields = fieldNames.asList(String.class);
            for(String field : fields) {
                Assert.assertTrue("respons does not have field = "+field,response.getBody().findValues(field).size()>0);
            }
        });

    }
}
