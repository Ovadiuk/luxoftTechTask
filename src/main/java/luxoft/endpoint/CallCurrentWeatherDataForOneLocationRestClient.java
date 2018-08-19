package luxoft.endpoint;

import com.fasterxml.jackson.databind.JsonNode;
import luxoft.configuration.AppProperties;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class CallCurrentWeatherDataForOneLocationRestClient {

    private AppProperties.CurrentWeatherDataForOneLocation currentWeatherDataForOneLocation;
    private RestTemplate restTemplate;

    public CallCurrentWeatherDataForOneLocationRestClient(RestTemplate restTemplate,
                                                          AppProperties.CurrentWeatherDataForOneLocation
                                                                  currentWeatherDataForOneLocation) {
        this.restTemplate = restTemplate;
        this.currentWeatherDataForOneLocation = currentWeatherDataForOneLocation;
    }

    public ResponseEntity<JsonNode> getWeatherByCityName(String cityName) {
        return restTemplate.exchange("{url}?q={cityName}&appid={appid}",
                HttpMethod.GET, new HttpEntity<>(new HttpHeaders()),
                JsonNode.class,
                currentWeatherDataForOneLocation.getUrl(),
                cityName,currentWeatherDataForOneLocation.getAppid());
    }

    public ResponseEntity<JsonNode> getWeatherByCityNameAndCountryName(String cityName,
                                                                       String countryName) {
        return restTemplate.exchange("{url}?q=/{cityName},{countryName}&appid={appid}",
                HttpMethod.GET, new HttpEntity<>(new HttpHeaders()),
                JsonNode.class,
                currentWeatherDataForOneLocation.getUrl(),
                cityName,
                countryName,
                currentWeatherDataForOneLocation.getAppid());
    }

    public ResponseEntity<JsonNode> getWeatherByCityId(String cityId) {
        return restTemplate.exchange("{url}?id={cityId}&appid={appid}",
                HttpMethod.GET, new HttpEntity<>(new HttpHeaders()),
                JsonNode.class,
                currentWeatherDataForOneLocation.getUrl(),
                cityId,
                currentWeatherDataForOneLocation.getAppid());
    }

    public ResponseEntity<JsonNode> getWeatherByGeographicCoordinates(String lat, String lon) {
        return restTemplate.exchange("{url}?lat={lat}&lon={lon}&appid={appid}",
                HttpMethod.GET, new HttpEntity<>(new HttpHeaders()),
                JsonNode.class,
                currentWeatherDataForOneLocation.getUrl(),
                lat, lon,
                currentWeatherDataForOneLocation.getAppid());
    }

    public ResponseEntity<JsonNode> getWeatherByZipCode(String zipCode, String countryCode) {
        return restTemplate.exchange("{url}?zip={zipCode},{countryCode}&appid={appid}",
                HttpMethod.GET, new HttpEntity<>(new HttpHeaders()),
                JsonNode.class,
                currentWeatherDataForOneLocation.getUrl(),
                zipCode, countryCode,
                currentWeatherDataForOneLocation.getAppid());
    }

}
